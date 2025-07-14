package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.commands.CommandSourceStack;

import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.core.Registry;

import java.util.Objects;

public class ItemOutputProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments) {

		// Exemple : dans n'importe quelle méthode côté client/server
		WriteOutputProcedure.execute("");
		WriteOutputProcedure.execute("===== START ITEM OUTPUT =====");
		WriteOutputProcedure.execute("");
		Registry.ITEM.stream().forEach(item -> {
			if (Objects.requireNonNull(item.getRegistryName()).toString().contains(StringArgumentType.getString(arguments, "containID"))) {
				WriteOutputProcedure.execute(Objects.requireNonNull(item.getRegistryName()).toString());
			}
		});
		WriteOutputProcedure.execute("");
		WriteOutputProcedure.execute("===== FINISH ITEM OUTPUT =====");
		System.out.println("FINISH");
	}
}
