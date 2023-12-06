package com.github.iunius118.tolaserblade.client;

import net.minecraft.client.render.Tessellator;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class LaserBladeRenderer {
	public static void doRender(Entity entity, ItemStack itemstack, boolean handheldTransform) {
		Tessellator tessellator = Tessellator.instance;

		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		tessellator.startDrawingQuads();
		tessellator.addVertex(0.0D, 0.0D, 0.0D);
		tessellator.addVertex(0.0D, 1.0D, 0.0D);
		tessellator.addVertex(1.0D, 1.0D, 0.0D);
		tessellator.addVertex(1.0D, 0.0D, 0.0D);
		tessellator.draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
}
