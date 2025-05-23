
package fr.eriniumgroup.skyzeradventure.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.eriniumgroup.skyzeradventure.ARGBToInt;
import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import fr.eriniumgroup.skyzeradventure.procedures.*;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.common.capabilities.Capability;
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
public class HealthOverlayOverlay {
	@SubscribeEvent(priority = EventPriority.HIGHEST)
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

			RenderSystem.disableDepthTest();
			RenderSystem.depthMask(false);
			RenderSystem.enableBlend();
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			RenderSystem.setShaderColor(1, 1, 1, 1);

			if (IsRPGModeEnabledProcedure.execute(world) && !Objects.requireNonNull(entity).isCreative() && !entity.isSpectator()) {
				int drawX = w / 2 - 91;
				int drawY = h - 22 - 54;

				int centeredtext = drawX + 15 + 164 / 2;

				RenderSystem.setShaderTexture(0, new ResourceLocation("skyzeradventure:textures/screens/overlay.png"));
				GuiComponent.blit(event.getMatrixStack(), drawX, drawY, 0, 0, 182, 53, 182, 53);

				RenderSystem.setShaderTexture(0, new ResourceLocation("skyzeradventure:textures/screens/xp_bar.png"));
				GuiComponent.blit(event.getMatrixStack(), drawX + 15, drawY + 4, 0, 0, (int) (164 * entity.experienceProgress), 9, 164, 9);

				GuiComponent.drawCenteredString(event.getMatrixStack(), Minecraft.getInstance().font, ReturnXpTextProcedure.execute(entity), centeredtext, drawY + 4 + 1, ARGBToInt.ARGBToInt(255, 255, 255, 255));

				RenderSystem.setShaderTexture(0, new ResourceLocation("skyzeradventure:textures/screens/armor_bar.png"));
				GuiComponent.blit(event.getMatrixStack(), drawX + 15, drawY + 16, 0, 0, (int) ReturnArmorValueProcedure.execute(entity), 9, 164, 9);

				GuiComponent.drawCenteredString(event.getMatrixStack(), Minecraft.getInstance().font, ReturnArmorTextProcedure.execute(entity), centeredtext, drawY + 16 + 1, ARGBToInt.ARGBToInt(255, 255, 255, 255));

				RenderSystem.setShaderTexture(0, new ResourceLocation("skyzeradventure:textures/screens/food_bar.png"));
				GuiComponent.blit(event.getMatrixStack(), drawX + 15, drawY + 28, 0, 0, (int) ReturnFoodValueProcedure.execute(entity), 9, 164, 9);

				GuiComponent.drawCenteredString(event.getMatrixStack(), Minecraft.getInstance().font, ReturnFoodValueTextProcedure.execute(entity), centeredtext, drawY + 28 + 1, ARGBToInt.ARGBToInt(255, 255, 255, 255));

				RenderSystem.setShaderTexture(0, new ResourceLocation("skyzeradventure:textures/screens/health_bar.png"));
				GuiComponent.blit(event.getMatrixStack(), drawX + 15, drawY + 40, 0, 0, (int) ReturnHealthValueProcedure.execute(entity), 9, 164, 9);

				GuiComponent.drawCenteredString(event.getMatrixStack(), Minecraft.getInstance().font, ReturnHealthValueTextProcedure.execute(entity), centeredtext, drawY + 40 + 1, ARGBToInt.ARGBToInt(255, 255, 255, 255));
			}

			RenderSystem.depthMask(true);
			RenderSystem.defaultBlendFunc();
			RenderSystem.enableDepthTest();
			RenderSystem.disableBlend();
			RenderSystem.setShaderColor(1, 1, 1, 1);

		}
	}

}
