package fr.eriniumgroup.skyzeradventure.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Button;

import fr.eriniumgroup.skyzeradventure.world.inventory.EarningWikiHomePageMenu;
import fr.eriniumgroup.skyzeradventure.network.EarningWikiHomePageButtonMessage;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModScreens;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class EarningWikiHomePageScreen extends AbstractContainerScreen<EarningWikiHomePageMenu> implements SkyzeradventureModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	Button button_unlocked;
	ImageButton imagebutton_crafting;
	ImageButton imagebutton_smelting;
	ImageButton imagebutton_sword;
	ImageButton imagebutton_pickaxe;

	public EarningWikiHomePageScreen(EarningWikiHomePageMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 428;
		this.imageHeight = 240;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = new ResourceLocation("skyzeradventure:textures/screens/earning_wiki_home_page.png");

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
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.setShaderTexture(0, new ResourceLocation("skyzeradventure:textures/screens/gui_background.png"));
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
	protected void renderLabels(PoseStack ms, int mouseX, int mouseY) {
		this.font.draw(ms, new TranslatableComponent("gui.skyzeradventure.earning_wiki_home_page.label_choose_type"), 2, 2, -3407872);
	}

	@Override
	public void init() {
		super.init();
		button_unlocked = new Button(this.leftPos + 357, this.topPos + 8, 63, 20, new TranslatableComponent("gui.skyzeradventure.earning_wiki_home_page.button_unlocked"), e -> {
		});
		this.addRenderableWidget(button_unlocked);
		imagebutton_crafting = new ImageButton(this.leftPos + 6, this.topPos + 17, 64, 64, 0, 0, 64, new ResourceLocation("skyzeradventure:textures/screens/atlas/imagebutton_crafting.png"), 64, 128, e -> {
			int x = EarningWikiHomePageScreen.this.x;
			int y = EarningWikiHomePageScreen.this.y;
			if (true) {
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new EarningWikiHomePageButtonMessage(1, x, y, z));
				EarningWikiHomePageButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_crafting);
		imagebutton_smelting = new ImageButton(this.leftPos + 78, this.topPos + 17, 64, 64, 0, 0, 64, new ResourceLocation("skyzeradventure:textures/screens/atlas/imagebutton_smelting.png"), 64, 128, e -> {
			int x = EarningWikiHomePageScreen.this.x;
			int y = EarningWikiHomePageScreen.this.y;
			if (true) {
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new EarningWikiHomePageButtonMessage(2, x, y, z));
				EarningWikiHomePageButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_smelting);
		imagebutton_sword = new ImageButton(this.leftPos + 150, this.topPos + 17, 64, 64, 0, 0, 64, new ResourceLocation("skyzeradventure:textures/screens/atlas/imagebutton_sword.png"), 64, 128, e -> {
			int x = EarningWikiHomePageScreen.this.x;
			int y = EarningWikiHomePageScreen.this.y;
			if (true) {
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new EarningWikiHomePageButtonMessage(3, x, y, z));
				EarningWikiHomePageButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_sword);
		imagebutton_pickaxe = new ImageButton(this.leftPos + 222, this.topPos + 17, 64, 64, 0, 0, 64, new ResourceLocation("skyzeradventure:textures/screens/atlas/imagebutton_pickaxe.png"), 64, 128, e -> {
			int x = EarningWikiHomePageScreen.this.x;
			int y = EarningWikiHomePageScreen.this.y;
			if (true) {
				SkyzeradventureMod.PACKET_HANDLER.sendToServer(new EarningWikiHomePageButtonMessage(4, x, y, z));
				EarningWikiHomePageButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_pickaxe);
	}
}