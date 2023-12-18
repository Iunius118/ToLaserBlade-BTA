package com.github.iunius118.tolaserblade.client;

public class LaserBladeColor {
	public static final LaserBladeColor[] COLORS = new LaserBladeColor[]{
		new LaserBladeColor(0xFF999999, 0xFFFF0000, 0xFFFFFFFF, false, false, false),
		new LaserBladeColor(0xFF999999, 0xFFFF681F, 0xFFFFFFFF, false, false, false),
		new LaserBladeColor(0xFF999999, 0xFFFF00CC, 0xFFFFFFFF, false, false, false),
		new LaserBladeColor(0xFF999999, 0xFF0080FF, 0xFFFFFFFF, false, false, false),
		new LaserBladeColor(0xFF999999, 0xFFFFC400, 0xFFFFFFFF, false, false, false),
		new LaserBladeColor(0xFF999999, 0xFF00FF00, 0xFFFFFFFF, false, false, false),
		new LaserBladeColor(0xFF4B2D3C, 0xFFFF004C, 0xFFFADCF0, false, false, false),
		new LaserBladeColor(0xFFCCCCCC, 0xFFFFFFFF, 0xFFFFFFFF, false, false, true),
		new LaserBladeColor(0xFF666666, 0xFFFFFFFF, 0xFFFFFFFF, false, true, false),
		new LaserBladeColor(0xFF052328, 0xFF00FFFF, 0xFFFADCD7, false, false, true),
		new LaserBladeColor(0xFF333333, 0xFFFF00CC, 0xFFFFFFFF, false, false, true),
		new LaserBladeColor(0xFF999999, 0xFF0000FF, 0xFFFFFFFF, false, false, false),
		new LaserBladeColor(0xFF333333, 0xFFFF681F, 0xFFFFFFFF, false, false, true),
		new LaserBladeColor(0xFF333333, 0xFF00FF00, 0xFFFFFFFF, false, false, true),
		new LaserBladeColor(0xFF333333, 0xFFFF0000, 0xFFFFFFFF, false, false, true),
		new LaserBladeColor(0xFF333333, 0xFF0000FF, 0xFFFFFFFF, false, false, true)
	};

	public final Color4F gripColor;
	public final Color4F outerColor;
	public final Color4F innerColor;
	public final boolean isOutSubColor;
	public final boolean isMidSubColor;
	public final boolean isInSubColor;

	private LaserBladeColor(int gripColor, int outerColor, int innerColor, boolean isOutSubColor, boolean isMidSubColor, boolean isInSubColor) {
		this.gripColor = Color4F.of(gripColor);
		this.outerColor = Color4F.of(outerColor);
		this.innerColor = Color4F.of(innerColor);
		this.isOutSubColor = isOutSubColor;
		this.isMidSubColor = isMidSubColor;
		this.isInSubColor = isInSubColor;
	}
}
