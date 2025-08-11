package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import java.util.HashMap;

import java.io.File;

import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModItems;

public class CmdTempProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		File file = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		java.util.Map<String, Double> hashmap = new HashMap<>();
		double index = 0;
		java.util.List<Object> object = new java.util.ArrayList<>();
		ItemStack item = ItemStack.EMPTY;
		String temp = "";
		item = new ItemStack(SkyzeradventureModItems.RANDOM_XP_BOOST.get()).copy();
		item.getOrCreateTag().putString("type", "mythic");
		if (entity instanceof Player _player) {
			ItemStack _setstack = item.copy();
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
		item = new ItemStack(SkyzeradventureModItems.RANDOM_XP_BOOST.get()).copy();
		item.getOrCreateTag().putString("type", "legendary");
		if (entity instanceof Player _player) {
			ItemStack _setstack = item.copy();
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
		item = new ItemStack(SkyzeradventureModItems.RANDOM_XP_BOOST.get()).copy();
		item.getOrCreateTag().putString("type", "epic");
		if (entity instanceof Player _player) {
			ItemStack _setstack = item.copy();
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
	}
}