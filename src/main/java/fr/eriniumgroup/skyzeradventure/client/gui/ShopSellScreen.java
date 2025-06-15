
package fr.eriniumgroup.skyzeradventure.client.gui;

import fr.eriniumgroup.skyzeradventure.ShopScrollList;
import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
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

import fr.eriniumgroup.skyzeradventure.world.inventory.ShopSellMenu;
import fr.eriniumgroup.skyzeradventure.procedures.ReturnMoneyTextProcedure;
import fr.eriniumgroup.skyzeradventure.network.ShopSellButtonMessage;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class ShopSellScreen extends AbstractContainerScreen<ShopSellMenu> {
	private final static HashMap<String, Object> guistate = ShopSellMenu.guistate;
	private final static HashMap<String, String> textstate = new HashMap<>();

	private final Level world;
	private final int x, y, z;
	private final Player entity;
	public static EditBox search;
	Button button_return;
	Button button_search;
	SkyzeradventureModVariables.PlayerVariables playercap;

	public ShopSellScreen(ShopSellMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 428;
		this.imageHeight = 240;
		this.playercap = entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables());
	}

	private static final ResourceLocation texture = new ResourceLocation("skyzeradventure:textures/screens/shop_sell.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
		search.render(ms, mouseX, mouseY, partialTicks);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		RenderSystem.setShaderTexture(0, new ResourceLocation("skyzeradventure:textures/screens/shop_background.png"));
		this.blit(ms, this.leftPos + 0, this.topPos + 0, 0, 0, 428, 240, 428, 240);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (search.isFocused())
			return search.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		search.tick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, new TranslatableComponent("gui.skyzeradventure.shop_sell.label_shop_gui"), 60, 8, -1);
		this.font.draw(poseStack,

				ReturnMoneyTextProcedure.execute(entity), 107, 8, -12829636);
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
		search = new EditBox(this.font, this.leftPos + 225, this.topPos + 3, 144, 20, new TranslatableComponent("gui.skyzeradventure.shop_sell.search")) {
			{
				setSuggestion(new TranslatableComponent("gui.skyzeradventure.shop_sell.search").getString());
				setValue(playercap.sellsearch);
			}

			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(new TranslatableComponent("gui.skyzeradventure.shop_sell.search").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(new TranslatableComponent("gui.skyzeradventure.shop_sell.search").getString());
				else
					setSuggestion(null);
			}
		};
		search.setMaxLength(32767);
		guistate.put("text:search", search);
		this.addWidget(this.search);
		button_return = new Button(this.leftPos + 3, this.topPos + 3, 56, 20, new TranslatableComponent("gui.skyzeradventure.shop_sell.button_return"), e -> {
			if (true) {
				textstate.put("textin:search", search.getValue());
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new ShopSellButtonMessage(0, x, y, z, textstate));
				ShopSellButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		});
		guistate.put("button:button_return", button_return);
		this.addRenderableWidget(button_return);
		button_search = new Button(this.leftPos + 369, this.topPos + 3, 56, 20, new TranslatableComponent("gui.skyzeradventure.shop_sell.button_search"), e -> {
			if (true) {
				textstate.put("textin:search", search.getValue());
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new ShopSellButtonMessage(1, x, y, z, textstate));
				ShopSellButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		});
		guistate.put("button:button_search", button_search);
		this.addRenderableWidget(button_search);

		ShopScrollList scrollableList = new ShopScrollList(this.minecraft, this.leftPos + 2, this.topPos + 24, 424, 214, entity);
		this.addRenderableWidget(scrollableList);
	}

	@Override
	public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
		// Si tu as plusieurs scrolls, répète la ligne pour chaque (ex : shopScrollList2, etc.)
		for (var widget : this.renderables) {
			if (widget instanceof ShopScrollList scrollList) {
				if (scrollList.mouseDragged(mouseX, mouseY, button, dragX, dragY)) {
					return true;
				}
			}
		}
		return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
	}
}
