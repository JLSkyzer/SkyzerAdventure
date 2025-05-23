package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

public class ReturnFoodValueProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		return (entity instanceof Player _plr ? _plr.getFoodData().getFoodLevel() : 0) * 8.2;
	}
}
