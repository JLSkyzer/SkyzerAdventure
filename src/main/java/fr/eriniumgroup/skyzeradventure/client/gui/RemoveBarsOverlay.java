
package fr.eriniumgroup.skyzeradventure.client.gui;

import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import org.checkerframework.checker.units.qual.h;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.client.Minecraft;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class RemoveBarsOverlay {
	@SubscribeEvent()
	public static void eventHandler(RenderGameOverlayEvent.PreLayer event) {
		IIngameOverlay overlay = event.getOverlay();
		// Vérifier chaque élément HUD à masquer :
		if (overlay == ForgeIngameGui.PLAYER_HEALTH_ELEMENT    // coeurs de vie
				|| overlay == ForgeIngameGui.ARMOR_LEVEL_ELEMENT      // armure
				|| overlay == ForgeIngameGui.FOOD_LEVEL_ELEMENT       // nourriture
				|| overlay == ForgeIngameGui.AIR_LEVEL_ELEMENT        // oxygène (souffle)
				|| overlay == ForgeIngameGui.MOUNT_HEALTH_ELEMENT     // vie de la monture
				|| overlay == ForgeIngameGui.EXPERIENCE_BAR_ELEMENT)  // barre d’expérience
		{
			event.setCanceled(true); // Empêche le rendu de cet overlay spécifique
		}
	}
}
