package com.github.iunius118.tolaserblade.client;

import com.github.iunius118.tolaserblade.common.ToLaserBlade;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.renderer.Shaders;
import net.minecraft.client.render.shader.Shader;
import turniplabs.halplibe.event.defs.ClientEvents;
import turniplabs.halplibe.util.dependency.Key;

public class ToLaserBladeClient implements ClientModInitializer {
	// Register mod shader
	public static final Shader LASER_BLADE_SHADER = Shaders.register("tolaserblade/laser_blade", new Shader());

	// Client config option
	public static boolean use3DLaserBladeIcons = true;

	@Override
	public void onInitializeClient() {
		// Register event listeners
		ClientEvents.ITEM_MODEL_RELOAD.listen(Key.of(ToLaserBlade.MOD_ID), this::initItemModels);
	}

	private void initItemModels(ItemModelDispatcher dispatcher) {
		// Get item model config
		use3DLaserBladeIcons = ToLaserBlade.config.getBoolean("Client.use_3d_laser_blade_icons");

		// Register item models
		dispatcher.addDispatch(ToLaserBlade.lbSword, new ItemModelLBSword(ToLaserBlade.lbSword));
	}
}
