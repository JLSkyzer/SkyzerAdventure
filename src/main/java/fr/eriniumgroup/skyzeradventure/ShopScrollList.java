/**
 * The code of this mod element is always locked.
 *
 * You can register new events in this class too.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside fr.eriniumgroup.skyzeradventure as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 *
 * This class will be added in the mod root package.
*/
package fr.eriniumgroup.skyzeradventure;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import fr.eriniumgroup.skyzeradventure.configuration.ShopListingConfiguration;
import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import fr.eriniumgroup.skyzeradventure.procedures.HaveItemProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.OpenBuyPageProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.OpenSellingPageProcedure;
import fr.eriniumgroup.skyzeradventure.world.inventory.ShopBuyMenu;
import fr.eriniumgroup.skyzeradventure.world.inventory.ShopSellMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractSelectionList;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.registries.ForgeRegistries;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ShopScrollList extends AbstractSelectionList<ShopScrollList.Entry> {
	private int selectedIndex = -1;
	private int height;
	private int width;
	private int y;
	private int x;
	private Minecraft minecraft;
	private HashMap<String, Object> guistate;
	private Entity entity;
	private String type;

	public ShopScrollList(Minecraft minecraft, int x, int y, int itemWidth, int itemHeight, Entity entity) {
		super(minecraft, itemWidth, itemHeight, y, y + itemHeight, 24); // 20 = hauteur d'un élément
		this.setLeftPos(x); // Définit la position horizontale
		this.height = itemHeight;
		this.width = itemWidth;
		this.x = x;
		this.y = y;
		this.minecraft = minecraft;
		this.entity = entity;

		String temp = "";
		String secondtemp = "";
		double tempindex = 0;
		if (entity instanceof Player _plr ? _plr.containerMenu instanceof ShopBuyMenu : false) {
			temp = ShopListingConfiguration.BUY_LISTING.get();
			guistate = ShopBuyMenu.guistate;
			this.type = "buy";
		} else if (entity instanceof Player _plr ? _plr.containerMenu instanceof ShopSellMenu : false) {
			temp = ShopListingConfiguration.SELL_LISTING.get();
			guistate = ShopSellMenu.guistate;
			this.type = "sell";
		}
		for (int index0 = 0; index0 < (int) new Object() {
			private int returnSize(String text, String separator) {
				String[] resultTxt = (text).split(separator);
				return resultTxt.length;
			}
		}.returnSize(temp, ";"); index0++) {
			secondtemp = new Object() {
				private String returnValue(String string, int Index, String sep) {
					try {
						return ((string).split(sep)[Index]);
						// Utilisez account ici
					} catch (ArrayIndexOutOfBoundsException e) {
						// Gérer l'erreur ici, par exemple :
						System.out.println("Valeur null !");
						return "";
					}
				}
			}.returnValue(temp, (int) tempindex, ";");
			if (!(guistate.containsKey("textin:search") ? (String) guistate.get("textin:search") : "").isEmpty()) {
				if (secondtemp.contains(guistate.containsKey("textin:search") ? (String) guistate.get("textin:search") : "") || (new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((((new Object() {
					private String returnValue(String string, int Index, String sep) {
						try {
							return ((string).split(sep)[Index]);
							// Utilisez account ici
						} catch (ArrayIndexOutOfBoundsException e) {
							// Gérer l'erreur ici, par exemple :
							System.out.println("Valeur null !");
							return "";
						}
					}
				}.returnValue(secondtemp, 0, ":") + ":" + new Object() {
					private String returnValue(String string, int Index, String sep) {
						try {
							return ((string).split(sep)[Index]);
							// Utilisez account ici
						} catch (ArrayIndexOutOfBoundsException e) {
							// Gérer l'erreur ici, par exemple :
							System.out.println("Valeur null !");
							return "";
						}
					}
				}.returnValue(secondtemp, 1, ":")).replace(" ", ""))).toLowerCase(java.util.Locale.ENGLISH)))).getDisplayName().getString()).contains(guistate.containsKey("textin:search") ? (String) guistate.get("textin:search") : "")) {
					this.addEntry(new Entry(secondtemp));
				}
			} else {
				ItemStack tempItem = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((((new Object() {
					private String returnValue(String string, int Index, String sep) {
						try {
							return ((string).split(sep)[Index]);
							// Utilisez account ici
						} catch (ArrayIndexOutOfBoundsException e) {
							// Gérer l'erreur ici, par exemple :
							System.out.println("Valeur null !");
							return "";
						}
					}
				}.returnValue(secondtemp, 0, ":") + ":" + new Object() {
					private String returnValue(String string, int Index, String sep) {
						try {
							return ((string).split(sep)[Index]);
							// Utilisez account ici
						} catch (ArrayIndexOutOfBoundsException e) {
							// Gérer l'erreur ici, par exemple :
							System.out.println("Valeur null !");
							return "";
						}
					}
				}.returnValue(secondtemp, 1, ":")).replace(" ", ""))).toLowerCase(java.util.Locale.ENGLISH))));

				if (!tempItem.isEmpty() || tempItem.getItem() != Items.AIR) {
					// Air ou vide
					this.addEntry(new Entry(secondtemp));
				}
			}
			tempindex = tempindex + 1;
		}
	}

	// Supprime tout fond indésirable
	@Override
	protected void renderBackground(PoseStack poseStack) {
		// Désactiver le rendu de la dirt en fond
		setRenderBackground(false);
		setRenderTopAndBottom(false);
	}


	// To make content dont exit the scroll list's area
	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
		// Convertir les coordonnées pour le Scissor Test
		int scissorX = (int) (this.getLeft() * this.minecraft.getWindow().getGuiScale());
		int scissorY = (int) ((this.minecraft.getWindow().getGuiScaledHeight() - this.getBottom()) * this.minecraft.getWindow().getGuiScale());
		int scissorWidth = (int) (this.getWidth() * this.minecraft.getWindow().getGuiScale());
		int scissorHeight = (int) (this.getHeight() * this.minecraft.getWindow().getGuiScale());

		// Activer le Scissor Test
		RenderSystem.enableScissor(scissorX, scissorY, scissorWidth, scissorHeight);

		// Appeler la méthode parente pour dessiner la liste
		super.render(poseStack, mouseX, mouseY, partialTick);

		// Désactiver le Scissor Test après le rendu
		RenderSystem.disableScissor();
	}

	// Ajuste la largeur des éléments (pour les aligner avec la scrollbar)
	@Override
	public int getRowWidth() {
		return this.getWidth() - 12; // Largeur de la liste moins un décalage pour la scrollbar
	}

	// Ajuste la position de la scrollbar (pour être alignée à droite)
	@Override
	protected int getScrollbarPosition() {
		return this.getLeft() + this.getWidth() - 6; // Scrollbar 6 pixels à l'intérieur du bord droit
	}

	// Implémentation de la méthode obligatoire pour l'accessibilité
	@Override
	public void updateNarration(NarrationElementOutput narrationElementOutput) {
		// Implémentation vide pour l'instant
		// Si nécessaire, ajoute une description comme : narrationElementOutput.add(NarratedElementType.TITLE, "Description ici");
	}

	// Gestionnaire pour dessiner chaque entrée
	protected class Entry extends AbstractSelectionList.Entry<Entry> {
		private final String text;

		// Constructeur de l'entrée avec un texte à afficher
		public Entry(String text) {
			this.text = text;
		}

		/*// Supprime tout fond indésirable
		protected void renderBackground(PoseStack poseStack) {
			// Laisse vide pour ne rien dessiner en arrière-plan
			setRenderBackground(false);
			setRenderTopAndBottom(false);
		}*/

		@Override
		public void render(PoseStack poseStack, int index, int y, int x, int itemWidth, int itemHeight, int mouseX, int mouseY, boolean isSelected, float partialTick) {
			ItemStack tempItem = ItemStack.EMPTY;
			double tempPrice = 0;
			SkyzeradventureModVariables.PlayerVariables playercap = entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables());

			tempItem = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((((new Object() {
				private String returnValue(String string, int Index, String sep) {
					try {
						return ((string).split(sep)[Index]);
						// Utilisez account ici
					} catch (ArrayIndexOutOfBoundsException e) {
						// Gérer l'erreur ici, par exemple :
						System.out.println("Valeur null !");
						return "";
					}
				}
			}.returnValue(this.text, 0, ":") + ":" + new Object() {
				private String returnValue(String string, int Index, String sep) {
					try {
						return ((string).split(sep)[Index]);
						// Utilisez account ici
					} catch (ArrayIndexOutOfBoundsException e) {
						// Gérer l'erreur ici, par exemple :
						System.out.println("Valeur null !");
						return "";
					}
				}
			}.returnValue(this.text, 1, ":")).replace(" ", ""))).toLowerCase(java.util.Locale.ENGLISH))));
			tempPrice = new Object() {
				double convert(String s) {
					try {
						return Double.parseDouble(s.trim());
					} catch (Exception e) {
					}
					return 0;
				}
			}.convert(new Object() {
				private String returnValue(String string, int Index, String sep) {
					try {
						return ((string).split(sep)[Index]);
						// Utilisez account ici
					} catch (ArrayIndexOutOfBoundsException e) {
						// Gérer l'erreur ici, par exemple :
						System.out.println("Valeur null !");
						return "";
					}
				}
			}.returnValue(this.text, 2, ":"));

			int price;
			Boolean cant = false;
			if (playercap.shop_money >= tempPrice){
				price = ARGBToInt.ARGBToInt(255, 30, 188, 53);
			}else{
				if (Objects.equals(type, "buy")){
					price = ARGBToInt.ARGBToInt(255, 188, 54, 39);
					cant = true;
				}else{
					price = ARGBToInt.ARGBToInt(255, 30, 188, 53);
				}
			}

			// Dessine un fond pour chaque élément (facultatif)
			int background = ARGBToInt.ARGBToInt(255, 61, 61, 61);
			int backgroundseletect = ARGBToInt.ARGBToInt(255, 86, 86, 86);

			fill(poseStack, x + 1, y + 1, x + itemWidth - 1, y + itemHeight - 1, isSelected ? backgroundseletect : background);

			// Dessine le texte, avec un léger décalage vers la droite
			RenderSystem.enableDepthTest();
			minecraft.getItemRenderer().renderAndDecorateItem(tempItem, x + 3, y + 2);
			RenderSystem.disableDepthTest();

			Minecraft.getInstance().font.draw(poseStack, tempItem.getDisplayName(), x + (itemWidth / 2) - (Minecraft.getInstance().font.width(tempItem.getDisplayName()) / 2), y + (itemHeight / 2) - 1 - 3, ARGBToInt.ARGBToInt(255, 255, 255, 255));

			String pricetext = "Price : " + new DecimalFormat("#,###.####").format(tempPrice) + "$";
			int priceWidth = Minecraft.getInstance().font.width(pricetext) + 5;
			Minecraft.getInstance().font.draw(poseStack, pricetext, x + itemWidth - priceWidth, y + (itemHeight / 2) - 1 - 3, price);


			if (cant){
				fill(poseStack, x + 1, y + 1, x + itemWidth - 1, y + itemHeight - 1, ARGBToInt.ARGBToInt(125, 0, 0, 0));
			}

			// ----- Tooltip au hover -----
			// On veut le tooltip si la souris est dans le carré de l'item (16x16 px)
			Screen gui = Minecraft.getInstance().screen;
			if (gui != null && mouseX >= x + 3 && mouseX <= x + 3 + 16 && mouseY >= y + 2 && mouseY <= y + 2 + 16) {
				List<Component> tooltipLines = tempItem.getTooltipLines(
						Minecraft.getInstance().player,
						Minecraft.getInstance().options.advancedItemTooltips ? TooltipFlag.Default.ADVANCED : TooltipFlag.Default.NORMAL
				);
				gui.renderTooltip(poseStack, tooltipLines, Optional.empty(), mouseX, mouseY, tempItem);
			}
		}

		@Override
		public boolean mouseClicked(double mouseX, double mouseY, int button) {
			ItemStack tempItem = ItemStack.EMPTY;
			double tempPrice = 0;
			SkyzeradventureModVariables.PlayerVariables playercap = entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables());

			tempItem = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((((new Object() {
				private String returnValue(String string, int Index, String sep) {
					try {
						return ((string).split(sep)[Index]);
						// Utilisez account ici
					} catch (ArrayIndexOutOfBoundsException e) {
						// Gérer l'erreur ici, par exemple :
						System.out.println("Valeur null !");
						return "";
					}
				}
			}.returnValue(this.text, 0, ":") + ":" + new Object() {
				private String returnValue(String string, int Index, String sep) {
					try {
						return ((string).split(sep)[Index]);
						// Utilisez account ici
					} catch (ArrayIndexOutOfBoundsException e) {
						// Gérer l'erreur ici, par exemple :
						System.out.println("Valeur null !");
						return "";
					}
				}
			}.returnValue(this.text, 1, ":")).replace(" ", ""))).toLowerCase(java.util.Locale.ENGLISH))));
			tempPrice = new Object() {
				double convert(String s) {
					try {
						return Double.parseDouble(s.trim());
					} catch (Exception e) {
					}
					return 0;
				}
			}.convert(new Object() {
				private String returnValue(String string, int Index, String sep) {
					try {
						return ((string).split(sep)[Index]);
						// Utilisez account ici
					} catch (ArrayIndexOutOfBoundsException e) {
						// Gérer l'erreur ici, par exemple :
						System.out.println("Valeur null !");
						return "";
					}
				}
			}.returnValue(this.text, 2, ":"));

			if (playercap.shop_money >= tempPrice && type.equals("buy") || type.equals("sell") && HaveItemProcedure.execute(entity, tempItem)){
				// EXECUTION
				if (type.equals("buy")){
					SkyzeradventureMod.PACKET_HANDLER.sendToServer(new SkyzerAdventureMainNetwork(0, (int) entity.getX(), (int) entity.getY(), (int) entity.getZ(), "", tempItem, tempPrice));
					SkyzerAdventureMainNetwork.handleButtonAction((Player) entity, 0, (int) entity.getX(), (int) entity.getY(), (int) entity.getZ(), "", tempItem, tempPrice);
				} else if (type.equals("sell")) {
					SkyzeradventureMod.PACKET_HANDLER.sendToServer(new SkyzerAdventureMainNetwork(1, (int) entity.getX(), (int) entity.getY(), (int) entity.getZ(), "", tempItem, tempPrice));
					SkyzerAdventureMainNetwork.handleButtonAction((Player) entity, 1, (int) entity.getX(), (int) entity.getY(), (int) entity.getZ(), "", tempItem, tempPrice);
				}
			}
			System.out.println(type);
			return true;
		}
	}
}
