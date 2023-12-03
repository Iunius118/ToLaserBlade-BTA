package com.github.iunius118.tolaserblade;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Override
    public void onInitialize() {
        LOGGER.info("ExampleMod initialized.");
    }
}
