package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class SetLevelCmdProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		if (HaveStaffPermProcedure.execute(entity)) {
			{
				double _setval = DoubleArgumentType.getDouble(arguments, "level");
				(commandParameterEntity(arguments, "player")).getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.RPGLevel = _setval;
					capability.syncPlayerVariables((commandParameterEntity(arguments, "player")));
				});
			}
			{
				double _setval = 0;
				(commandParameterEntity(arguments, "player")).getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.RPGXp = _setval;
					capability.syncPlayerVariables((commandParameterEntity(arguments, "player")));
				});
			}
			UpdateRpgStatsPlayerCommandProcedure.execute(arguments);
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("\u00A7aDone !"), false);
		}
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
