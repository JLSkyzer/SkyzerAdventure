
package fr.eriniumgroup.skyzeradventure.block;

import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class BasicLuckyBlockBlock extends Block {
	public BasicLuckyBlockBlock() {
		super(BlockBehaviour.Properties.of((new Material.Builder(MaterialColor.NONE)).build()).sound(SoundType.METAL).strength(3f));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}
}
