package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModMenus;

public class ConfiguratorThisGUIIsOpenedProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).overlayConfigTarget).equals("level")) {
			if (entity instanceof ServerPlayer _player && !world.isClientSide())
				SkyzeradventureModMenus.setText("xVal",
						(new java.text.DecimalFormat("##.##").format((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).LevelOverlayX)), _player);
			if (entity instanceof ServerPlayer _player && !world.isClientSide())
				SkyzeradventureModMenus.setText("yVal",
						(new java.text.DecimalFormat("##.##").format((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).LevelOverlayY)), _player);
		} else if (((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).overlayConfigTarget).equals("earning")) {
			if (entity instanceof ServerPlayer _player && !world.isClientSide())
				SkyzeradventureModMenus.setText("xVal",
						(new java.text.DecimalFormat("##.##").format((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).earningNotifX)), _player);
			if (entity instanceof ServerPlayer _player && !world.isClientSide())
				SkyzeradventureModMenus.setText("yVal",
						(new java.text.DecimalFormat("##.##").format((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).earningNotifY)), _player);
		}
	}
}
