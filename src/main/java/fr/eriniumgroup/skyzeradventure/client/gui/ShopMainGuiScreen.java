
package fr.eriniumgroup.skyzeradventure.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import fr.eriniumgroup.skyzeradventure.world.inventory.ShopMainGuiMenu;
import fr.eriniumgroup.skyzeradventure.network.ShopMainGuiButtonMessage;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class ShopMainGuiScreen extends AbstractContainerScreen<ShopMainGuiMenu> {
	private final static HashMap<String, Object> guistate = ShopMainGuiMenu.guistate;
	private final static HashMap<String, String> textstate = new HashMap<>();

	private final Level world;
	private final int x, y, z;
	private final Player entity;
	ImageButton imagebutton_buy_button;
	ImageButton imagebutton_sell_button;

	public ShopMainGuiScreen(ShopMainGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("skyzeradventure:textures/screens/shop_main_gui.png");

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
		this.blit(ms, this.leftPos + -125, this.topPos + -37, 0, 0, 428, 240, 428, 240);

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
		imagebutton_buy_button = new ImageButton(this.leftPos + 6, this.topPos + 43, 64, 64, 0, 0, 64, new ResourceLocation("skyzeradventure:textures/screens/atlas/imagebutton_buy_button.png"), 64, 128, e -> {
			if (true) {
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new ShopMainGuiButtonMessage(0, x, y, z, textstate));
				ShopMainGuiButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_buy_button", imagebutton_buy_button);
		this.addRenderableWidget(imagebutton_buy_button);
		imagebutton_sell_button = new ImageButton(this.leftPos + 105, this.topPos + 43, 64, 64, 0, 0, 64, new ResourceLocation("skyzeradventure:textures/screens/atlas/imagebutton_sell_button.png"), 64, 128, e -> {
			if (true) {
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new ShopMainGuiButtonMessage(1, x, y, z, textstate));
				ShopMainGuiButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		});
		guistate.put("button:imagebutton_sell_button", imagebutton_sell_button);
		this.addRenderableWidget(imagebutton_sell_button);
	}
}
