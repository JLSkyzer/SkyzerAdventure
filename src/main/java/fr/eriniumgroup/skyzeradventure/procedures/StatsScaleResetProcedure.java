package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;

public class StatsScaleResetProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			double _setval = 1;
			entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.stats_scale = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
