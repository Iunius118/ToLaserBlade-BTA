package com.github.iunius118.tolaserblade;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tool.ItemToolSword;

public class ItemLBSword extends ItemToolSword {
	private ToolMaterial material;

	public ItemLBSword(String name, int id, ToolMaterial enumtoolmaterial) {
		super(name, id, enumtoolmaterial);

		material = enumtoolmaterial;
		this.setMaxDamage(0);
	}

	@Override
	public float getStrVsBlock(ItemStack itemstack, Block block) {
		return material.getEfficiency(false);
	}

	@Override
	public boolean canHarvestBlock(Block block) {
		return block.hasTag(BlockTags.MINEABLE_BY_SWORD) || block.hasTag(BlockTags.MINEABLE_BY_PICKAXE) || block.hasTag(BlockTags.MINEABLE_BY_AXE);
	}
}
