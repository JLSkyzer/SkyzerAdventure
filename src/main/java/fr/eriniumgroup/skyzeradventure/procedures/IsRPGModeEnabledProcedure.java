package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.level.LevelAccessor;

import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModGameRules;

public class IsRPGModeEnabledProcedure {
	public static boolean execute(LevelAccessor world) {
		double FinalDamage = 0;
		double damageReduction = 0;
		return world.getLevelData().getGameRules().getBoolean(SkyzeradventureModGameRules.RPG_MODE_GAMERULE);
	}
}
