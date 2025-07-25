package fr.eriniumgroup.skyzeradventure.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;

import fr.eriniumgroup.skyzeradventure.world.inventory.StatsScaleConfigMenu;
import fr.eriniumgroup.skyzeradventure.procedures.ReturnScaleValueProcedure;
import fr.eriniumgroup.skyzeradventure.network.StatsScaleConfigButtonMessage;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModScreens;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class StatsScaleConfigScreen extends AbstractContainerScreen<StatsScaleConfigMenu> implements SkyzeradventureModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
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
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int mouseX, int mouseY) {
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
	protected void renderLabels(PoseStack ms, int mouseX, int mouseY) {
		this.font.draw(ms, ReturnScaleValueProcedure.execute(entity), 69, 7, -3407872);
	}

	@Override
	public void init() {
		super.init();
		button_empty = new Button(this.leftPos + 96, this.topPos + 25, 18, 20, new TranslatableComponent("gui.skyzeradventure.stats_scale_config.button_empty"), e -> {
			int x = StatsScaleConfigScreen.this.x;
			int y = StatsScaleConfigScreen.this.y;
			if (true) {
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new StatsScaleConfigButtonMessage(0, x, y, z));
				StatsScaleConfigButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		this.addRenderableWidget(button_empty);
		button_r = new Button(this.leftPos + 78, this.topPos + 25, 18, 20, new TranslatableComponent("gui.skyzeradventure.stats_scale_config.button_r"), e -> {
			int x = StatsScaleConfigScreen.this.x;
			int y = StatsScaleConfigScreen.this.y;
			if (true) {
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new StatsScaleConfigButtonMessage(1, x, y, z));
				StatsScaleConfigButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		});
		this.addRenderableWidget(button_r);
		button_empty1 = new Button(this.leftPos + 60, this.topPos + 25, 18, 20, new TranslatableComponent("gui.skyzeradventure.stats_scale_config.button_empty1"), e -> {
			int x = StatsScaleConfigScreen.this.x;
			int y = StatsScaleConfigScreen.this.y;
			if (true) {
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new StatsScaleConfigButtonMessage(2, x, y, z));
				StatsScaleConfigButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		});
		this.addRenderableWidget(button_empty1);
	}
}