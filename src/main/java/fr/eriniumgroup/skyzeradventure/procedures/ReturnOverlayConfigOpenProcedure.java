package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import fr.eriniumgroup.skyzeradventure.world.inventory.ConfiguratorMenu;
import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;

public class ReturnOverlayConfigOpenProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if (entity instanceof Player _plr ? _plr.containerMenu instanceof ConfiguratorMenu : false) {
			if (((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).overlayConfigTarget).equals("earning")) {
				{
					double _setval = 60;
					entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.earningNotifTick = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					String _setval = "Text 1";
					entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.earning_text1 = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					String _setval = "Text 2";
					entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.earning_text2 = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					String _setval = "Text 3";
					entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.earning_text3 = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
		return entity instanceof Player _plr ? _plr.containerMenu instanceof ConfiguratorMenu : false;
	}
}
