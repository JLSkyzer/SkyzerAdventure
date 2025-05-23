package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;

import fr.eriniumgroup.skyzeradventure.configuration.MiscConfiguration;

public class HaveStaffPermProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if (MiscConfiguration.STAFFPERM.get().contains(entity.getDisplayName().getString())) {
			return true;
		}
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(new TextComponent("\u00A7cSorry ! but you dont have permission to perform this action "), false);
		return false;
	}
}
