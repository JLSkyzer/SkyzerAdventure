
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

import fr.eriniumgroup.skyzeradventure.world.inventory.BuyPageMenu;
import fr.eriniumgroup.skyzeradventure.procedures.ReturnTotalPriceProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.ReturnMoneyTextProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.ReturnItemAndPriceProcedure;
import fr.eriniumgroup.skyzeradventure.network.BuyPageButtonMessage;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class BuyPageScreen extends AbstractContainerScreen<BuyPageMenu> {
	private final static HashMap<String, Object> guistate = BuyPageMenu.guistate;
	private final static HashMap<String, String> textstate = new HashMap<>();

	private final Level world;
	private final int x, y, z;
	private final Player entity;
	public static EditBox amount;
	Button button_back;
	Button button_buy;
	Button button_max;

	public BuyPageScreen(BuyPageMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("skyzeradventure:textures/screens/buy_page.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
		amount.render(ms, mouseX, mouseY, partialTicks);
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
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, new TranslatableComponent("gui.skyzeradventure.buy_page.label_how_much_you_want_to_sell"), 15, 7, -16777216);
		this.font.draw(poseStack,

				ReturnItemAndPriceProcedure.execute(entity), 6, 25, -12829636);
		this.font.draw(poseStack,

				ReturnMoneyTextProcedure.execute(entity), 6, 43, -12829636);
		this.font.draw(poseStack,

				ReturnTotalPriceProcedure.execute(entity, guistate), 6, 70, -12829636);
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
		amount = new EditBox(this.font, this.leftPos + 6, this.topPos + 88, 108, 20, new TranslatableComponent("gui.skyzeradventure.buy_page.amount")) {
			{
				setSuggestion(new TranslatableComponent("gui.skyzeradventure.buy_page.amount").getString());
			}

			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(new TranslatableComponent("gui.skyzeradventure.buy_page.amount").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(new TranslatableComponent("gui.skyzeradventure.buy_page.amount").getString());
				else
					setSuggestion(null);
			}
		};
		amount.setMaxLength(32767);
		amount.setResponder(s -> {
			guistate.put("textin:amount", s); // Mets la valeur à jour en temps réel
		});
		this.addWidget(this.amount);
		button_back = new Button(this.leftPos + 60, this.topPos + 142, 46, 20, new TranslatableComponent("gui.skyzeradventure.buy_page.button_back"), e -> {
			if (true) {
				textstate.put("textin:amount", amount.getValue());
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new BuyPageButtonMessage(0, x, y, z, textstate));
				BuyPageButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		});
		guistate.put("button:button_back", button_back);
		this.addRenderableWidget(button_back);
		button_buy = new Button(this.leftPos + 60, this.topPos + 115, 45, 20, new TranslatableComponent("gui.skyzeradventure.buy_page.button_buy"), e -> {
			if (true) {
				textstate.put("textin:amount", amount.getValue());
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new BuyPageButtonMessage(1, x, y, z, textstate));
				BuyPageButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		});
		guistate.put("button:button_buy", button_buy);
		this.addRenderableWidget(button_buy);
		button_max = new Button(this.leftPos + 123, this.topPos + 88, 36, 20, new TranslatableComponent("gui.skyzeradventure.buy_page.button_max"), e -> {
			if (true) {
				textstate.put("textin:amount", amount.getValue());
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new BuyPageButtonMessage(2, x, y, z, textstate));
				BuyPageButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		});
		guistate.put("button:button_max", button_max);
		this.addRenderableWidget(button_max);
	}
}
