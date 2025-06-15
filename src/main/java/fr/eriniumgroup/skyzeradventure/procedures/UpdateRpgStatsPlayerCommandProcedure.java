package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

public class UpdateRpgStatsPlayerCommandProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments) {
		{
			double _setval = Math.max(20,
					20 + 0.0588 * Math.pow(((commandParameterEntity(arguments, "player")).getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).RPGLevel, 1.705));
			(commandParameterEntity(arguments, "player")).getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.RPGHealthMax = _setval;
				capability.syncPlayerVariables((commandParameterEntity(arguments, "player")));
			});
		}
		{
			double _setval = 500 + 800 * 20 * 1.503 * (((commandParameterEntity(arguments, "player")).getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).RPGLevel
					+ 10 * Math.pow(((commandParameterEntity(arguments, "player")).getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).RPGLevel, 2));
			(commandParameterEntity(arguments, "player")).getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.RPGMaxXp = _setval;
				capability.syncPlayerVariables((commandParameterEntity(arguments, "player")));
			});
		}
		if ((commandParameterEntity(arguments, "player")) instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(new TextComponent("\u00A7aRPG stats updated"), false);
	}

	private static Entity commandParameterEntity(CommandContext<CommandSourceStack> arguments, String parameter) {
		try {
			return EntityArgument.getEntity(arguments, parameter);
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}
}
