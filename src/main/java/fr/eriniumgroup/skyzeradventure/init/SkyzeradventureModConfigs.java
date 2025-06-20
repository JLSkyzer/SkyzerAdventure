package fr.eriniumgroup.skyzeradventure.init;

import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import fr.eriniumgroup.skyzeradventure.configuration.ShopListingConfiguration;
import fr.eriniumgroup.skyzeradventure.configuration.ShopConfigConfiguration;
import fr.eriniumgroup.skyzeradventure.configuration.MiscConfiguration;
import fr.eriniumgroup.skyzeradventure.configuration.EarningConfiguration;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

@Mod.EventBusSubscriber(modid = SkyzeradventureMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SkyzeradventureModConfigs {
	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		event.enqueueWork(() -> {
			ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EarningConfiguration.SPEC, "SkyzerAdventure/Rank/Earning.toml");
			ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MiscConfiguration.SPEC, "SkyzerAdventure/Rank/Misc.toml");
			ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ShopConfigConfiguration.SPEC, "SkyzerAdventure/Shop/ShopConfig.toml");
			ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ShopListingConfiguration.SPEC, "SkyzerAdventure/Shop/ShopListing.toml");
		});
	}
}
