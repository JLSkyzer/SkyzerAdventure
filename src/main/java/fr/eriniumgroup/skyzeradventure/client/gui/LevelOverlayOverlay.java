
package fr.eriniumgroup.skyzeradventure.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import fr.eriniumgroup.skyzeradventure.ARGBToInt;
import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import fr.eriniumgroup.skyzeradventure.procedures.*;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import org.checkerframework.checker.units.qual.h;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.client.Minecraft;

import java.util.Objects;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class LevelOverlayOverlay {
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
			PoseStack pose = event.getMatrixStack();

			RenderSystem.disableDepthTest();
			RenderSystem.depthMask(false);
			RenderSystem.enableBlend();
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			RenderSystem.setShaderColor(1, 1, 1, 1);

			if (IsRPGModeEnabledProcedure.execute(world) && !Objects.requireNonNull(entity).isCreative() && !entity.isSpectator()) {

				/* SCALING */
				int image1W = 600;
				int image1H = 200;
				int drawX = (int) (w * playercap.LevelOverlayX);
				int drawY = (int) (h * playercap.LevelOverlayY);
				int largeurMax = w / 4;

				float scale = 1.0f;
				if (600 > largeurMax) {
					scale = (float) largeurMax / 600f;
				}
				int renderWidth = (int) (600 * scale);
				int renderHeight = (int) (200 * scale);

				pose.pushPose();
				pose.scale(scale, scale, 1.0f);

				RenderSystem.setShaderTexture(0, new ResourceLocation("skyzeradventure:textures/screens/level_overlay_graph.png"));
				GuiComponent.blit(pose, (int) drawX, (int) drawY, 0, 0, image1W, image1H, image1W, image1H);

				RenderSystem.setShaderTexture(0, new ResourceLocation("skyzeradventure:textures/screens/level_overlay_bar.png"));
				GuiComponent.blit(pose, (int) drawX + 199, (int) drawY + 65, 0, 0, (int) ReturnXpForBarProcedure.execute(entity), 70, 391, 70);

				float textscale = 20.0f / 8.0f;

				int xpY = drawY + 65 + ((70 / 2) - 10);
				int xpX = drawX + 199 + (391 / 2);
				int LevelY = drawY + 14 + ((172 / 2) - 10);
				int LevelX = drawX + 14 + (172 / 2);

				pose.translate(xpX, xpY, 0);
				pose.scale(textscale, textscale, 1.0f);

				GuiComponent.drawCenteredString(pose, Minecraft.getInstance().font, ReturnXpLabelBarProcedure.execute(entity), 0, 0, ARGBToInt.ARGBToInt(255, 255, 255, 255));

				pose.popPose();
				pose.pushPose();

				pose.scale(scale, scale, 1.0f);
				pose.translate(LevelX, LevelY, 0);
				pose.scale(textscale, textscale, 1.0f);

				GuiComponent.drawCenteredString(pose, Minecraft.getInstance().font, ReturnLevelLabelProcedure.execute(entity), 0, 0, ARGBToInt.ARGBToInt(255, 255, 255, 255));

				/* END SCALING */
				pose.scale(1.0f, 1.0f, 1.0f);
				pose.popPose();

			}

			RenderSystem.depthMask(true);
			RenderSystem.defaultBlendFunc();
			RenderSystem.enableDepthTest();
			RenderSystem.disableBlend();
			RenderSystem.setShaderColor(1, 1, 1, 1);
		}
	}
}
