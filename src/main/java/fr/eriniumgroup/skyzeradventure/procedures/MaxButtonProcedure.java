package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModMenus;

public class MaxButtonProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && _player.containerMenu instanceof SkyzeradventureModMenus.MenuAccessor _menu)
			_menu.sendMenuStateUpdate(_player, 0, "amount",
					(new java.text.DecimalFormat("##").format(Math.floor((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).shop_money
							/ (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).tempitemprice))),
					true);
	}
}