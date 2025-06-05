
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.skyzeradventure.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import fr.eriniumgroup.skyzeradventure.client.gui.StatsScaleConfigScreen;
import fr.eriniumgroup.skyzeradventure.client.gui.EarningWikiScreen;
import fr.eriniumgroup.skyzeradventure.client.gui.ConfiguratorScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SkyzeradventureModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(SkyzeradventureModMenus.STATS_SCALE_CONFIG, StatsScaleConfigScreen::new);
			MenuScreens.register(SkyzeradventureModMenus.CONFIGURATOR, ConfiguratorScreen::new);
			MenuScreens.register(SkyzeradventureModMenus.EARNING_WIKI, EarningWikiScreen::new);
		});
	}
}
