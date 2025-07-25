package fr.eriniumgroup.skyzeradventure.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.LivingEntity;

import fr.eriniumgroup.skyzeradventure.procedures.RegenerationGumPlayerFinishesUsingItemProcedure;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModTabs;

public class RegenerationGumItem extends Item {
	public RegenerationGumItem() {
		super(new Item.Properties().tab(SkyzeradventureModTabs.TAB_ITEMS_TAB).food((new FoodProperties.Builder()).nutrition(1).saturationMod(0.3f).alwaysEat().build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		RegenerationGumPlayerFinishesUsingItemProcedure.execute(entity);
		return retval;
	}
}