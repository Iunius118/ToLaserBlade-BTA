package com.github.iunius118.tolaserblade.mixin;

import com.github.iunius118.tolaserblade.client.LBSwordRenderer;
import com.github.iunius118.tolaserblade.common.ToLaserBlade;
import net.minecraft.client.render.ItemRenderer;
import net.minecraft.client.render.RenderBlocks;
import net.minecraft.client.render.block.model.BlockModelRenderBlocks;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ItemRenderer.class, remap = false)
public class MixinItemRender {
	@Shadow
	private RenderBlocks renderBlocksInstance;

	@Unique
	private LBSwordRenderer lBSwordRenderer = new LBSwordRenderer();

	@Inject(method = "renderItem(Lnet/minecraft/core/entity/Entity;Lnet/minecraft/core/item/ItemStack;Z)V", at = @At("HEAD"), cancellable = true)
	private void onRenderItem(Entity entity, ItemStack itemstack, boolean handheldTransform, CallbackInfo ci) {
		if (itemstack.itemID == ToLaserBlade.LB_SWORD.id) {
			GL11.glPushMatrix();
			BlockModelRenderBlocks.setRenderBlocks(this.renderBlocksInstance);
			GL11.glEnable(32826);

			if (handheldTransform) {
				GL11.glTranslatef(0.0F, -0.3F, 0.0F);
				GL11.glScalef(1.5F, 1.5F, 1.5F);
				GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
			}

			lBSwordRenderer.doRender(entity, itemstack, handheldTransform);
			GL11.glDisable(32826);
			GL11.glPopMatrix();

			ci.cancel();
		}
	}
}
