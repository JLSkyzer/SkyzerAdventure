
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.skyzeradventure.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import fr.eriniumgroup.skyzeradventure.network.ShowOnOffHealthOverlayMessage;
import fr.eriniumgroup.skyzeradventure.network.OpenEarningWikiMessage;
import fr.eriniumgroup.skyzeradventure.network.KeyOpenAdminShopMessage;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class SkyzeradventureModKeyMappings {
	public static final KeyMapping OPEN_EARNING_WIKI = new KeyMapping("key.skyzeradventure.open_earning_wiki", GLFW.GLFW_KEY_O, "key.categories.skyzeradventure");
	public static final KeyMapping SHOW_ON_OFF_HEALTH_OVERLAY = new KeyMapping("key.skyzeradventure.show_on_off_health_overlay", GLFW.GLFW_KEY_P, "key.categories.skyzeradventure");
	public static final KeyMapping KEY_OPEN_ADMIN_SHOP = new KeyMapping("key.skyzeradventure.key_open_admin_shop", GLFW.GLFW_KEY_U, "key.categories.skyzeradventure");

	@SubscribeEvent
	public static void registerKeyBindings(FMLClientSetupEvent event) {
		ClientRegistry.registerKeyBinding(OPEN_EARNING_WIKI);
		ClientRegistry.registerKeyBinding(SHOW_ON_OFF_HEALTH_OVERLAY);
		ClientRegistry.registerKeyBinding(KEY_OPEN_ADMIN_SHOP);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onKeyInput(InputEvent.KeyInputEvent event) {
			if (Minecraft.getInstance().screen == null) {
				if (event.getKey() == OPEN_EARNING_WIKI.getKey().getValue()) {
					if (event.getAction() == GLFW.GLFW_PRESS) {
						SkyzeradventureMod.PACKET_HANDLER.sendToServer(new OpenEarningWikiMessage(0, 0));
						OpenEarningWikiMessage.pressAction(Minecraft.getInstance().player, 0, 0);
					}
				}
				if (event.getKey() == SHOW_ON_OFF_HEALTH_OVERLAY.getKey().getValue()) {
					if (event.getAction() == GLFW.GLFW_PRESS) {
						SkyzeradventureMod.PACKET_HANDLER.sendToServer(new ShowOnOffHealthOverlayMessage(0, 0));
						ShowOnOffHealthOverlayMessage.pressAction(Minecraft.getInstance().player, 0, 0);
					}
				}
				if (event.getKey() == KEY_OPEN_ADMIN_SHOP.getKey().getValue()) {
					if (event.getAction() == GLFW.GLFW_PRESS) {
						SkyzeradventureMod.PACKET_HANDLER.sendToServer(new KeyOpenAdminShopMessage(0, 0));
						KeyOpenAdminShopMessage.pressAction(Minecraft.getInstance().player, 0, 0);
					}
				}
			}
		}
	}
}
