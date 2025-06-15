/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.skyzeradventure.init;

import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.GameRules;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SkyzeradventureModGameRules {
	public static final GameRules.Key<GameRules.BooleanValue> RPG_MODE_GAMERULE = GameRules.register("rPGModeGamerule", GameRules.Category.MISC, GameRules.BooleanValue.create(true));
}
