package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;

public class ReturnScaleValueProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "" + new java.text.DecimalFormat("##.##").format((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).stats_scale);
	}
}
