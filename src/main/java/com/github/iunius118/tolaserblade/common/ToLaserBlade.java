package com.github.iunius118.tolaserblade.common;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.ItemHelper;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.ConfigHandler;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

import java.util.Properties;


public class ToLaserBlade implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
	public static final String MOD_ID = "tolaserblade";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Mod item
	public static Item lb_sword;

	@Override
	public void onInitialize() {
		LOGGER.info("ToLaserBlade initialized.");
	}

	@Override
	public void beforeGameStart() {
		// Handle config
		Properties prop = new Properties();
		prop.setProperty("starting_item_id", "24530");
		ConfigHandler config = new ConfigHandler(MOD_ID, prop);
		int startingItemId = config.getInt("starting_item_id");
		config.updateConfig();

		// Register item
		lb_sword = ItemHelper.createItem(MOD_ID, new ItemLBSword("laser_blade", startingItemId++).setKey("laser_blade"));
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
				.create("laser_blade_" + i, new ItemStack(lb_sword, 1, i));
		}
	}

	@Override
	public void initNamespaces() {
		RecipeBuilder.initNameSpace(MOD_ID);
	}
}
