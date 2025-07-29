package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;

import java.util.HashMap;

import java.io.File;

public class CmdTempProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		File file = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		java.util.Map<String, Double> hashmap = new HashMap<>();
		double index = 0;
		java.util.List<Object> object = new java.util.ArrayList<>();
		for (int index0 = 0; index0 < 11; index0++) {
			object = GetSlotPositionProcedure.execute(index, 5000);
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent(("Slot" + index + " (" + object.get(0) + ", " + 120 + ", " + object.get(1) + ")")), false);
			index = index + 1;
		}
	}
}