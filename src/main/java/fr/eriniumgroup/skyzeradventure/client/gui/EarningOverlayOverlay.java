
package fr.eriniumgroup.skyzeradventure.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import fr.eriniumgroup.skyzeradventure.procedures.ReturnOverlayConfigOpenProcedure;
import net.minecraft.client.gui.GuiComponent;
import org.checkerframework.checker.units.qual.h;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.Minecraft;

import fr.eriniumgroup.skyzeradventure.procedures.ReturnEarningTickIsAboveZeroProcedure;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

import fr.eriniumgroup.skyzeradventure.ARGBToInt;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class EarningOverlayOverlay {
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGameOverlayEvent.Pre event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
			int w = event.getWindow().getGuiScaledWidth();
			int h = event.getWindow().getGuiScaledHeight();
			int posX = w / 2;
			int posY = h / 2;
			Level world = null;
			double x = 0;
			double y = 0;
			double z = 0;
			Player entity = Minecraft.getInstance().player;
			if (entity != null) {
				world = entity.level;
				x = entity.getX();
				y = entity.getY();
				z = entity.getZ();
			}

			assert entity != null;
			SkyzeradventureModVariables.PlayerVariables playercap = (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables()));

			RenderSystem.disableDepthTest();
			RenderSystem.depthMask(false);
			RenderSystem.enableBlend();
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			RenderSystem.setShaderColor(1, 1, 1, 1);
			if (ReturnEarningTickIsAboveZeroProcedure.execute(entity) || ReturnOverlayConfigOpenProcedure.execute(entity)) {
				int LocX = (int) (w * playercap.earningNotifX);
				int LocY = (int) (h * playercap.earningNotifY);

				if (playercap.earningNotifX >= 0.65){
					LocX = LocX - 160;
				}
				if (playercap.earningNotifY >= 70){
					LocY = LocY + 55;
				}


				int barSize = (int) (playercap.earningNotifTick * ((double) 160 / 60));
				int centerforText = LocX + (160/2);

				RenderSystem.setShaderTexture(0, new ResourceLocation("skyzeradventure:textures/screens/rank_overlay.png"));
				GuiComponent.blit(event.getMatrixStack(), LocX, LocY, 0, 0, 160, 50, 160, 50);

				RenderSystem.setShaderTexture(0, new ResourceLocation("skyzeradventure:textures/screens/rank_overlay_bar.png"));
				GuiComponent.blit(event.getMatrixStack(), LocX, LocY + 50, 0, 0, barSize, 5, 160, 5);

				GuiComponent.drawCenteredString(event.getMatrixStack(), Minecraft.getInstance().font, playercap.earning_text1, centerforText, LocY + 6, ARGBToInt.ARGBToInt(255, 255, 255, 255));
				GuiComponent.drawCenteredString(event.getMatrixStack(), Minecraft.getInstance().font, playercap.earning_text2, centerforText, LocY + 21, ARGBToInt.ARGBToInt(255, 255, 255, 255));
				GuiComponent.drawCenteredString(event.getMatrixStack(), Minecraft.getInstance().font, playercap.earning_text3, centerforText, LocY + 36, ARGBToInt.ARGBToInt(255, 255, 255, 255));
			}
			RenderSystem.depthMask(true);
			RenderSystem.defaultBlendFunc();
			RenderSystem.enableDepthTest();
			RenderSystem.disableBlend();
			RenderSystem.setShaderColor(1, 1, 1, 1);
		}
	}
}
