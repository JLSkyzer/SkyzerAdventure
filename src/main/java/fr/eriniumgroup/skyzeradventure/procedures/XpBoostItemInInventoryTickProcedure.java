package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.TextComponent;

public class XpBoostItemInInventoryTickProcedure {
	public static void execute(ItemStack itemstack) {
		itemstack.setHoverName(new TextComponent(("XP Boost \u00A7a[" + new java.text.DecimalFormat("##.##").format(itemstack.getOrCreateTag().getDouble("boost")) + "%]")));
	}
}