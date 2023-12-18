package com.github.iunius118.tolaserblade.client;

public class Color4F {
	public final float a;
	public final float r;
	public final float g;
	public final float b;

	public Color4F(float alpha, float red, float green, float blue) {
		a = alpha;
		r = red;
		g = green;
		b = blue;
	}

	public static Color4F of(int color) {
		float fb = (float) (color & 0xFF) / 0xFF;
		float fg = (float) ((color >>> 8) & 0xFF) / 0xFF;
		float fr = (float) ((color >>> 16) & 0xFF) / 0xFF;
		float fa = (float) ((color >>> 24) & 0xFF) / 0xFF;
		return new Color4F(fa, fr, fg, fb);
	}
}
