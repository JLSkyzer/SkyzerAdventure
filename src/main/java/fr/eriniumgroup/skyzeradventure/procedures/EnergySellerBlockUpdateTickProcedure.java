package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraftforge.energy.CapabilityEnergy;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;

public class EnergySellerBlockUpdateTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (new Object() {
			public int getEnergyStored(LevelAccessor level, BlockPos pos) {
				AtomicInteger _retval = new AtomicInteger(0);
				BlockEntity _ent = level.getBlockEntity(pos);
				if (_ent != null)
					_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> _retval.set(capability.getEnergyStored()));
				return _retval.get();
			}
		}.getEnergyStored(world, new BlockPos(x, y, z)) > 0) {
			for (Entity entityiterator : new ArrayList<>(world.players())) {
				if ((entityiterator.getDisplayName().getString()).equals(new Object() {
					public String getValue(LevelAccessor world, BlockPos pos, String tag) {
						BlockEntity blockEntity = world.getBlockEntity(pos);
						if (blockEntity != null)
							return blockEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(world, new BlockPos(x, y, z), "target"))) {
					{
						double _setval = (entityiterator.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).shop_money + new Object() {
							public int getEnergyStored(LevelAccessor level, BlockPos pos) {
								AtomicInteger _retval = new AtomicInteger(0);
								BlockEntity _ent = level.getBlockEntity(pos);
								if (_ent != null)
									_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> _retval.set(capability.getEnergyStored()));
								return _retval.get();
							}
						}.getEnergyStored(world, new BlockPos(x, y, z)) * ReturnEnergyPriceProcedure.execute();
						entityiterator.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.shop_money = _setval;
							capability.syncPlayerVariables(entityiterator);
						});
					}
					{
						BlockEntity _ent = world.getBlockEntity(new BlockPos(x, y, z));
						int _amount = new Object() {
							public int getEnergyStored(LevelAccessor level, BlockPos pos) {
								AtomicInteger _retval = new AtomicInteger(0);
								BlockEntity _ent = level.getBlockEntity(pos);
								if (_ent != null)
									_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> _retval.set(capability.getEnergyStored()));
								return _retval.get();
							}
						}.getEnergyStored(world, new BlockPos(x, y, z));
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
					}
					break;
				}
			}
		}
	}
}
