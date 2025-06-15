
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

import fr.eriniumgroup.skyzeradventure.network.OpenEarningWikiMessage;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class SkyzeradventureModKeyMappings {
	public static final KeyMapping OPEN_EARNING_WIKI = new KeyMapping("key.skyzeradventure.open_earning_wiki", GLFW.GLFW_KEY_O, "key.categories.skyzeradventure");

	@SubscribeEvent
	public static void registerKeyBindings(FMLClientSetupEvent event) {
		ClientRegistry.registerKeyBinding(OPEN_EARNING_WIKI);
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
			}
		}
	}
}
