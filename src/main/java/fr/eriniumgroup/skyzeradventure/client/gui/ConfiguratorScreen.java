
package fr.eriniumgroup.skyzeradventure.client.gui;

import fr.eriniumgroup.skyzeradventure.procedures.OverlayConfigTickProcedure;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import fr.eriniumgroup.skyzeradventure.world.inventory.ConfiguratorMenu;
import fr.eriniumgroup.skyzeradventure.network.ConfiguratorButtonMessage;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class ConfiguratorScreen extends AbstractContainerScreen<ConfiguratorMenu> {
	private final static HashMap<String, Object> guistate = ConfiguratorMenu.guistate;
	private final static HashMap<String, String> textstate = new HashMap<>();

	private final Level world;
	private final int x, y, z;
	private final Player entity;
	public static EditBox xVal;
	public static EditBox yVal;
	Button button_empty;
	Button button_r;
	Button button_empty1;
	Button button_empty2;
	Button button_empty3;
	Button button_001;
	Button button_01;
	Button button_1;
	Button button_10;
	Button button_save;

	public ConfiguratorScreen(ConfiguratorMenu container, Inventory inventory, Component text) {
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
		/*this.renderBackground(ms);*/
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
		xVal.render(ms, mouseX, mouseY, partialTicks);
		yVal.render(ms, mouseX, mouseY, partialTicks);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		/*RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.disableBlend();*/
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (xVal.isFocused())
			return xVal.keyPressed(key, b, c);
		if (yVal.isFocused())
			return yVal.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		xVal.tick();
		yVal.tick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, new TranslatableComponent("gui.skyzeradventure.configurator.label_x"), 100, 151, -1);
		this.font.draw(poseStack, new TranslatableComponent("gui.skyzeradventure.configurator.label_y"), 100, 191, -1);
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
		xVal = new EditBox(this.font, this.leftPos + 120, this.topPos + 141, 40, 20, new TranslatableComponent("gui.skyzeradventure.configurator.xVal"));
		xVal.setMaxLength(32767);
		guistate.put("text:xVal", xVal);
		this.addWidget(this.xVal);
		yVal = new EditBox(this.font, this.leftPos + 120, this.topPos + 181, 40, 20, new TranslatableComponent("gui.skyzeradventure.configurator.yVal"));
		yVal.setMaxLength(32767);
		guistate.put("text:yVal", yVal);
		this.addWidget(this.yVal);
		button_empty = new Button(this.leftPos + 80, this.topPos + 141, 20, 20, new TranslatableComponent("gui.skyzeradventure.configurator.button_empty"), e -> {
			if (true) {
				textstate.put("textin:xVal", xVal.getValue());
				textstate.put("textin:yVal", yVal.getValue());
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new ConfiguratorButtonMessage(0, x, y, z, textstate));
				ConfiguratorButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		});
		guistate.put("button:button_empty", button_empty);
		this.addRenderableWidget(button_empty);
		button_r = new Button(this.leftPos + 80, this.topPos + 161, 20, 20, new TranslatableComponent("gui.skyzeradventure.configurator.button_r"), e -> {
			if (true) {
				textstate.put("textin:xVal", xVal.getValue());
				textstate.put("textin:yVal", yVal.getValue());
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new ConfiguratorButtonMessage(1, x, y, z, textstate));
				ConfiguratorButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		});
		guistate.put("button:button_r", button_r);
		this.addRenderableWidget(button_r);
		button_empty1 = new Button(this.leftPos + 80, this.topPos + 181, 20, 20, new TranslatableComponent("gui.skyzeradventure.configurator.button_empty1"), e -> {
			if (true) {
				textstate.put("textin:xVal", xVal.getValue());
				textstate.put("textin:yVal", yVal.getValue());
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new ConfiguratorButtonMessage(2, x, y, z, textstate));
				ConfiguratorButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		});
		guistate.put("button:button_empty1", button_empty1);
		this.addRenderableWidget(button_empty1);
		button_empty2 = new Button(this.leftPos + 100, this.topPos + 161, 20, 20, new TranslatableComponent("gui.skyzeradventure.configurator.button_empty2"), e -> {
			if (true) {
				textstate.put("textin:xVal", xVal.getValue());
				textstate.put("textin:yVal", yVal.getValue());
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new ConfiguratorButtonMessage(3, x, y, z, textstate));
				ConfiguratorButtonMessage.handleButtonAction(entity, 3, x, y, z, textstate);
			}
		});
		guistate.put("button:button_empty2", button_empty2);
		this.addRenderableWidget(button_empty2);
		button_empty3 = new Button(this.leftPos + 60, this.topPos + 161, 20, 20, new TranslatableComponent("gui.skyzeradventure.configurator.button_empty3"), e -> {
			if (true) {
				textstate.put("textin:xVal", xVal.getValue());
				textstate.put("textin:yVal", yVal.getValue());
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new ConfiguratorButtonMessage(4, x, y, z, textstate));
				ConfiguratorButtonMessage.handleButtonAction(entity, 4, x, y, z, textstate);
			}
		});
		guistate.put("button:button_empty3", button_empty3);
		this.addRenderableWidget(button_empty3);
		button_001 = new Button(this.leftPos + -9, this.topPos + 181, 30, 20, new TranslatableComponent("gui.skyzeradventure.configurator.button_001"), e -> {
			if (true) {
				textstate.put("textin:xVal", xVal.getValue());
				textstate.put("textin:yVal", yVal.getValue());
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new ConfiguratorButtonMessage(5, x, y, z, textstate));
				ConfiguratorButtonMessage.handleButtonAction(entity, 5, x, y, z, textstate);
			}
		});
		guistate.put("button:button_001", button_001);
		this.addRenderableWidget(button_001);
		button_01 = new Button(this.leftPos + 20, this.topPos + 181, 20, 20, new TranslatableComponent("gui.skyzeradventure.configurator.button_01"), e -> {
			if (true) {
				textstate.put("textin:xVal", xVal.getValue());
				textstate.put("textin:yVal", yVal.getValue());
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new ConfiguratorButtonMessage(6, x, y, z, textstate));
				ConfiguratorButtonMessage.handleButtonAction(entity, 6, x, y, z, textstate);
			}
		});
		guistate.put("button:button_01", button_01);
		this.addRenderableWidget(button_01);
		button_1 = new Button(this.leftPos + 40, this.topPos + 181, 20, 20, new TranslatableComponent("gui.skyzeradventure.configurator.button_1"), e -> {
			if (true) {
				textstate.put("textin:xVal", xVal.getValue());
				textstate.put("textin:yVal", yVal.getValue());
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new ConfiguratorButtonMessage(7, x, y, z, textstate));
				ConfiguratorButtonMessage.handleButtonAction(entity, 7, x, y, z, textstate);
			}
		});
		guistate.put("button:button_1", button_1);
		this.addRenderableWidget(button_1);
		button_10 = new Button(this.leftPos + 60, this.topPos + 181, 20, 20, new TranslatableComponent("gui.skyzeradventure.configurator.button_10"), e -> {
			if (true) {
				textstate.put("textin:xVal", xVal.getValue());
				textstate.put("textin:yVal", yVal.getValue());
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new ConfiguratorButtonMessage(8, x, y, z, textstate));
				ConfiguratorButtonMessage.handleButtonAction(entity, 8, x, y, z, textstate);
			}
		});
		guistate.put("button:button_10", button_10);
		this.addRenderableWidget(button_10);
		button_save = new Button(this.leftPos + 120, this.topPos + 161, 40, 20, new TranslatableComponent("gui.skyzeradventure.configurator.button_save"), e -> {
			if (true) {
				textstate.put("textin:xVal", xVal.getValue());
				textstate.put("textin:yVal", yVal.getValue());
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new ConfiguratorButtonMessage(9, x, y, z, textstate));
				ConfiguratorButtonMessage.handleButtonAction(entity, 9, x, y, z, textstate);
			}
		});
		guistate.put("button:button_save", button_save);
		this.addRenderableWidget(button_save);
	}
}
