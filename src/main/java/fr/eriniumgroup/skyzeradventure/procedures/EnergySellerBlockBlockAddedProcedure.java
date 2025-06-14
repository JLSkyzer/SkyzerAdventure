package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraftforge.energy.CapabilityEnergy;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.core.BlockPos;

public class EnergySellerBlockBlockAddedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!world.isClientSide()) {
			BlockPos _bp = new BlockPos(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null)
				_blockEntity.getTileData().putString("target", (entity.getDisplayName().getString()));
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(new TextComponent("Block is set to you name !"), false);
		{
			BlockEntity _ent = world.getBlockEntity(new BlockPos(x, y, z));
			int _amount = 200000;
			if (_ent != null)
				_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.receiveEnergy(_amount, false));
		}
	}
}
