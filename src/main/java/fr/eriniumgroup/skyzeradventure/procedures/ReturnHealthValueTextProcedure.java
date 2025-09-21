package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class ReturnHealthValueTextProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		if ((entity instanceof Player _plr ? _plr.getAbsorptionAmount() : 0) > 0) {
			return (new java.text.DecimalFormat("#,###.##").format(entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1)) + " / "
					+ (new java.text.DecimalFormat("#,###.##").format(entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1)) + " \u00A7e(+ "
					+ (new java.text.DecimalFormat("#,###.##").format(entity instanceof Player _plr ? _plr.getAbsorptionAmount() : 0)) + ")";
		}
		return (new java.text.DecimalFormat("#,###.##").format(entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1)) + " / "
				+ (new java.text.DecimalFormat("#,###.##").format(entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1));
	}
}