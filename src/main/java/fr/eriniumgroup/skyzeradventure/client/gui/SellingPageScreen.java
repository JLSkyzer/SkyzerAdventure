package fr.eriniumgroup.skyzeradventure.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import fr.eriniumgroup.skyzeradventure.world.inventory.SellingPageMenu;
import fr.eriniumgroup.skyzeradventure.procedures.ReturnMoneyTextProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.ReturnItemAndPriceProcedure;
import fr.eriniumgroup.skyzeradventure.network.SellingPageButtonMessage;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModScreens.WidgetScreen;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class SellingPageScreen extends AbstractContainerScreen<SellingPageMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = SellingPageMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	EditBox amount;
	Button button_sell;
	Button button_back;
	Button button_sell_all;

	public SellingPageScreen(SellingPageMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("skyzeradventure:textures/screens/selling_page.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		amount.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
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
		if (amount.isFocused())
			return amount.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		amount.tick();
		textstate.put("textin:amount", amount.getValue());
		SkyzeradventureMod.PACKET_HANDLER.sendToServer(new SellingPageMenu.SellingPageOtherMessage(0, x, y, z, textstate));
		SellingPageMenu.SellingPageOtherMessage.handleOtherAction(entity, 0, x, y, z, textstate);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String amountValue = amount.getValue();
		super.resize(minecraft, width, height);
		amount.setValue(amountValue);
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, new TranslatableComponent("gui.skyzeradventure.selling_page.label_how_much_you_want_to_sell"), 15, 7, -16777216);
		this.font.draw(poseStack,

				ReturnItemAndPriceProcedure.execute(entity), 6, 25, -12829636);
		this.font.draw(poseStack,

				ReturnMoneyTextProcedure.execute(entity), 6, 43, -12829636);
	}

	@Override
	public void init() {
		super.init();
		amount = new EditBox(this.font, this.leftPos + 16, this.topPos + 62, 142, 18, new TranslatableComponent("gui.skyzeradventure.selling_page.amount")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(new TranslatableComponent("gui.skyzeradventure.selling_page.amount").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(new TranslatableComponent("gui.skyzeradventure.selling_page.amount").getString());
				else
					setSuggestion(null);
			}
		};
		amount.setSuggestion(new TranslatableComponent("gui.skyzeradventure.selling_page.amount").getString());
		amount.setMaxLength(32767);
		guistate.put("text:amount", amount);
		this.addWidget(this.amount);
		button_sell = new Button(this.leftPos + 60, this.topPos + 88, 46, 20, new TranslatableComponent("gui.skyzeradventure.selling_page.button_sell"), e -> {
			if (true) {
				textstate.put("textin:amount", amount.getValue());
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new SellingPageButtonMessage(0, x, y, z, textstate));
				SellingPageButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		});
		guistate.put("button:button_sell", button_sell);
		this.addRenderableWidget(button_sell);
		button_back = new Button(this.leftPos + 60, this.topPos + 142, 46, 20, new TranslatableComponent("gui.skyzeradventure.selling_page.button_back"), e -> {
			if (true) {
				textstate.put("textin:amount", amount.getValue());
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new SellingPageButtonMessage(1, x, y, z, textstate));
				SellingPageButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		});
		guistate.put("button:button_back", button_back);
		this.addRenderableWidget(button_back);
		button_sell_all = new Button(this.leftPos + 51, this.topPos + 115, 63, 20, new TranslatableComponent("gui.skyzeradventure.selling_page.button_sell_all"), e -> {
			if (true) {
				textstate.put("textin:amount", amount.getValue());
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new SellingPageButtonMessage(2, x, y, z, textstate));
				SellingPageButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		});
		guistate.put("button:button_sell_all", button_sell_all);
		this.addRenderableWidget(button_sell_all);
	}
}
