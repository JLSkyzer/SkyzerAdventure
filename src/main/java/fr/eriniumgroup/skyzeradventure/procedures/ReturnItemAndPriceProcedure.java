package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;

public class ReturnItemAndPriceProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return ((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).tempitem).getDisplayName().getString() + " \u00A79Price : \u00A7a"
				+ new java.text.DecimalFormat("#,###.####").format((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).tempitemprice) + "$";
	}
}
