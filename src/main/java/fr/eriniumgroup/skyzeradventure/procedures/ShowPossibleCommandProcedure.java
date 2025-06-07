package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;

public class ShowPossibleCommandProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(new TextComponent("\u00A75=====[ Level's command ]====="), false);
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(new TextComponent("\u00A7e- /level SetLevel \u00A7r| To set level of a player \u00A74Require staff permissions"), false);
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(new TextComponent("\u00A7e- /level Reset \u00A7r| To reset level of a player \u00A74Require staff permissions"), false);
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(new TextComponent("\u00A7e- /level Earning \u00A7r| To open earning wiki page to know how to earn xp"), false);
	}
}
