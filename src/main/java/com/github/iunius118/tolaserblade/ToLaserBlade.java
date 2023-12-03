package com.github.iunius118.tolaserblade;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.material.ToolMaterial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.ItemHelper;
import turniplabs.halplibe.helper.RecipeHelper;
import turniplabs.halplibe.util.ConfigHandler;

import java.util.Properties;


public class ToLaserBlade implements ModInitializer {
    public static final String MOD_ID = "tolaserblade";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final int STARTING_ITEM_ID;

	static {
		// Handle config
		Properties prop = new Properties();
		prop.setProperty("starting_item_id","24530");
		ConfigHandler config = new ConfigHandler(MOD_ID, prop);
		STARTING_ITEM_ID = config.getInt("starting_item_id");
		config.updateConfig();
	}

	public static final ToolMaterial LASER_MATERIAL = new ToolMaterial().setDurability(0).setEfficiency(7.0F, 14.0F).setMiningLevel(3).setDamage(4).setBlockHitDelay(4);
	public static final Item LB_SWORD = ItemHelper.createItem(MOD_ID, new ItemLBSword("laser_blade", STARTING_ITEM_ID, LASER_MATERIAL), "laser_blade", "laser_blade.png");

    @Override
    public void onInitialize() {
	    RecipeHelper.Crafting.createRecipe(LB_SWORD, 1, new Object[]{
			" id",
		    "igi",
		    "ri ",
		    'i', Item.ingotIron,
		    'd', Item.diamond,
		    'q', Item.dustGlowstone,
		    'r', Item.dustRedstone});

	    LOGGER.info("ToLaserBlade initialized.");
    }
}
