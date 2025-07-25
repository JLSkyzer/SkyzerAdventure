/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.skyzeradventure.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import fr.eriniumgroup.skyzeradventure.client.gui.StatsScaleConfigScreen;
import fr.eriniumgroup.skyzeradventure.client.gui.ShopSellScreen;
import fr.eriniumgroup.skyzeradventure.client.gui.ShopMainGuiScreen;
import fr.eriniumgroup.skyzeradventure.client.gui.ShopBuyScreen;
import fr.eriniumgroup.skyzeradventure.client.gui.SellingPageScreen;
import fr.eriniumgroup.skyzeradventure.client.gui.EnergySellerGuiScreen;
import fr.eriniumgroup.skyzeradventure.client.gui.EarningWikiScreen;
import fr.eriniumgroup.skyzeradventure.client.gui.EarningWikiHomePageScreen;
import fr.eriniumgroup.skyzeradventure.client.gui.ConfiguratorScreen;
import fr.eriniumgroup.skyzeradventure.client.gui.BuyPageScreen;
import fr.eriniumgroup.skyzeradventure.client.gui.AutoSellerGuiScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SkyzeradventureModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(SkyzeradventureModMenus.CONFIGURATOR.get(), ConfiguratorScreen::new);
			MenuScreens.register(SkyzeradventureModMenus.STATS_SCALE_CONFIG.get(), StatsScaleConfigScreen::new);
			MenuScreens.register(SkyzeradventureModMenus.EARNING_WIKI.get(), EarningWikiScreen::new);
			MenuScreens.register(SkyzeradventureModMenus.EARNING_WIKI_HOME_PAGE.get(), EarningWikiHomePageScreen::new);
			MenuScreens.register(SkyzeradventureModMenus.SHOP_MAIN_GUI.get(), ShopMainGuiScreen::new);
			MenuScreens.register(SkyzeradventureModMenus.SHOP_BUY.get(), ShopBuyScreen::new);
			MenuScreens.register(SkyzeradventureModMenus.SHOP_SELL.get(), ShopSellScreen::new);
			MenuScreens.register(SkyzeradventureModMenus.SELLING_PAGE.get(), SellingPageScreen::new);
			MenuScreens.register(SkyzeradventureModMenus.BUY_PAGE.get(), BuyPageScreen::new);
			MenuScreens.register(SkyzeradventureModMenus.ENERGY_SELLER_GUI.get(), EnergySellerGuiScreen::new);
			MenuScreens.register(SkyzeradventureModMenus.AUTO_SELLER_GUI.get(), AutoSellerGuiScreen::new);
		});
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}