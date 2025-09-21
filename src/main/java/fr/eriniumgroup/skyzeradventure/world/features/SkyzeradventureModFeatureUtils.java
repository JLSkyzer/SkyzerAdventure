package fr.eriniumgroup.skyzeradventure.world.features;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;

public final class SkyzeradventureModFeatureUtils {
	protected static BlockState addProperty(BlockState block, String propr, boolean bool) {
		if (block.getBlock().getStateDefinition().getProperty(propr) instanceof BooleanProperty _booleanProp)
			return block.setValue(_booleanProp, bool);
		return block;
	}

	protected static BlockState addProperty(BlockState block, String propr, int num) {
		if (block.getBlock().getStateDefinition().getProperty(propr) instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(num))
			return block.setValue(_integerProp, num);
		return block;
	}

	protected static BlockState addProperty(BlockState block, String propr, String str) {
		if (block.getBlock().getStateDefinition().getProperty(propr) instanceof EnumProperty _enumProp && _enumProp.getValue(str).isPresent())
			return block.setValue(_enumProp, (Enum) _enumProp.getValue(str).get());
		return block;
	}
}