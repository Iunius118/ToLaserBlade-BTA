package com.github.iunius118.tolaserblade.common;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.ItemBuilder;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

import java.util.Properties;


public class ToLaserBlade implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
	public static final String MOD_ID = "tolaserblade";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Mod Config
	public static TomlConfigHandler config;
	private static final Toml TOML = new Toml("ToLaserBlade Config");

	// Mod item
	public static Item lbSword;

	@Override
	public void onInitialize() {
		LOGGER.info("ToLaserBlade initialized.");
	}

	@Override
	public void beforeGameStart() {
		// Handle config
		TOML.addCategory("IDs")
			.addEntry("starting_item_id", 24530);
		config = new TomlConfigHandler(MOD_ID, TOML);
		int startingItemId = config.getInt("IDs.starting_item_id");

		// Register item
		lbSword = new ItemBuilder(MOD_ID)
			.build(new ItemLBSword("laser_blade", String.format("%s:item/%s", MOD_ID, "laser_blade"), startingItemId++));
	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void onRecipesReady() {
		// Register recipes
		// Colored laser blades
		for (int i = 0; i < 16; i++) {
			RecipeBuilder.Shaped(MOD_ID)
				.setShape("gsd", "sLs", "rsg")
				.addInput('g', Item.dustGlowstone)
				.addInput('s', Item.ingotSteel)
				.addInput('d', Item.diamond)
				.addInput('L', new ItemStack(Block.lampIdle, 1, i))
				.addInput('r', Item.dustRedstone)
				.create("laser_blade_" + i, new ItemStack(lbSword, 1, i));
		}
	}

	@Override
	public void initNamespaces() {
		RecipeBuilder.initNameSpace(MOD_ID);
	}
}
