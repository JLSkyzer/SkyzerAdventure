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
import net.minecraftforge.event.world.NoteBlockEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.Block;

import fr.eriniumgroup.skyzeradventure.block.ExponiaPortalFrameBlock;
import fr.eriniumgroup.skyzeradventure.block.ExponiaPortalBlock;
import fr.eriniumgroup.skyzeradventure.block.ExponiaMeteoriteBlock;
import fr.eriniumgroup.skyzeradventure.block.EnergySellerBlockBlock;
import fr.eriniumgroup.skyzeradventure.block.CastleBossSpawnerBlock;
import fr.eriniumgroup.skyzeradventure.block.BasicLuckyBlockBlock;
import fr.eriniumgroup.skyzeradventure.block.AutoSellerBlock;
import fr.eriniumgroup.skyzeradventure.block.AdetiumOreBlock;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

@Mod.EventBusSubscriber
public class SkyzeradventureModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, SkyzeradventureMod.MODID);
	public static final RegistryObject<Block> ADETIUM_ORE = REGISTRY.register("adetium_ore", AdetiumOreBlock::new);
	public static final RegistryObject<Block> ENERGY_SELLER_BLOCK = REGISTRY.register("energy_seller_block", EnergySellerBlockBlock::new);
	public static final RegistryObject<Block> CASTLE_BOSS_SPAWNER = REGISTRY.register("castle_boss_spawner", CastleBossSpawnerBlock::new);
	public static final RegistryObject<Block> BASIC_LUCKY_BLOCK = REGISTRY.register("basic_lucky_block", BasicLuckyBlockBlock::new);
	public static final RegistryObject<Block> AUTO_SELLER = REGISTRY.register("auto_seller", AutoSellerBlock::new);
	public static final RegistryObject<Block> EXPONIA_METEORITE = REGISTRY.register("exponia_meteorite", ExponiaMeteoriteBlock::new);
	public static final RegistryObject<Block> EXPONIA_PORTAL_FRAME = REGISTRY.register("exponia_portal_frame", ExponiaPortalFrameBlock::new);
	public static final RegistryObject<Block> EXPONIA_PORTAL = REGISTRY.register("exponia_portal", ExponiaPortalBlock::new);

	// Start of user code block custom blocks
	// End of user code block custom blocks
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class BlocksClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			ExponiaMeteoriteBlock.registerRenderLayer();
			ExponiaPortalBlock.registerRenderLayer();
		}
	}

	@SubscribeEvent
	public static void onNoteBlockPlay(NoteBlockEvent.Play event) {
		Block below = event.getWorld().getBlockState(event.getPos().below()).getBlock();
		if (below == SkyzeradventureModBlocks.ADETIUM_ORE.get()) {
			event.setInstrument(NoteBlockInstrument.BASEDRUM);
		}
	}
}