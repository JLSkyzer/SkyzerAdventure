
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
			Player entity = Minecraft.getInstance().player;
			if (entity == null) return;

			Level world = entity.level;
			if (!IsRPGModeEnabledProcedure.execute(world) || entity.isCreative() || entity.isSpectator()) return;

			SkyzeradventureModVariables.PlayerVariables playercap = entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables());
			float scale = (float) playercap.stats_scale;

			PoseStack pose = event.getMatrixStack();

			int overlayWidth = 182;
			int overlayHeight = 53;

			int centerX = w / 2;
			int baseY = h - 23;

			int drawX = (int) ((centerX - (overlayWidth * scale) / 2) / scale);
			int drawY = (int) ((baseY - (overlayHeight * scale)) / scale);

			int centeredTextX = drawX + 15 + 164 / 2;

			pose.pushPose();
			pose.scale(scale, scale, 1.0f);

			RenderSystem.setShaderTexture(0, new ResourceLocation("skyzeradventure:textures/screens/overlay.png"));
			GuiComponent.blit(pose, drawX, drawY, 0, 0, 182, 53, 182, 53);

			RenderSystem.setShaderTexture(0, new ResourceLocation("skyzeradventure:textures/screens/xp_bar.png"));
			GuiComponent.blit(pose, drawX + 15, drawY + 4, 0, 0, (int) (164 * entity.experienceProgress), 9, 164, 9);
			GuiComponent.drawCenteredString(pose, Minecraft.getInstance().font, ReturnXpTextProcedure.execute(entity), centeredTextX, drawY + 5, ARGBToInt.ARGBToInt(255, 255, 255, 255));

			RenderSystem.setShaderTexture(0, new ResourceLocation("skyzeradventure:textures/screens/armor_bar.png"));
			GuiComponent.blit(pose, drawX + 15, drawY + 16, 0, 0, (int) ReturnArmorValueProcedure.execute(entity), 9, 164, 9);
			GuiComponent.drawCenteredString(pose, Minecraft.getInstance().font, ReturnArmorTextProcedure.execute(entity), centeredTextX, drawY + 17, ARGBToInt.ARGBToInt(255, 255, 255, 255));

			RenderSystem.setShaderTexture(0, new ResourceLocation("skyzeradventure:textures/screens/food_bar.png"));
			GuiComponent.blit(pose, drawX + 15, drawY + 28, 0, 0, (int) ReturnFoodValueProcedure.execute(entity), 9, 164, 9);
			GuiComponent.drawCenteredString(pose, Minecraft.getInstance().font, ReturnFoodValueTextProcedure.execute(entity), centeredTextX, drawY + 29, ARGBToInt.ARGBToInt(255, 255, 255, 255));

			RenderSystem.setShaderTexture(0, new ResourceLocation("skyzeradventure:textures/screens/health_bar.png"));
			GuiComponent.blit(pose, drawX + 15, drawY + 40, 0, 0, (int) ReturnHealthValueProcedure.execute(entity), 9, 164, 9);
			GuiComponent.drawCenteredString(pose, Minecraft.getInstance().font, ReturnHealthValueTextProcedure.execute(entity), centeredTextX, drawY + 41, ARGBToInt.ARGBToInt(255, 255, 255, 255));

			pose.popPose();

			RenderSystem.depthMask(true);
			RenderSystem.defaultBlendFunc();
			RenderSystem.enableDepthTest();
			RenderSystem.disableBlend();
			RenderSystem.setShaderColor(1, 1, 1, 1);
		}
	}
}
