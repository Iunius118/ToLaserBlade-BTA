package com.github.iunius118.tolaserblade.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiPhotoMode;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.camera.EntityCamera;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.MathHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.util.vector.Vector4f;

import java.util.List;

public class LBSwordRenderer {
	public void doRender(Entity entity, ItemStack itemstack, boolean handheldTransform, boolean isXMirrored) {
		Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
		Tessellator tessellator = Tessellator.instance;

		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		// Transform for laser blade model
		GL11.glTranslatef(0.905719F, 0.0707107F, -0.0234375F);
		// The following is equal to the right: GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F); GL11.glRotatef(-95.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(102.753013F, 0.361063F, -0.871876F, 0.330857F);
		// Fix scaling
		if (isXMirrored) {
			GL11.glScalef(-1.2F, 1.2F, 1.2F);
		} else {
			GL11.glScalef(1.2F, 1.2F, 1.2F);
		}

		// Render hilt
		renderHilt(tessellator, getBrightness(mc, entity));

		// Change render mode for laser blades
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		//GL14.glBlendEquation(GL14.GL_FUNC_REVERSE_SUBTRACT);

		// Render blades
		renderBlade(tessellator, LBSwordModel.BLADE_OUT_QUADS, new Vector4f(1.0F, 0.0F, 0.0F, LBSwordModel.BLADE_OUT_COLOR.w));
		renderBlade(tessellator, LBSwordModel.BLADE_MID_QUADS, new Vector4f(1.0F, 0.0F, 0.0F, LBSwordModel.BLADE_MID_COLOR.w));
		renderBlade(tessellator, LBSwordModel.BLADE_IN_QUADS, LBSwordModel.BLADE_IN_COLOR);

		// Restore render settings
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL14.glBlendEquation(GL14.GL_FUNC_ADD);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_CULL_FACE);
	}

	private boolean isRenderingItemInFirstPerson(Minecraft mc) {
        return mc.gameSettings.thirdPersonView.value == 0
                && !(mc.currentScreen instanceof GuiPhotoMode)
                && mc.activeCamera instanceof EntityCamera
                && !((EntityCamera) mc.activeCamera).entity.isPlayerSleeping()
                && mc.gameSettings.immersiveMode.drawHand()
                && (!mc.gameSettings.keyZoom.isPressed() || mc.currentScreen != null);
    }

	private float getBrightness(Minecraft mc, Entity entity) {
		if (mc.fullbright) {
			return 1.0F;
		} else {
			return mc.theWorld.getLightBrightness(MathHelper.floor_double(entity.x), MathHelper.floor_double(entity.y), MathHelper.floor_double(entity.z));
		}
	}

	private void renderHilt(Tessellator tessellator, float brightness) {
		Vector4f color = LBSwordModel.Hilt_COLOR;
		GL11.glColor4f(color.x * brightness, color.y * brightness, color.z * brightness, color.w);

		for (SimpleQuad quad : LBSwordModel.HILT_QUADS) {
			quad.renderQuad(tessellator);
		}
	}

	private void renderBlade(Tessellator tessellator, List<SimpleQuad> quads, Vector4f color) {
		GL11.glColor4f(color.x, color.y, color.z, color.w);

		for (SimpleQuad quad : quads) {
			quad.renderQuad(tessellator);
		}
	}
}
