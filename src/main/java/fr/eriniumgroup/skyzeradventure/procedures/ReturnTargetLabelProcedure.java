package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class ReturnTargetLabelProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		return "Player targeted : " + (new Object() {
			public String getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(world, new BlockPos(x, y, z), "target"));
	}
}
