package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

public class HaveItemProcedure {
	public static boolean execute(Entity entity, ItemStack item) {
		if (entity == null)
			return false;
		return entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(item) : false;
	}
}
