package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.TextComponent;

public class RandomXpBoostTickProcedure {
	public static void execute(ItemStack itemstack) {
		(itemstack).setHoverName(new TextComponent(("Random XP Boost \u00A7a[" + itemstack.getOrCreateTag().getString("type") + "]")));
	}
}