
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.skyzeradventure.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import fr.eriniumgroup.skyzeradventure.potion.PlayerXpMultiplierMobEffect;
import fr.eriniumgroup.skyzeradventure.potion.DamageofdeathMobEffect;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

public class SkyzeradventureModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, SkyzeradventureMod.MODID);
	public static final RegistryObject<MobEffect> PLAYER_XP_MULTIPLIER = REGISTRY.register("player_xp_multiplier", () -> new PlayerXpMultiplierMobEffect());
	public static final RegistryObject<MobEffect> DAMAGEOFDEATH = REGISTRY.register("damageofdeath", () -> new DamageofdeathMobEffect());
}
