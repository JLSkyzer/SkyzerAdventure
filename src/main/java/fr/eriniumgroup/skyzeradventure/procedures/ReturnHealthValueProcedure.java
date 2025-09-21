package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class ReturnHealthValueProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		return Math.min((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) * (164 / (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1)), 164);
	}
}