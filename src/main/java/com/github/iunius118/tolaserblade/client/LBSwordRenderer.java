package com.github.iunius118.tolaserblade.client;

import net.minecraft.client.render.renderer.BlendFactor;
import net.minecraft.client.render.renderer.GLRenderer;
import net.minecraft.client.render.renderer.State;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.LightIndexHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL14;

import java.util.List;

public class LBSwordRenderer {
	private static final byte FULL_BRIGHT_LIGHT_INDEX = LightIndexHelper.lightIndex2i(15, 15);

	public static void doRender(@NotNull TessellatorGeneral tessellator, @Nullable Entity holder,
								@NotNull ItemStack itemStack, @NotNull String displayPosId, byte lightIndex,
								float partialTick) {
		// Change render settings
		GLRenderer.setShader(ToLaserBladeClient.LASER_BLADE_SHADER);
		GLRenderer.enableState(State.BLEND);
		GLRenderer.enableState(State.CULL_FACE);
		GLRenderer.globalSetLightEnabled(true);

		// Get laser blade color
		boolean isSubMode = false;
		int metadata = itemStack.getMetadata();
		LaserBladeColor color = LaserBladeColor.COLORS[metadata];

		// Render hilt
		renderHilt(tessellator, lightIndex, color.gripColor());

		// Change render mode for laser blades
		GLRenderer.globalSetLightEnabled(false);
		GLRenderer.setBlendFunc(BlendFactor.SRC_ALPHA, BlendFactor.ONE);

		// Render blades
		isSubMode = setBlendMode(color.isOutSubColor(), isSubMode);
		renderBlade(tessellator, LBSwordModel.BLADE_OUT_QUADS, color.outerColor(), LBSwordModel.BLADE_OUT_COLOR.w);
		isSubMode = setBlendMode(color.isMidSubColor(), isSubMode);
		renderBlade(tessellator, LBSwordModel.BLADE_MID_QUADS, color.outerColor(), LBSwordModel.BLADE_MID_COLOR.w);
		isSubMode = setBlendMode(color.isInSubColor(), isSubMode);
		renderBlade(tessellator, LBSwordModel.BLADE_IN_QUADS, color.innerColor(), LBSwordModel.BLADE_IN_COLOR.w);

		// Restore render settings
		setBlendMode(false, isSubMode);
		GLRenderer.globalSetLightEnabled(true);
		GLRenderer.disableState(State.CULL_FACE);
		GLRenderer.setBlendFunc(BlendFactor.SRC_ALPHA, BlendFactor.ONE_MINUS_SRC_ALPHA);
		GLRenderer.disableState(State.BLEND);
	}

	private static void renderHilt(@NotNull TessellatorGeneral tessellator, byte lightIndex, @NotNull Color4F color) {
		tessellator.startDrawingQuads();
		tessellator.setLightmapCoord1i(lightIndex);
		tessellator.setColor4f(color.r(), color.g(), color.b(), color.a());

		for (SimpleQuad quad : LBSwordModel.HILT_QUADS) {
			quad.addTo(tessellator);
		}

		tessellator.draw();
	}

	private static void renderBlade(@NotNull TessellatorGeneral tessellator, @NotNull List<SimpleQuad> quads,
									@NotNull Color4F color, float opacity) {
		tessellator.startDrawingQuads();
		tessellator.setLightmapCoord1i(FULL_BRIGHT_LIGHT_INDEX);
		tessellator.setColor4f(color.r(), color.g(), color.b(), color.a() * opacity);

		for (SimpleQuad quad : quads) {
			quad.addTo(tessellator);
		}

		tessellator.draw();
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
