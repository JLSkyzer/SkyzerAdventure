package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.NonNullList;

import java.util.List;

public class HaveItemProcedure {
	public static boolean execute(Entity entity, ItemStack item) {
		if (entity == null)
			return false;
		return hasEntityInInventory(entity, item);
	}

	private static boolean hasEntityInInventory(Entity entity, ItemStack itemstack) {
		if (entity instanceof Player player) {
			Inventory inventory = player.getInventory();
			List<NonNullList<ItemStack>> compartments = com.google.common.collect.ImmutableList.of(inventory.items, inventory.armor, inventory.offhand);
			for (List<ItemStack> list : compartments) {
				for (ItemStack itemstack2 : list) {
					if (itemstack.sameItem(itemstack2)) {
						return true;
					}
				}
			}
		}
		return false;
	}
}