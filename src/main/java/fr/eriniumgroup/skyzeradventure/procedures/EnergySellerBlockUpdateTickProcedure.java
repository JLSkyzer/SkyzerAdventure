package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraftforge.energy.CapabilityEnergy;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;

public class EnergySellerBlockUpdateTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (getEnergyStored(world, new BlockPos(x, y, z), null) > 0) {
			for (Entity entityiterator : new ArrayList<>(world.players())) {
				if ((entityiterator.getDisplayName().getString()).equals(getBlockNBTString(world, new BlockPos(x, y, z), "target"))) {
					{
						double _setval = (entityiterator.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).shop_money
								+ getEnergyStored(world, new BlockPos(x, y, z), null) * ReturnEnergyPriceProcedure.execute();
						entityiterator.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.shop_money = _setval;
							capability.syncPlayerVariables(entityiterator);
						});
					}
					{
						BlockEntity _ent = world.getBlockEntity(new BlockPos(x, y, z));
						int _amount = getEnergyStored(world, new BlockPos(x, y, z), null);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
					}
					break;
				}
			}
		}
	}

	public static int getEnergyStored(LevelAccessor level, BlockPos pos, Direction direction) {
		AtomicInteger result = new AtomicInteger(0);
		BlockEntity entity = level.getBlockEntity(pos);
		if (entity != null)
			entity.getCapability(CapabilityEnergy.ENERGY, direction).ifPresent(capability -> result.set(capability.getEnergyStored()));
		return result.get();
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getTileData().getString(tag);
		return "";
	}
}
