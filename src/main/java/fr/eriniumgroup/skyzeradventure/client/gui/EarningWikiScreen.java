
package fr.eriniumgroup.skyzeradventure.client.gui;

import fr.eriniumgroup.skyzeradventure.ARGBToInt;
import fr.eriniumgroup.skyzeradventure.LevelWikiScrollList;
import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import fr.eriniumgroup.skyzeradventure.world.inventory.EarningWikiMenu;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class EarningWikiScreen extends AbstractContainerScreen<EarningWikiMenu> {
	private final static HashMap<String, Object> guistate = EarningWikiMenu.guistate;
	private final static HashMap<String, String> textstate = new HashMap<>();

	private final Level world;
	private final int x, y, z;
	private final Player entity;

	private SkyzeradventureModVariables.PlayerVariables playercap;

	public EarningWikiScreen(EarningWikiMenu container, Inventory inventory, Component text) {
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

	private static final ResourceLocation texture = new ResourceLocation("skyzeradventure:textures/screens/earning_wiki.png");

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

		RenderSystem.setShaderTexture(0, new ResourceLocation("skyzeradventure:textures/screens/earningwikibackground.png"));
		this.blit(ms, this.leftPos + 0, this.topPos + 0, 0, 0, 428, 240, 428, 240);

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
		/*
		// Juste avant le render d’item
			RenderSystem.enableDepthTest();
			minecraft.getItemRenderer().renderAndDecorateItem(item, x + 3, y + 3);
			RenderSystem.disableDepthTest();

			// Min level
			Minecraft.getInstance().font.draw(poseStack, new TextComponent(new DecimalFormat("##").format(minLevel)), x + 24 + 60, y + 1 + 10 - 3, text);
			// Max level
			Minecraft.getInstance().font.draw(poseStack, new TextComponent(new DecimalFormat("##").format(maxLevel)), x + 24 + 120, y + 1 + 10 - 3, text);
			// XP amount
			int XpAmountX = x + 1 + getRowWidth() - 1 - Minecraft.getInstance().font.width(new DecimalFormat("#,###.##").format(xp) + " XP") - 5;
			Minecraft.getInstance().font.draw(poseStack, new TextComponent(new DecimalFormat("#,###.##").format(xp) + " XP"), XpAmountX, y + 1 + 10 - 3, text);
		 */
		this.font.draw(poseStack, new TextComponent("Item / Block"), 90 + 3, 36 - 8, ARGBToInt.ARGBToInt(255, 255, 255, 255));
		this.font.draw(poseStack, new TextComponent("Min Level"), 90 + 3 + 80, 36 - 8, ARGBToInt.ARGBToInt(255, 255, 255, 255));
		this.font.draw(poseStack, new TextComponent("Max Level"), 90 + 3 + 145, 36 - 8, ARGBToInt.ARGBToInt(255, 255, 255, 255));
		this.font.draw(poseStack, new TextComponent("Xp amount"), 90 + 334 - this.font.width(new TextComponent("Xp amount")) - 10, 36 - 8, ARGBToInt.ARGBToInt(255, 255, 255, 255));

		// TITLE
		this.font.draw(poseStack, new TextComponent("How to gain xp : §e" + playercap.EarningWikiTarget), (428 / 2) - (this.font.width(new TextComponent("How to gain xp : §e" + playercap.EarningWikiTarget)) / 2), 4, ARGBToInt.ARGBToInt(255, 255, 255, 255));
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

		// Ajoute une instance de la liste scrollable au GUI
		LevelWikiScrollList scrollableList = new LevelWikiScrollList(this.minecraft, this.leftPos + 90, this.topPos + 36, 334, 200, entity);
		this.addRenderableWidget(scrollableList);
	}
}
