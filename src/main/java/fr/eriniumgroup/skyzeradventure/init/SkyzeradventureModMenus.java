
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.skyzeradventure.init;

import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.AbstractContainerMenu;

import java.util.List;
import java.util.ArrayList;

import fr.eriniumgroup.skyzeradventure.world.inventory.StatsScaleConfigMenu;
import fr.eriniumgroup.skyzeradventure.world.inventory.ShopSellMenu;
import fr.eriniumgroup.skyzeradventure.world.inventory.ShopMainGuiMenu;
import fr.eriniumgroup.skyzeradventure.world.inventory.ShopBuyMenu;
import fr.eriniumgroup.skyzeradventure.world.inventory.SellingPageMenu;
import fr.eriniumgroup.skyzeradventure.world.inventory.EnergySellerGuiMenu;
import fr.eriniumgroup.skyzeradventure.world.inventory.EarningWikiMenu;
import fr.eriniumgroup.skyzeradventure.world.inventory.EarningWikiHomePageMenu;
import fr.eriniumgroup.skyzeradventure.world.inventory.ConfiguratorMenu;
import fr.eriniumgroup.skyzeradventure.world.inventory.BuyPageMenu;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SkyzeradventureModMenus {
	private static final List<MenuType<?>> REGISTRY = new ArrayList<>();
	public static final MenuType<StatsScaleConfigMenu> STATS_SCALE_CONFIG = register("stats_scale_config", (id, inv, extraData) -> new StatsScaleConfigMenu(id, inv, extraData));
	public static final MenuType<ConfiguratorMenu> CONFIGURATOR = register("configurator", (id, inv, extraData) -> new ConfiguratorMenu(id, inv, extraData));
	public static final MenuType<EarningWikiMenu> EARNING_WIKI = register("earning_wiki", (id, inv, extraData) -> new EarningWikiMenu(id, inv, extraData));
	public static final MenuType<EarningWikiHomePageMenu> EARNING_WIKI_HOME_PAGE = register("earning_wiki_home_page", (id, inv, extraData) -> new EarningWikiHomePageMenu(id, inv, extraData));
	public static final MenuType<ShopMainGuiMenu> SHOP_MAIN_GUI = register("shop_main_gui", (id, inv, extraData) -> new ShopMainGuiMenu(id, inv, extraData));
	public static final MenuType<ShopBuyMenu> SHOP_BUY = register("shop_buy", (id, inv, extraData) -> new ShopBuyMenu(id, inv, extraData));
	public static final MenuType<ShopSellMenu> SHOP_SELL = register("shop_sell", (id, inv, extraData) -> new ShopSellMenu(id, inv, extraData));
	public static final MenuType<SellingPageMenu> SELLING_PAGE = register("selling_page", (id, inv, extraData) -> new SellingPageMenu(id, inv, extraData));
	public static final MenuType<BuyPageMenu> BUY_PAGE = register("buy_page", (id, inv, extraData) -> new BuyPageMenu(id, inv, extraData));
	public static final MenuType<EnergySellerGuiMenu> ENERGY_SELLER_GUI = register("energy_seller_gui", (id, inv, extraData) -> new EnergySellerGuiMenu(id, inv, extraData));

	private static <T extends AbstractContainerMenu> MenuType<T> register(String registryname, IContainerFactory<T> containerFactory) {
		MenuType<T> menuType = new MenuType<T>(containerFactory);
		menuType.setRegistryName(registryname);
		REGISTRY.add(menuType);
		return menuType;
	}

	@SubscribeEvent
	public static void registerContainers(RegistryEvent.Register<MenuType<?>> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new MenuType[0]));
	}
}
