/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.skyzeradventure.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Block;

import fr.eriniumgroup.skyzeradventure.block.entity.EnergySellerBlockBlockEntity;
import fr.eriniumgroup.skyzeradventure.block.entity.AutoSellerBlockEntity;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

public class SkyzeradventureModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, SkyzeradventureMod.MODID);
	public static final RegistryObject<BlockEntityType<EnergySellerBlockBlockEntity>> ENERGY_SELLER_BLOCK = register("energy_seller_block", SkyzeradventureModBlocks.ENERGY_SELLER_BLOCK, EnergySellerBlockBlockEntity::new);
	public static final RegistryObject<BlockEntityType<AutoSellerBlockEntity>> AUTO_SELLER = register("auto_seller", SkyzeradventureModBlocks.AUTO_SELLER, AutoSellerBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<T> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}