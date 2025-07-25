/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.skyzeradventure.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.levelgen.feature.StructureFeature;

import fr.eriniumgroup.skyzeradventure.world.structures.Cave1StructureStructure;
import fr.eriniumgroup.skyzeradventure.world.structures.BrokenCastleStructure;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

public class SkyzeradventureModStructures {
	public static final DeferredRegister<StructureFeature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, SkyzeradventureMod.MODID);
	public static final RegistryObject<StructureFeature<?>> BROKEN_CASTLE = REGISTRY.register("broken_castle", () -> new BrokenCastleStructure());
	public static final RegistryObject<StructureFeature<?>> CAVE_1_STRUCTURE = REGISTRY.register("cave_1_structure", () -> new Cave1StructureStructure());
}