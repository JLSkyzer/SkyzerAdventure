
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.skyzeradventure.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.enchantment.Enchantment;

import fr.eriniumgroup.skyzeradventure.enchantment.HealthRegenEnchantment;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

public class SkyzeradventureModEnchantments {
	public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, SkyzeradventureMod.MODID);
	public static final RegistryObject<Enchantment> HEALTH_REGEN = REGISTRY.register("health_regen", () -> new HealthRegenEnchantment());
}
