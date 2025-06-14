
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.skyzeradventure.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import fr.eriniumgroup.skyzeradventure.block.entity.EnergySellerBlockBlockEntity;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

public class SkyzeradventureModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, SkyzeradventureMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> ENERGY_SELLER_BLOCK = register("energy_seller_block", SkyzeradventureModBlocks.ENERGY_SELLER_BLOCK, EnergySellerBlockBlockEntity::new);

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
