package fr.eriniumgroup.skyzeradventure.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

import fr.eriniumgroup.skyzeradventure.procedures.WritedPaperRightclickedProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.WritedPaperItemInHandTickProcedure;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModTabs;

public class WritedPaperItem extends Item {
	public WritedPaperItem() {
		super(new Item.Properties().tab(SkyzeradventureModTabs.TAB_ITEMS_TAB).stacksTo(1).rarity(Rarity.EPIC));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		WritedPaperRightclickedProcedure.execute(entity, ar.getObject());
		return ar;
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		if (selected)
			WritedPaperItemInHandTickProcedure.execute(itemstack);
	}
}