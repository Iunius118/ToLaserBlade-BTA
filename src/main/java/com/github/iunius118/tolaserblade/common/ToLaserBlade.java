package com.github.iunius118.tolaserblade.common;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.ItemHelper;
import turniplabs.halplibe.helper.RecipeHelper;
import turniplabs.halplibe.util.ConfigHandler;

import java.util.Properties;


public class ToLaserBlade implements ModInitializer {
	public static final String MOD_ID = "tolaserblade";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// ID
	public static final int STARTING_ITEM_ID;

	static {
		// Handle config
		Properties prop = new Properties();
		prop.setProperty("starting_item_id", "24530");
		ConfigHandler config = new ConfigHandler(MOD_ID, prop);
		STARTING_ITEM_ID = config.getInt("starting_item_id");
		config.updateConfig();
	}

	// Register item
	public static final Item LB_SWORD = ItemHelper.createItem(MOD_ID, new ItemLBSword("laser_blade", STARTING_ITEM_ID), "laser_blade");

	@Override
	public void onInitialize() {
		// Register recipe
		addLaserBladeRecipes();

		LOGGER.info("ToLaserBlade initialized.");
	}

	private void addLaserBladeRecipes() {
		for (int i = 0; i < 16; i++) {
			RecipeHelper.Crafting.createRecipe(new ItemStack(LB_SWORD, 1, i), new Object[]{
				"gsd",
				"sLs",
				"rsg",
				'g', Item.dustGlowstone,
				's', Item.ingotSteel,
				'd', Item.diamond,
				'L', new ItemStack(Block.lampIdle, 1, i),
				'r', Item.dustRedstone});
		}
	}
}
