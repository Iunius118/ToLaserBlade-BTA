package com.github.iunius118.tolaserblade.common;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tool.ItemToolSword;

public class ItemLBSword extends ItemToolSword {
	public static final ToolMaterial LASER_MATERIAL = new ToolMaterial()
		.setDurability(0)
		.setEfficiency(7.0F, 14.0F)
		.setMiningLevel(3)
		.setDamage(4)
		.setBlockHitDelay(4);

	public ItemLBSword(String name, String namespaceId, int id) {
		super(name, namespaceId, id, LASER_MATERIAL);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public float getStrVsBlock(ItemStack itemstack, Block block) {
		return LASER_MATERIAL.getEfficiency(false);
	}

	@Override
	public boolean canHarvestBlock(Mob mob, ItemStack itemStack, Block<?> block) {
		return block.hasTag(BlockTags.MINEABLE_BY_SWORD) || block.hasTag(BlockTags.MINEABLE_BY_PICKAXE) || block.hasTag(BlockTags.MINEABLE_BY_AXE);
	}
}
