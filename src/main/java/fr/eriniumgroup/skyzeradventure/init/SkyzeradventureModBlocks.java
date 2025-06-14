
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.skyzeradventure.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import fr.eriniumgroup.skyzeradventure.block.EnergySellerBlockBlock;
import fr.eriniumgroup.skyzeradventure.block.AdetiumOreBlock;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

public class SkyzeradventureModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, SkyzeradventureMod.MODID);
	public static final RegistryObject<Block> ADETIUM_ORE = REGISTRY.register("adetium_ore", () -> new AdetiumOreBlock());
	public static final RegistryObject<Block> ENERGY_SELLER_BLOCK = REGISTRY.register("energy_seller_block", () -> new EnergySellerBlockBlock());
}
