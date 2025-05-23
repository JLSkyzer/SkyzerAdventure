package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class ReturnArmorValueProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		return Math.min(100, entity instanceof LivingEntity _livEnt ? _livEnt.getArmorValue() : 0) * 1.64;
	}
}
