package com.github.iunius118.tolaserblade.client;

import com.github.iunius118.tolaserblade.common.ToLaserBlade;
import net.minecraft.client.render.ItemRenderer;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class ItemModelLBSword extends ItemModelStandard {
	public static IconCoordinate[] lbSwordIcons = new IconCoordinate[16];

	public ItemModelLBSword(Item item, String namespace) {
		super(item, namespace);
	}

	@Override
	public @NotNull IconCoordinate getIcon(@Nullable Entity entity, ItemStack itemStack) {
		int meta = itemStack.getMetadata();
		return lbSwordIcons[meta & 15];
	}

	@Override
	public void renderItemInWorld(Tessellator tessellator, Entity entity, ItemStack itemStack, float brightness, float alpha, boolean worldTransform) {
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		LBSwordRenderer.doRender(tessellator, entity, itemStack, brightness, alpha, worldTransform);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	}

	@Override
	public void heldTransformThirdPerson(ItemRenderer renderer, Entity entity, ItemStack itemStack) {
		// Transform for laser blade model in third person view
		GL11.glTranslatef(0.0F, -0.046875F, 0.0F);
		GL11.glScalef(0.625F, 0.625F, 0.625F);
		GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
	}

	static {
		// Register item icons
		for (int i = 0; i < lbSwordIcons.length; i++) {
			String texture = String.format("%s:item/laser_blade_%d", ToLaserBlade.MOD_ID, i);
			lbSwordIcons[i] = TextureRegistry.getTexture(texture);
		}
	}
}
