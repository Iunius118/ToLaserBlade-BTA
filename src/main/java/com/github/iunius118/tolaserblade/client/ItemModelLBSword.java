package com.github.iunius118.tolaserblade.client;

import com.github.iunius118.tolaserblade.common.ToLaserBlade;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.useless.dragonfly.DisplayPos;

public class ItemModelLBSword extends ItemModelStandard {
	public static final DisplayPos FIRST_PERSON_RIGHT_HAND =
		new DisplayPos(-0.0125F, -0.1875F, 0F, 20.0F, 0F, 0F, 0.544F, 0.775F, 0.544F);
	public static final DisplayPos FIRST_PERSON_LEFT_HAND =
		new DisplayPos(0.0125F, -0.1875F, 0F, 20.0F, 0F, 0F, 0.544F, 0.775F, 0.544F);
	public static final DisplayPos THIRD_PERSON_RIGHT_HAND =
		new DisplayPos(0F, -0.315F, 0.125F, -8.0F, 0F, 0F, 1.1F, 1.1F, 1.1F);
	public static final DisplayPos THIRD_PERSON_LEFT_HAND =
		new DisplayPos(0F, -0.315F, 0.125F, -8.0F, 0F, 0F, 1.1F, 1.1F, 1.1F);

	public static IconCoordinate[] lbSwordIcons = new IconCoordinate[16];

	public ItemModelLBSword(@NotNull Item item) {
		super(item, false);
		this.setDisplayPos("firstperson_righthand", FIRST_PERSON_RIGHT_HAND);
		this.setDisplayPos("firstperson_lefthand", FIRST_PERSON_LEFT_HAND);
		this.setDisplayPos("thirdperson_righthand", THIRD_PERSON_RIGHT_HAND);
		this.setDisplayPos("thirdperson_lefthand", THIRD_PERSON_LEFT_HAND);
	}

	@Override
	public void render(@NotNull TessellatorGeneral tessellator, @Nullable Entity holder, @NotNull ItemStack itemStack,
					   @NotNull String displayPosId, boolean items3d, int clusterSize, byte lightIndex,
					   float partialTick, boolean leftHanded) {
		if (shouldRenderOriginalModel(displayPosId)) {
			LBSwordRenderer.doRender(tessellator, holder, itemStack, this.getDisplayPos(displayPosId), lightIndex);
		} else {
			super.render(tessellator, holder, itemStack, displayPosId, items3d, clusterSize, lightIndex, partialTick,
				leftHanded);
		}
	}

	private boolean shouldRenderOriginalModel(@NotNull String displayPosId) {
		return DisplayPos.FIRST_PERSON_RIGHT_HAND.equals(displayPosId)
			|| DisplayPos.FIRST_PERSON_LEFT_HAND.equals(displayPosId)
			|| DisplayPos.THIRD_PERSON_RIGHT_HAND.equals(displayPosId)
			|| DisplayPos.THIRD_PERSON_LEFT_HAND.equals(displayPosId);
	}

	@Override
	public @NotNull IconCoordinate getIcon(@Nullable Entity entity, @NotNull ItemStack itemStack) {
		int meta = itemStack.getMetadata();
		return lbSwordIcons[meta & 15];
	}

	static {
		// Register item icons
		for (int i = 0; i < lbSwordIcons.length; i++) {
			String texture = "%s:item/laser_blade_%d".formatted(ToLaserBlade.MOD_ID, i);
			lbSwordIcons[i] = TextureRegistry.getTexture(texture);
		}
	}
}
