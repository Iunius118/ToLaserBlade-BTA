package com.github.iunius118.tolaserblade.common;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.event.defs.CommonEvents;
import turniplabs.halplibe.helper.ItemBuilder;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryCategory;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryPlacement;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.dependency.Key;
import turniplabs.halplibe.util.toml.Toml;

import java.util.List;
import java.util.stream.IntStream;

public class ToLaserBlade implements ModInitializer {
	public static final String MOD_ID = HalpLibe.registerMod("tolaserblade", true);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Mod Config
	public static TomlConfigHandler config;
	private static final Toml TOML = new Toml("ToLaserBlade configuration file.");

	// Mod item
	public static Item lbSword;

	@Override
	public void onInitialize() {
		// Register event listeners
		CommonEvents.BEFORE_GAME_START.listen(Key.of(MOD_ID), this::beforeGameStart);
		CommonEvents.AFTER_GAME_START.listen(Key.of(MOD_ID), this::afterGameStart);
		CommonEvents.AFTER_ITEM_INIT.listen(Key.of(MOD_ID), this::registerItems);
		CommonEvents.RECIPES_NAMESPACE_INIT.listen(Key.of(MOD_ID), this::initNamespaces);
		CommonEvents.RECIPES_READY.listen(Key.of(MOD_ID), this::onRecipesReady);

		LOGGER.info("ToLaserBlade initialized.");
	}

	private void beforeGameStart() {
		// Handle config
		TOML.addCategory("IDs")
			.addEntry("starting_item_id",
				"The integer specifies the starting ID for the mod's items.", 24530);
		TOML.addCategory("Client")
			.addEntry("use_3d_laser_blade_icons",
				"Use 3D models for Laser Blades on the head, on the ground, and in GUIs if true, " +
					"or 2D textures if false.", true);
		config = new TomlConfigHandler(MOD_ID, TOML);
	}

	private void afterGameStart() {

	}

	private void registerItems(){
		int startingItemId = config.getInt("IDs.starting_item_id");

		// Register laser blade item and its creative inventory placement
		var laserBladePlacement = new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)
			.setCustomSupplier(() ->
				IntStream.range(0, 16)
					.mapToObj(i -> new ItemStack(lbSword, 1, i))
					.toList()
			);
		lbSword = new ItemBuilder(MOD_ID)
			.setCreativeInventoryPlacement(laserBladePlacement)
			.build(new ItemLBSword("laser_blade", startingItemId++));
	}

	private void initNamespaces() {
		RecipeBuilder.initNameSpace(MOD_ID);
		registerItemGroups();
	}

	private void registerItemGroups() {
		// Register item group for laser blades
		List<ItemStack> laserBlades = IntStream.range(0, 16)
			.mapToObj(i -> new ItemStack(lbSword, 1, i))
			.toList();
		Registries.ITEM_GROUPS.register(MOD_ID + ":laser_blades", laserBlades);
	}

	private void onRecipesReady() {
		// Register recipes
		// Colored laser blades
		for (int i = 0; i < 16; i++) {
			RecipeBuilder.Shaped(MOD_ID)
				.setShape("gsd", "sLs", "rsg")
				.addInput('g', Items.DUST_GLOWSTONE)
				.addInput('s', Items.INGOT_STEEL)
				.addInput('d', Items.DIAMOND)
				.addInput('L', new ItemStack(Blocks.LAMP_IDLE, 1, i))
				.addInput('r', Items.DUST_REDSTONE)
				.create("laser_blade_%02d".formatted(i), new ItemStack(lbSword, 1, i));
		}
	}
}
