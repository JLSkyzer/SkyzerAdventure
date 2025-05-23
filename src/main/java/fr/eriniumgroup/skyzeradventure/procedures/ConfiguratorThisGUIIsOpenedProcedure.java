package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraftforge.network.PacketDistributor;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

public class ConfiguratorThisGUIIsOpenedProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).overlayConfigTarget).equals("level")) {
			if (entity instanceof ServerPlayer _player && !world.isClientSide())
				SkyzeradventureMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> _player), new SkyzeradventureMod.TextboxSetMessage("xVal",
						(new java.text.DecimalFormat("##.##").format((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).LevelOverlayX))));
			if (entity instanceof ServerPlayer _player && !world.isClientSide())
				SkyzeradventureMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> _player), new SkyzeradventureMod.TextboxSetMessage("yVal",
						(new java.text.DecimalFormat("##.##").format((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).LevelOverlayY))));
		}
	}
}
