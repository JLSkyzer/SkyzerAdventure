package fr.eriniumgroup.skyzeradventure.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;

import java.util.HashMap;

import fr.eriniumgroup.skyzeradventure.world.inventory.EnergySellerGuiMenu;
import fr.eriniumgroup.skyzeradventure.procedures.ReturnTargetLabelProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.ReturnPriceLabelProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.ReturnEnergyLabelProcedure;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModScreens.WidgetScreen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class EnergySellerGuiScreen extends AbstractContainerScreen<EnergySellerGuiMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = EnergySellerGuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();

	public EnergySellerGuiScreen(EnergySellerGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("skyzeradventure:textures/screens/energy_seller_gui.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		RenderSystem.setShaderTexture(0, new ResourceLocation("skyzeradventure:textures/screens/gui_background.png"));
		this.blit(ms, this.leftPos + -126, this.topPos + -37, 0, 0, 428, 240, 428, 240);

		RenderSystem.disableBlend();
	}

	public HashMap<String, Object> getWidgets() {
		return guistate;
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, new TranslatableComponent("gui.skyzeradventure.energy_seller_gui.label_energy_seller"), 51, -29, -1);
		this.font.draw(poseStack,

				ReturnTargetLabelProcedure.execute(world, x, y, z), 24, -2, -1);
		this.font.draw(poseStack,

				ReturnEnergyLabelProcedure.execute(world, x, y, z), 24, 16, -1);
		this.font.draw(poseStack,

				ReturnPriceLabelProcedure.execute(), 24, 34, -1);
	}

	@Override
	public void init() {
		super.init();
	}
}
