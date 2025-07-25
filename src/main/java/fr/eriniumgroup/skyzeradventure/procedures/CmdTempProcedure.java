package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.commands.CommandSourceStack;

import java.util.HashMap;

import java.io.File;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class CmdTempProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		File file = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		java.util.Map<String, Double> hashmap = new HashMap<>();
		entity.setDeltaMovement(entity.getDeltaMovement().x, (DoubleArgumentType.getDouble(arguments, "temp")), entity.getDeltaMovement().z);
		entity.hasImpulse = true;
		entity.hurtMarked = true;
	}
}