
package fr.eriniumgroup.skyzeradventure.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import fr.eriniumgroup.skyzeradventure.world.inventory.StatsScaleConfigMenu;
import fr.eriniumgroup.skyzeradventure.procedures.ReturnScaleValueProcedure;
import fr.eriniumgroup.skyzeradventure.network.StatsScaleConfigButtonMessage;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class StatsScaleConfigScreen extends AbstractContainerScreen<StatsScaleConfigMenu> {
	private final static HashMap<String, Object> guistate = StatsScaleConfigMenu.guistate;
	private final static HashMap<String, String> textstate = new HashMap<>();

	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_empty;
	Button button_r;
	Button button_empty1;

	public StatsScaleConfigScreen(StatsScaleConfigMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

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
		RenderSystem.disableBlend();
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
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack,

				ReturnScaleValueProcedure.execute(entity), 69, 7, -3407872);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
	}

	@Override
	public void init() {
		super.init();
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		button_empty = new Button(this.leftPos + 96, this.topPos + 25, 18, 20, new TranslatableComponent("gui.skyzeradventure.stats_scale_config.button_empty"), e -> {
			if (true) {
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new StatsScaleConfigButtonMessage(0, x, y, z, textstate));
				StatsScaleConfigButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		});
		guistate.put("button:button_empty", button_empty);
		this.addRenderableWidget(button_empty);
		button_r = new Button(this.leftPos + 78, this.topPos + 25, 18, 20, new TranslatableComponent("gui.skyzeradventure.stats_scale_config.button_r"), e -> {
			if (true) {
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new StatsScaleConfigButtonMessage(1, x, y, z, textstate));
				StatsScaleConfigButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		});
		guistate.put("button:button_r", button_r);
		this.addRenderableWidget(button_r);
		button_empty1 = new Button(this.leftPos + 60, this.topPos + 25, 18, 20, new TranslatableComponent("gui.skyzeradventure.stats_scale_config.button_empty1"), e -> {
			if (true) {
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new StatsScaleConfigButtonMessage(2, x, y, z, textstate));
				StatsScaleConfigButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		});
		guistate.put("button:button_empty1", button_empty1);
		this.addRenderableWidget(button_empty1);
	}
}
