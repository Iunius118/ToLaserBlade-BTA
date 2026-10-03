package com.github.iunius118.tolaserblade.client;

import net.minecraft.client.render.renderer.BlendFactor;
import net.minecraft.client.render.renderer.GLRenderer;
import net.minecraft.client.render.renderer.Shaders;
import net.minecraft.client.render.renderer.State;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import java.util.ArrayDeque;
import java.util.Deque;

public class LaserBladeTrail {
	public static long DEFAULT_LIFETIME = 200_000_000L;

	public final long lifetimeNanos;
	private final Deque<TrailPoint> points = new ArrayDeque<>();

	public LaserBladeTrail(long lifetimeNanos) {
		this.lifetimeNanos = lifetimeNanos;
	}

	public LaserBladeTrail() {
		this(DEFAULT_LIFETIME);
	}

	public void add(@NotNull Vector3dc root, @NotNull Vector3dc tip, @NotNull Color4F color, long now) {
		points.addLast(new TrailPoint(root, tip, color, now + lifetimeNanos));
	}

	public void update(long now) {
		while (!points.isEmpty() && points.peekFirst().expireTime() <= now) {
			points.removeFirst();
		}
	}

	public void clear() {
		points.clear();
	}

	public boolean isEmpty() {
		return points.isEmpty();
	}

	public Iterable<TrailPoint> points() {
		return points;
	}

	public void doRender(Vector3dc offsetPos, long now, float partialTicks) {
		if (points.size() < 2) {
			return;
		}

		GLRenderer.pushFrame();

		// Change render settings
		GLRenderer.setShader(Shaders.COLOR);
		GLRenderer.enableState(State.BLEND);
		GLRenderer.disableState(State.CULL_FACE);
		GLRenderer.setBlendFunc(BlendFactor.SRC_ALPHA, BlendFactor.ONE);
		GLRenderer.globalSetLightEnabled(false);

		GLRenderer.modelM4f().identity();

		TessellatorGeneral tessellator = GLRenderer.getTessellator();
		tessellator.startDrawingQuads();

		TrailPoint prevPoint = null;

		for (LaserBladeTrail.TrailPoint point : points) {
			if (prevPoint != null) {
				// point(t - 1)
				Color4F prevColor = prevPoint.color();
				float prevAlpha = Math.max((float) (prevPoint.expireTime() - now) / lifetimeNanos, 0);
				tessellator.setColor4f(prevColor.r(), prevColor.g(), prevColor.b(), prevAlpha);
				tessellator.addVertex(new Vector3d(prevPoint.root()).sub(offsetPos));
				tessellator.addVertex(new Vector3d(prevPoint.tip()).sub(offsetPos));
				// point(t)
				Color4F color = point.color();
				float alpha = Math.max((float) (point.expireTime() - now) / lifetimeNanos, 0);
				tessellator.setColor4f(color.r(), color.g(), color.b(), alpha);
				tessellator.addVertex(new Vector3d(point.tip()).sub(offsetPos));
				tessellator.addVertex(new Vector3d(point.root()).sub(offsetPos));
			}

			prevPoint = point;
		}

		tessellator.draw();

		// Restore render settings
		GLRenderer.globalSetLightEnabled(true);
		GLRenderer.setBlendFunc(BlendFactor.SRC_ALPHA, BlendFactor.ONE_MINUS_SRC_ALPHA);
		GLRenderer.disableState(State.BLEND);

		GLRenderer.popFrame();
	}

	public void doRender(long now, float partialTicks) {
		doRender(new Vector3d(), now, partialTicks);
	}

	public record TrailPoint(
		@NotNull Vector3dc root,
		@NotNull Vector3dc tip,
		@NotNull Color4F color,
		long expireTime) { }
}
