package com.github.iunius118.tolaserblade.mixin;

import com.github.iunius118.tolaserblade.client.ToLaserBladeClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.render.WorldRenderer;
import org.joml.Vector3dc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WorldRenderer.class, remap = false)
public abstract class WorldRendererMixin {

	@Inject(method = "renderWorld(FJ)V", at = @At("HEAD"))
	private void updateLaserBladeTrails(float partialTicks, long updateRenderersUntil, CallbackInfo ci) {
		if (ToLaserBladeClient.enableLaserBladeTrail) {
			long now = System.nanoTime();
			ToLaserBladeClient.thePlayerTrails.updateAll(now);
		}
	}

	@Inject(
		method = "renderWorld(FJ)V",
		at = @At(
			value = "INVOKE",
			target ="Lnet/minecraft/core/util/debug/Debug;change(Ljava/lang/String;)V"
		),
		slice = @Slice(
			from = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/RenderGlobal;" +
				"renderEntities(Lnet/minecraft/client/render/camera/ICamera;F)V"),
			to = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/ParticleEngine;" +
				"renderLitParticles(Lnet/minecraft/client/render/camera/ICamera;F)V")
		)
	)
	private void renderThirdPersonLaserBladeTrails(float partialTicks, long updateRenderersUntil, CallbackInfo ci) {
		if (ToLaserBladeClient.enableLaserBladeTrail && GameSettings.THIRD_PERSON_VIEW.value != 0) {
			Vector3dc cameraPos = Minecraft.getMinecraft().activeCamera.getPosition(partialTicks);
			long now = System.nanoTime();

			if (GameSettings.PLAYER_LEFT_HANDED.value) {
				ToLaserBladeClient.thePlayerTrails.thirdPersonLeftHandTrail().doRender(cameraPos, now, partialTicks);
			} else {
				ToLaserBladeClient.thePlayerTrails.thirdPersonRightHandTrail().doRender(cameraPos, now, partialTicks);
			}
		}
	}

	@Inject(
		method = "renderWorld(FJ)V",
		at = @At(
			value = "INVOKE",
			target ="Lnet/minecraft/client/render/WorldRenderer;renderHand(F)V",
			shift = At.Shift.AFTER
		)
	)
	private void renderFirstPersonLaserBladeTrails(float partialTicks, long updateRenderersUntil, CallbackInfo ci) {
		if (ToLaserBladeClient.enableLaserBladeTrail) {
			long now = System.nanoTime();

			if (GameSettings.PLAYER_LEFT_HANDED.value) {
				ToLaserBladeClient.thePlayerTrails.firstPersonLeftHandTrail().doRender(now, partialTicks);
			} else {
				ToLaserBladeClient.thePlayerTrails.firstPersonRightHandTrail().doRender(now, partialTicks);
			}
		}
	}
}
