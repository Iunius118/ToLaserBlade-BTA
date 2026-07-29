package com.github.iunius118.tolaserblade.common;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tool.ItemToolSword;
import org.jetbrains.annotations.NotNull;

public class ItemLBSword extends ItemToolSword {
	public static final ToolMaterial LASER_MATERIAL = new ToolMaterial()
		.setDurability(0)
		.setEfficiency(7.0F, 14.0F)
		.setMiningLevel(3)
		.setDamage(4)
		.setBlockHitDelay(4);

	public ItemLBSword(@NotNull String name, int id) {
		super(name, "%s:item/%s".formatted(ToLaserBlade.MOD_ID, name), id, LASER_MATERIAL);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public float getStrVsBlock(@NotNull ItemStack selfStack, @NotNull Block<?> block) {
		return LASER_MATERIAL.getEfficiency(false);
	}

	@Override
	public boolean canHarvestBlock(@NotNull ItemStack selfStack, @NotNull Mob mob, @NotNull Block<?> block) {
		return block.hasTag(BlockTags.MINEABLE_BY_SWORD)
			|| block.hasTag(BlockTags.MINEABLE_BY_PICKAXE)
			|| block.hasTag(BlockTags.MINEABLE_BY_AXE);
	}
}
