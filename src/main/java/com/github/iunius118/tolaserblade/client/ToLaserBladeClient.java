package com.github.iunius118.tolaserblade.client;

import com.github.iunius118.tolaserblade.common.ToLaserBlade;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.TextureHelper;

public class ToLaserBladeClient implements ClientModInitializer {
	public static final int[] LB_SWORD_ICONS = new int[16];

	@Override
	public void onInitializeClient() {
		initLaserBladeIcons();
	}

	private void initLaserBladeIcons() {
		for (int i = 0; i < 16; i++) {
			String texture = String.format("laser_blade_%d.png", i);
			int[] texCoords = TextureHelper.getOrCreateItemTexture(ToLaserBlade.MOD_ID, texture);
			LB_SWORD_ICONS[i] = Item.iconCoordToIndex(texCoords[0], texCoords[1]);
		}
	}
}
