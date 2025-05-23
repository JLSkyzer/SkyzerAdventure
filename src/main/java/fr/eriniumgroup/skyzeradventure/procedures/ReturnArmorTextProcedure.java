package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class ReturnArmorTextProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return (new java.text.DecimalFormat("#,###.##").format(entity instanceof LivingEntity _livEnt ? _livEnt.getArmorValue() : 0)) + " / 100";
	}
}
