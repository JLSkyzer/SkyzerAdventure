package fr.eriniumgroup.skyzeradventure.item;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.chat.Component;

import java.util.List;

import fr.eriniumgroup.skyzeradventure.procedures.XpBoostPlayerFinishesUsingItemProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.XpBoostItemInInventoryTickProcedure;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModTabs;

public class XpBoostItem extends Item {
	public XpBoostItem() {
		super(new Item.Properties().tab(SkyzeradventureModTabs.TAB_ITEMS_TAB).stacksTo(1).food((new FoodProperties.Builder()).nutrition(12).saturationMod(15f).alwaysEat().build()));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.DRINK;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean isFoil(ItemStack itemstack) {
		return true;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(new TranslatableComponent("item.skyzeradventure.xp_boost.description_0"));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		XpBoostPlayerFinishesUsingItemProcedure.execute(entity, itemstack);
		return retval;
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		XpBoostItemInInventoryTickProcedure.execute(itemstack);
	}
}