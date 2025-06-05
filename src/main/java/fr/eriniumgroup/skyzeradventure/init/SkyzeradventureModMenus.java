
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
import fr.eriniumgroup.skyzeradventure.world.inventory.EarningWikiMenu;
import fr.eriniumgroup.skyzeradventure.world.inventory.ConfiguratorMenu;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SkyzeradventureModMenus {
	private static final List<MenuType<?>> REGISTRY = new ArrayList<>();
	public static final MenuType<StatsScaleConfigMenu> STATS_SCALE_CONFIG = register("stats_scale_config", (id, inv, extraData) -> new StatsScaleConfigMenu(id, inv, extraData));
	public static final MenuType<ConfiguratorMenu> CONFIGURATOR = register("configurator", (id, inv, extraData) -> new ConfiguratorMenu(id, inv, extraData));
	public static final MenuType<EarningWikiMenu> EARNING_WIKI = register("earning_wiki", (id, inv, extraData) -> new EarningWikiMenu(id, inv, extraData));

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
