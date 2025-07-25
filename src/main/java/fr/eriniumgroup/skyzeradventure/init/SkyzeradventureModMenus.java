/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.skyzeradventure.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.client.Minecraft;

import java.util.Map;

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
import fr.eriniumgroup.skyzeradventure.world.inventory.AutoSellerGuiMenu;
import fr.eriniumgroup.skyzeradventure.network.MenuStateUpdateMessage;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

public class SkyzeradventureModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.CONTAINERS, SkyzeradventureMod.MODID);
	public static final RegistryObject<MenuType<ConfiguratorMenu>> CONFIGURATOR = REGISTRY.register("configurator", () -> IForgeMenuType.create(ConfiguratorMenu::new));
	public static final RegistryObject<MenuType<StatsScaleConfigMenu>> STATS_SCALE_CONFIG = REGISTRY.register("stats_scale_config", () -> IForgeMenuType.create(StatsScaleConfigMenu::new));
	public static final RegistryObject<MenuType<EarningWikiMenu>> EARNING_WIKI = REGISTRY.register("earning_wiki", () -> IForgeMenuType.create(EarningWikiMenu::new));
	public static final RegistryObject<MenuType<EarningWikiHomePageMenu>> EARNING_WIKI_HOME_PAGE = REGISTRY.register("earning_wiki_home_page", () -> IForgeMenuType.create(EarningWikiHomePageMenu::new));
	public static final RegistryObject<MenuType<ShopMainGuiMenu>> SHOP_MAIN_GUI = REGISTRY.register("shop_main_gui", () -> IForgeMenuType.create(ShopMainGuiMenu::new));
	public static final RegistryObject<MenuType<ShopBuyMenu>> SHOP_BUY = REGISTRY.register("shop_buy", () -> IForgeMenuType.create(ShopBuyMenu::new));
	public static final RegistryObject<MenuType<ShopSellMenu>> SHOP_SELL = REGISTRY.register("shop_sell", () -> IForgeMenuType.create(ShopSellMenu::new));
	public static final RegistryObject<MenuType<SellingPageMenu>> SELLING_PAGE = REGISTRY.register("selling_page", () -> IForgeMenuType.create(SellingPageMenu::new));
	public static final RegistryObject<MenuType<BuyPageMenu>> BUY_PAGE = REGISTRY.register("buy_page", () -> IForgeMenuType.create(BuyPageMenu::new));
	public static final RegistryObject<MenuType<EnergySellerGuiMenu>> ENERGY_SELLER_GUI = REGISTRY.register("energy_seller_gui", () -> IForgeMenuType.create(EnergySellerGuiMenu::new));
	public static final RegistryObject<MenuType<AutoSellerGuiMenu>> AUTO_SELLER_GUI = REGISTRY.register("auto_seller_gui", () -> IForgeMenuType.create(AutoSellerGuiMenu::new));

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (player instanceof ServerPlayer serverPlayer) {
				SkyzeradventureMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new MenuStateUpdateMessage(elementType, name, elementState));
			} else if (player.level.isClientSide) {
				if (Minecraft.getInstance().screen instanceof SkyzeradventureModScreens.ScreenAccessor accessor && needClientUpdate)
					accessor.updateMenuState(elementType, name, elementState);
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new MenuStateUpdateMessage(elementType, name, elementState));
			}
		}

		default <T> T getMenuState(int elementType, String name, T defaultValue) {
			try {
				return (T) getMenuState().getOrDefault(elementType + ":" + name, defaultValue);
			} catch (ClassCastException e) {
				return defaultValue;
			}
		}
	}
}