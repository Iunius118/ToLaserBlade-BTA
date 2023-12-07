package com.github.iunius118.tolaserblade.client;

import net.minecraft.client.render.Tessellator;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public class SimpleQuad {
	private static final int V1 = 0;
	private static final int V2 = 3;
	private static final int V3 = 6;
	private static final int V4 = 9;
	private static final int NORMAL = 12;
	private static final int X = 0;
	private static final int Y = 1;
	private static final int Z = 2;

	private final float[] v;

	public SimpleQuad(Vector3f v1, Vector3f v2, Vector3f v3, Vector3f v4, Vector3f normal) {
		v = new float[]{v1.x, v1.y, v1.z, v2.x, v2.y, v2.z, v3.x, v3.y, v3.z, v4.x, v4.y, v4.z, normal.x, normal.y, normal.z};
	}

	public void renderQuad(Tessellator tessellator) {
		GL11.glNormal3f(v[NORMAL + X], v[NORMAL + Y], v[NORMAL + Z]);
		tessellator.startDrawingQuads();
		tessellator.addVertex(v[V1 + X], v[V1 + Y], v[V1 + Z]);
		tessellator.addVertex(v[V2 + X], v[V2 + Y], v[V2 + Z]);
		tessellator.addVertex(v[V3 + X], v[V3 + Y], v[V3 + Z]);
		tessellator.addVertex(v[V4 + X], v[V4 + Y], v[V4 + Z]);
		tessellator.draw();
	}
}
