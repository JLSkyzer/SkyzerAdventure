package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

public class ReturnXpTextProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "" + (new java.text.DecimalFormat("##").format(entity instanceof Player _plr ? _plr.experienceLevel : 0));
	}
}
