package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;

import java.util.Random;

import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModItems;

public class RandomXpBoostRightclickedProcedure {
	public static void execute(LevelAccessor world, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		ItemStack item = ItemStack.EMPTY;
		if (world.isClientSide()) {
			return;
		}
		if ((itemstack.getOrCreateTag().getString("type")).equals("epic")) {
			item = new ItemStack(SkyzeradventureModItems.XP_BOOST.get()).copy();
			item.getOrCreateTag().putDouble("boost", (Mth.nextDouble(new Random(), 1.5, 5)));
			if (entity instanceof Player _player) {
				ItemStack _setstack = item.copy();
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = itemstack;
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
			}
		} else if ((itemstack.getOrCreateTag().getString("type")).equals("legendary")) {
			item = new ItemStack(SkyzeradventureModItems.XP_BOOST.get()).copy();
			item.getOrCreateTag().putDouble("boost", (Mth.nextDouble(new Random(), 5.01, 7.5)));
			if (entity instanceof Player _player) {
				ItemStack _setstack = item.copy();
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = itemstack;
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
			}
		} else {
			item = new ItemStack(SkyzeradventureModItems.XP_BOOST.get()).copy();
			item.getOrCreateTag().putDouble("boost", (Mth.nextDouble(new Random(), 7.51, 12)));
			if (entity instanceof Player _player) {
				ItemStack _setstack = item.copy();
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = itemstack;
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
			}
		}
	}
}