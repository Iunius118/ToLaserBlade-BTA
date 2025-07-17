package com.github.iunius118.tolaserblade.client;

import com.google.common.collect.ImmutableList;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import java.util.List;

public class LBSwordModel {
	public static final List<SimpleQuad> HILT_QUADS;
	public static final List<SimpleQuad> BLADE_OUT_QUADS;
	public static final List<SimpleQuad> BLADE_MID_QUADS;
	public static final List<SimpleQuad> BLADE_IN_QUADS;
	public static final Vector4f Hilt_COLOR = new Vector4f(0.6F, 0.6F, 0.6F, 1F);
	public static final Vector4f BLADE_OUT_COLOR = new Vector4f(1F, 1F, 1F, 0.25F);
	public static final Vector4f BLADE_MID_COLOR = new Vector4f(1F, 1F, 1F, 0.5F);
	public static final Vector4f BLADE_IN_COLOR = new Vector4f(1F, 1F, 1F, 0.9F);

	static {
		Vector3f v0 = new Vector3f(-0.093750F, 0.375000F, 0.093750F);
		Vector3f v1 = new Vector3f(0.093750F, 0.375000F, 0.093750F);
		Vector3f v2 = new Vector3f(0.093750F, 0.312500F, 0.093750F);
		Vector3f v3 = new Vector3f(-0.093750F, 0.312500F, 0.093750F);
		Vector3f v4 = new Vector3f(0.093750F, 0.375000F, -0.093750F);
		Vector3f v5 = new Vector3f(0.093750F, 0.312500F, -0.093750F);
		Vector3f v6 = new Vector3f(-0.093750F, 0.375000F, -0.093750F);
		Vector3f v7 = new Vector3f(-0.093750F, 0.312500F, -0.093750F);
		Vector3f v8 = new Vector3f(0.031250F, 0.312500F, 0.031250F);
		Vector3f v9 = new Vector3f(0.031250F, 0.312500F, -0.031250F);
		Vector3f v10 = new Vector3f(0.031250F, 0.000000F, -0.031250F);
		Vector3f v11 = new Vector3f(0.031250F, 0.000000F, 0.031250F);
		Vector3f v12 = new Vector3f(-0.031250F, 0.312500F, -0.031250F);
		Vector3f v13 = new Vector3f(-0.031250F, 0.000000F, -0.031250F);
		Vector3f v14 = new Vector3f(-0.031250F, 0.312500F, 0.031250F);
		Vector3f v15 = new Vector3f(-0.031250F, 0.000000F, 0.031250F);
		Vector3f v16 = new Vector3f(0.093750F, 1.437500F, -0.093750F);
		Vector3f v17 = new Vector3f(-0.093750F, 1.437500F, -0.093750F);
		Vector3f v18 = new Vector3f(-0.093750F, 0.382812F, -0.093750F);
		Vector3f v19 = new Vector3f(0.093750F, 0.382812F, -0.093750F);
		Vector3f v20 = new Vector3f(-0.093750F, 1.437500F, 0.093750F);
		Vector3f v21 = new Vector3f(-0.093750F, 0.382812F, 0.093750F);
		Vector3f v22 = new Vector3f(0.093750F, 1.437500F, 0.093750F);
		Vector3f v23 = new Vector3f(0.093750F, 0.382812F, 0.093750F);
		Vector3f v24 = new Vector3f(-0.031250F, 0.375000F, 0.031250F);
		Vector3f v25 = new Vector3f(0.031250F, 0.375000F, 0.031250F);
		Vector3f v26 = new Vector3f(0.031250F, 0.375000F, -0.031250F);
		Vector3f v27 = new Vector3f(-0.031250F, 0.375000F, -0.031250F);
		Vector3f v28 = new Vector3f(0.062500F, 1.406250F, -0.062500F);
		Vector3f v29 = new Vector3f(-0.062500F, 1.406250F, -0.062500F);
		Vector3f v30 = new Vector3f(-0.062500F, 0.382812F, -0.062500F);
		Vector3f v31 = new Vector3f(0.062500F, 0.382812F, -0.062500F);
		Vector3f v32 = new Vector3f(-0.062500F, 1.406250F, 0.062500F);
		Vector3f v33 = new Vector3f(-0.062500F, 0.382812F, 0.062500F);
		Vector3f v34 = new Vector3f(0.062500F, 1.406250F, 0.062500F);
		Vector3f v35 = new Vector3f(0.062500F, 0.382812F, 0.062500F);
		Vector3f v36 = new Vector3f(-0.031250F, 1.375000F, 0.031250F);
		Vector3f v37 = new Vector3f(0.031250F, 1.375000F, 0.031250F);
		Vector3f v38 = new Vector3f(0.031250F, 1.375000F, -0.031250F);
		Vector3f v39 = new Vector3f(-0.031250F, 1.375000F, -0.031250F);
		Vector3f v40 = new Vector3f(0.00000F, 0.00000F, 1.00000F);
		Vector3f v41 = new Vector3f(1.00000F, 0.00000F, 0.00000F);
		Vector3f v42 = new Vector3f(0.00000F, 0.00000F, -1.00000F);
		Vector3f v43 = new Vector3f(-1.00000F, 0.00000F, 0.00000F);
		Vector3f v44 = new Vector3f(0.00000F, -1.00000F, 0.00000F);
		Vector3f v45 = new Vector3f(0.00000F, 1.00000F, 0.00000F);
		ImmutableList.Builder<SimpleQuad> builder;

		// Hilt
		builder = ImmutableList.builder();
		builder.add(new SimpleQuad(v3, v2, v1, v0, v40));
		builder.add(new SimpleQuad(v2, v5, v4, v1, v41));
		builder.add(new SimpleQuad(v5, v7, v6, v4, v42));
		builder.add(new SimpleQuad(v7, v3, v0, v6, v43));
		builder.add(new SimpleQuad(v7, v5, v2, v3, v44));
		builder.add(new SimpleQuad(v11, v10, v9, v8, v41));
		builder.add(new SimpleQuad(v10, v13, v12, v9, v42));
		builder.add(new SimpleQuad(v13, v15, v14, v12, v43));
		builder.add(new SimpleQuad(v13, v10, v11, v15, v44));
		builder.add(new SimpleQuad(v0, v1, v4, v6, v45));
		builder.add(new SimpleQuad(v15, v11, v8, v14, v40));
		builder.add(new SimpleQuad(v9, v12, v14, v8, v45));
		HILT_QUADS = builder.build();

		// BladeOut
		builder = ImmutableList.builder();
		builder.add(new SimpleQuad(v17, v18, v19, v16, v40));
		builder.add(new SimpleQuad(v20, v21, v18, v17, v41));
		builder.add(new SimpleQuad(v22, v23, v21, v20, v42));
		builder.add(new SimpleQuad(v16, v19, v23, v22, v43));
		builder.add(new SimpleQuad(v17, v16, v22, v20, v44));
		builder.add(new SimpleQuad(v21, v23, v25, v24, v45));
		builder.add(new SimpleQuad(v23, v19, v26, v25, v45));
		builder.add(new SimpleQuad(v19, v18, v27, v26, v45));
		builder.add(new SimpleQuad(v18, v21, v24, v27, v45));
		BLADE_OUT_QUADS = builder.build();

		// BladeMid
		builder = ImmutableList.builder();
		builder.add(new SimpleQuad(v29, v30, v31, v28, v40));
		builder.add(new SimpleQuad(v32, v33, v30, v29, v41));
		builder.add(new SimpleQuad(v34, v35, v33, v32, v42));
		builder.add(new SimpleQuad(v28, v31, v35, v34, v43));
		builder.add(new SimpleQuad(v29, v28, v34, v32, v44));
		builder.add(new SimpleQuad(v33, v35, v25, v24, v45));
		builder.add(new SimpleQuad(v35, v31, v26, v25, v45));
		builder.add(new SimpleQuad(v31, v30, v27, v26, v45));
		builder.add(new SimpleQuad(v30, v33, v24, v27, v45));
		BLADE_MID_QUADS = builder.build();

		// BladeIn
		builder = ImmutableList.builder();
		builder.add(new SimpleQuad(v24, v25, v37, v36, v40));
		builder.add(new SimpleQuad(v25, v26, v38, v37, v41));
		builder.add(new SimpleQuad(v26, v27, v39, v38, v42));
		builder.add(new SimpleQuad(v27, v24, v36, v39, v43));
		builder.add(new SimpleQuad(v37, v38, v39, v36, v45));
		BLADE_IN_QUADS = builder.build();
	}
}
