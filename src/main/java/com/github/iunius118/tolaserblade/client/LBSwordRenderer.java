package com.github.iunius118.tolaserblade.client;

import net.minecraft.client.render.LightmapHelper;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

import java.util.List;

public class LBSwordRenderer {

	public static void doRender(Tessellator tessellator, Entity entity, ItemStack itemStack, float brightness, float alpha, boolean worldTransform) {
		// Change render settings
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		// Transform for laser blade model
		GL11.glTranslatef(0.905719F, 0.0707107F, -0.0234375F);
		// The following is equal to the right: GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F); GL11.glRotatef(-95.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(102.753013F, 0.361063F, -0.871876F, 0.330857F);
		// Fix scaling
		GL11.glScalef(1.2F, 1.2F, 1.2F);

		// Get laser blade color
		boolean isSubMode = false;
		int metadata = itemStack.getMetadata();
		LaserBladeColor color = LaserBladeColor.COLORS[metadata];

		// Render hilt
		renderHilt(tessellator, color.gripColor, brightness);

		// Change render mode for laser blades
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);

		// Blades are rendered at full brightness
		if (LightmapHelper.isLightmapEnabled()) {
				LightmapHelper.setLightmapCoord(LightmapHelper.getLightmapCoord(15, 15));
		}

		// Render blades
		isSubMode = setBlendMode(color.isOutSubColor, isSubMode);
		renderBlade(tessellator, LBSwordModel.BLADE_OUT_QUADS, color.outerColor, LBSwordModel.BLADE_OUT_COLOR.w);
		isSubMode = setBlendMode(color.isMidSubColor, isSubMode);
		renderBlade(tessellator, LBSwordModel.BLADE_MID_QUADS, color.outerColor, LBSwordModel.BLADE_MID_COLOR.w);
		isSubMode = setBlendMode(color.isInSubColor, isSubMode);
		renderBlade(tessellator, LBSwordModel.BLADE_IN_QUADS, color.innerColor, LBSwordModel.BLADE_IN_COLOR.w);

		// Restore render settings
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		setBlendMode(false, isSubMode);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_CULL_FACE);
	}

	private static void renderHilt(Tessellator tessellator, Color4F color, float brightness) {
		GL11.glColor4f(color.r * brightness, color.g * brightness, color.b * brightness, color.a);

		for (SimpleQuad quad : LBSwordModel.HILT_QUADS) {
			quad.renderQuad(tessellator);
		}
	}

	private static void renderBlade(Tessellator tessellator, List<SimpleQuad> quads, Color4F color, float opacity) {
		GL11.glColor4f(color.r, color.g, color.b, color.a * opacity);

		for (SimpleQuad quad : quads) {
			quad.renderQuad(tessellator);
		}
	}

	private static boolean setBlendMode(boolean makeSubMode, boolean isSubMode) {
		if (makeSubMode == isSubMode) {
			return isSubMode;
		} else if (!makeSubMode) {
			// !makeSubMode && isSubMode
			GL14.glBlendEquation(GL14.GL_FUNC_ADD);
			return false;
		} else {
			// makeSubMode && !isSubMode
			GL14.glBlendEquation(GL14.GL_FUNC_REVERSE_SUBTRACT);
			return true;
		}
	}
}
