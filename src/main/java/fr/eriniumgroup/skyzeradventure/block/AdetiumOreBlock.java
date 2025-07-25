package fr.eriniumgroup.skyzeradventure.block;

import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class AdetiumOreBlock extends Block {
	public AdetiumOreBlock() {
		super(BlockBehaviour.Properties.of((new Material.Builder(MaterialColor.NONE)).build()).strength(1f));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}
}