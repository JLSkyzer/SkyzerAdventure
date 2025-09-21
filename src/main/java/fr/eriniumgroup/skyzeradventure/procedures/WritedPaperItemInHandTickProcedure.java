package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.TextComponent;

import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModItems;

public class WritedPaperItemInHandTickProcedure {
	public static void execute(ItemStack itemstack) {
		itemstack.setHoverName(new TextComponent((new ItemStack(SkyzeradventureModItems.WRITED_PAPER.get()).getDisplayName().getString() + " \u00A7a" + itemstack.getOrCreateTag().getString("id"))));
	}
}