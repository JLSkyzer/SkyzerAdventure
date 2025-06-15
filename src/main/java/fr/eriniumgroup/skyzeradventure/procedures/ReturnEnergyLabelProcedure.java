package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraftforge.energy.CapabilityEnergy;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import java.util.concurrent.atomic.AtomicInteger;

public class ReturnEnergyLabelProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		return "\u00A7aEnergy : " + getEnergyStored(world, new BlockPos(x, y, z), null) + " / " + getMaxEnergyStored(world, new BlockPos(x, y, z), null);
	}

	public static int getEnergyStored(LevelAccessor level, BlockPos pos, Direction direction) {
		AtomicInteger result = new AtomicInteger(0);
		BlockEntity entity = level.getBlockEntity(pos);
		if (entity != null)
			entity.getCapability(CapabilityEnergy.ENERGY, direction).ifPresent(capability -> result.set(capability.getEnergyStored()));
		return result.get();
	}

	public static int getMaxEnergyStored(LevelAccessor level, BlockPos pos, Direction direction) {
		AtomicInteger result = new AtomicInteger(0);
		BlockEntity entity = level.getBlockEntity(pos);
		if (entity != null)
			entity.getCapability(CapabilityEnergy.ENERGY, direction).ifPresent(capability -> result.set(capability.getMaxEnergyStored()));
		return result.get();
	}
}
