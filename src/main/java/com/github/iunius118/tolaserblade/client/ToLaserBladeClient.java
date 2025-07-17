package com.github.iunius118.tolaserblade.client;

import com.github.iunius118.tolaserblade.common.ToLaserBlade;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import turniplabs.halplibe.util.ModelEntrypoint;

public class ToLaserBladeClient implements ClientModInitializer, ModelEntrypoint {

	@Override
	public void onInitializeClient() {}

	@Override
	public void initBlockModels(BlockModelDispatcher dispatcher) {}

	@Override
	public void initItemModels(ItemModelDispatcher dispatcher) {
		// Register item models
		dispatcher.addDispatch(ToLaserBlade.lbSword, new ItemModelLBSword(ToLaserBlade.lbSword, null));
	}

	@Override
	public void initEntityModels(EntityRenderDispatcher dispatcher) {}

	@Override
	public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {}

	@Override
	public void initBlockColors(BlockColorDispatcher dispatcher) {}
}
