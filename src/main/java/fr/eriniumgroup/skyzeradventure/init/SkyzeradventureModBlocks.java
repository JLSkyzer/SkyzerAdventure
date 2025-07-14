/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.skyzeradventure.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;

import fr.eriniumgroup.skyzeradventure.block.ExponiaMeteoriteBlock;
import fr.eriniumgroup.skyzeradventure.block.EnergySellerBlockBlock;
import fr.eriniumgroup.skyzeradventure.block.CastleBossSpawnerBlock;
import fr.eriniumgroup.skyzeradventure.block.BasicLuckyBlockBlock;
import fr.eriniumgroup.skyzeradventure.block.AutoSellerBlock;
import fr.eriniumgroup.skyzeradventure.block.AdetiumOreBlock;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

public class SkyzeradventureModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, SkyzeradventureMod.MODID);
	public static final RegistryObject<Block> ADETIUM_ORE = REGISTRY.register("adetium_ore", () -> new AdetiumOreBlock());
	public static final RegistryObject<Block> ENERGY_SELLER_BLOCK = REGISTRY.register("energy_seller_block", () -> new EnergySellerBlockBlock());
	public static final RegistryObject<Block> CASTLE_BOSS_SPAWNER = REGISTRY.register("castle_boss_spawner", () -> new CastleBossSpawnerBlock());
	public static final RegistryObject<Block> BASIC_LUCKY_BLOCK = REGISTRY.register("basic_lucky_block", () -> new BasicLuckyBlockBlock());
	public static final RegistryObject<Block> AUTO_SELLER = REGISTRY.register("auto_seller", () -> new AutoSellerBlock());
	public static final RegistryObject<Block> EXPONIA_METEORITE = REGISTRY.register("exponia_meteorite", () -> new ExponiaMeteoriteBlock());

	// Start of user code block custom blocks
	// End of user code block custom blocks
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class BlocksClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			ExponiaMeteoriteBlock.registerRenderLayer();
		}
	}
}
