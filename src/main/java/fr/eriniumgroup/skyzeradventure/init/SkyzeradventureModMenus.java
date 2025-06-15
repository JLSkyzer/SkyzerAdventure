
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.skyzeradventure.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;

import javax.annotation.Nullable;

import java.util.function.Supplier;

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
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
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

	public static void setText(String boxname, String value, @Nullable ServerPlayer player) {
		if (player != null) {
			SkyzeradventureMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new GuiSyncMessage(boxname, value));
		} else {
			SkyzeradventureMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new GuiSyncMessage(boxname, value));
		}
	}

	public static class GuiSyncMessage {
		private final String textboxid;
		private final String data;

		public GuiSyncMessage(FriendlyByteBuf buffer) {
			this.textboxid = buffer.readComponent().getString();
			this.data = buffer.readComponent().getString();
		}

		public GuiSyncMessage(String textboxid, String data) {
			this.textboxid = textboxid;
			this.data = data;
		}

		public static void buffer(GuiSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeUtf(message.textboxid);
			buffer.writeUtf(message.data);
		}

		public static void handleData(GuiSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					SkyzeradventureModScreens.handleTextBoxMessage(message);
				}
			});
			context.setPacketHandled(true);
		}

		String editbox() {
			return this.textboxid;
		}

		String value() {
			return this.data;
		}
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		SkyzeradventureMod.addNetworkMessage(GuiSyncMessage.class, GuiSyncMessage::buffer, GuiSyncMessage::new, GuiSyncMessage::handleData);
	}
}
