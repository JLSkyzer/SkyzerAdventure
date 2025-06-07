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
 LevelWikiScrollList
*/
package fr.eriniumgroup.skyzeradventure;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import fr.eriniumgroup.skyzeradventure.configuration.EarningConfiguration;
import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractSelectionList;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.ForgeRegistries;

import javax.swing.text.html.parser.Entity;
import java.text.DecimalFormat;
import java.util.*;

// Déclaration de la classe pour la liste scrollable
public class LevelWikiScrollList extends AbstractSelectionList<LevelWikiScrollList.Entry>  {
	private int selectedIndex = -1;
	private int height;
	private int width;
	private int y;
	private int x;
	private Minecraft minecraft;
	private Player entity;

	public LevelWikiScrollList(Minecraft minecraft, int x, int y, int itemWidth, int itemHeight, Player entity) {
		super(minecraft, itemWidth, itemHeight, y, y + itemHeight, 22); // 20 = hauteur d'un élément
		this.setLeftPos(x); // Définit la position horizontale
		this.height = itemHeight;
		this.width = itemWidth;
		this.x = x;
		this.y = y;
		this.minecraft = minecraft;
		this.entity = entity;

		SkyzeradventureModVariables.PlayerVariables playercap = entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables());
		String temp = "";

		switch (playercap.EarningWikiTarget){
			case "mining":
				//
				temp = EarningConfiguration.MINING.get();
				break;
			case "crafting":
				//
				temp = EarningConfiguration.CRAFTING.get();
				break;
			case "smelting":
				//
				temp = EarningConfiguration.SMELTING.get();
				break;
			case "killing":
				//
				temp = EarningConfiguration.KILLING.get();
				break;
			default:
				temp = EarningConfiguration.MINING.get();
		}

		// Ajoute des entrées
		Map<String, Double> hashmap = new HashMap<>();
		double whilecounter = 0;
		for (int index0 = 0; index0 < (int) new Object() {
			private int returnSize(String text, String separator) {
				String[] resultTxt = (text).split(separator);
				return resultTxt.length;
			}
		}.returnSize(temp, ";"); index0++) {
			hashmap.put(new Object() {
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
			}.returnValue(temp, (int) whilecounter, ";"), (double) new Object() {
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
			}.returnValue(new Object() {
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
			}.returnValue(temp, (int) whilecounter, ";"), 3, ":")));
			whilecounter = whilecounter + 1;
		}
		{
			// On crée une liste des entrées de la map
			List<Map.Entry<String, Double>> entries = new ArrayList<>(hashmap.entrySet());
			// On trie la liste par valeur croissante
			entries.sort(Map.Entry.comparingByValue());
			// Affiche le résultat trié
			for (Map.Entry<String, Double> stringDoubleEntry : entries) {
				// Executor... stringDoubleEntry.getKey() (String) and stringDoubleEntry.getValue() (Double)
				this.addEntry(new Entry(stringDoubleEntry.getKey()));
			}
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
			// Dessine un fond pour chaque élément (facultatif)
			//fill(poseStack, x, y, x + itemWidth, y + itemHeight, isSelected ? 0xFFAAAAAA : 0xFF000000);

			String temp = this.text;
			int text = 0;
			Boolean cant = false;
			String id = (new Object() {
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
			}.returnValue(temp, 0, ":") + ":" + new Object() {
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
			}.returnValue(temp, 1, ":")).replace(" ", "");

			if (new Object() {
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
			}.returnValue(temp, 2, ":")) > 0) {
				if ((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).RPGLevel >= new Object() {
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
				}.returnValue(temp, 3, ":"))) {
					if (new Object() {
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
					}.returnValue(temp, 4, ":")) == 0 || (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).RPGLevel <= new Object() {
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
					}.returnValue(temp, 4, ":"))) {
						text = ARGBToInt.ARGBToInt(255, 101, 216, 78);
					} else {
						text = ARGBToInt.ARGBToInt(255, 214, 45, 36);
						cant = true;
					}
				} else {
					text = ARGBToInt.ARGBToInt(255, 214, 45, 36);
					cant = true;
				}
			}

			ItemStack item = Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(((new Object() {
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
            }.returnValue(temp, 0, ":") + ":" + new Object() {
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
            }.returnValue(temp, 1, ":")).replace(" ", "")).toLowerCase(Locale.ENGLISH)))).getDefaultInstance();
			Double xp = new Object() {
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
			}.returnValue(temp, 2, ":"));
			Double minLevel = new Object() {
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
			}.returnValue(temp, 3, ":"));
			Double maxLevel = new Object() {
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
			}.returnValue(temp, 4, ":"));

			SkyzeradventureModVariables.PlayerVariables playercap = entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables());

			fill(poseStack, x + 1, y + 1, x + getRowWidth() - 1, y + 1 + 20, ARGBToInt.ARGBToInt(255, 61, 61, 61));

			// Juste avant le render d’item
			if (!playercap.EarningWikiTarget.contains("killing")){
				RenderSystem.enableDepthTest();
				minecraft.getItemRenderer().renderAndDecorateItem(item, x + 3, y + 3);
				RenderSystem.disableDepthTest();
			}else {
				if (id.contains("minecraft:player")){
					item = Items.PLAYER_HEAD.getDefaultInstance();
				}else{
					EntityType<?> type = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(id));
					if (type == null) item = Items.BARRIER.getDefaultInstance();

					for (Item items : ForgeRegistries.ITEMS) {
						if (items instanceof SpawnEggItem egg && egg.getType(null) == type) {
							item = egg.getDefaultInstance();
						}
					}
				}

				RenderSystem.enableDepthTest();
				minecraft.getItemRenderer().renderAndDecorateItem(item, x + 3, y + 3);
				RenderSystem.disableDepthTest();
			}



			// Min level
			Minecraft.getInstance().font.draw(poseStack, new TextComponent(new DecimalFormat("##").format(minLevel)), x + 24 + 60, y + 1 + 10 - 3, text);
			// Max level
			Minecraft.getInstance().font.draw(poseStack, new TextComponent(new DecimalFormat("##").format(maxLevel)), x + 24 + 120, y + 1 + 10 - 3, text);
			// XP amount
			int XpAmountX = x + 1 + getRowWidth() - 1 - Minecraft.getInstance().font.width(new DecimalFormat("#,###.##").format(xp) + " XP") - 5;
			Minecraft.getInstance().font.draw(poseStack, new TextComponent(new DecimalFormat("#,###.##").format(xp) + " XP"), XpAmountX, y + 1 + 10 - 3, text);

			if (cant){
				fill(poseStack, x + 1, y + 1, x + getRowWidth() - 1, y + 1 + 20, ARGBToInt.ARGBToInt(125, 0, 0, 0));
			}

			// ----- Tooltip au hover -----
			// On veut le tooltip si la souris est dans le carré de l'item (16x16 px)
			Screen gui = Minecraft.getInstance().screen;
			if (gui != null && mouseX >= x + 3 && mouseX <= x + 3 + 16 && mouseY >= y + 3 && mouseY <= y + 3 + 16) {
				List<Component> tooltipLines = item.getTooltipLines(
						Minecraft.getInstance().player,
						Minecraft.getInstance().options.advancedItemTooltips ? TooltipFlag.Default.ADVANCED : TooltipFlag.Default.NORMAL
				);
				gui.renderTooltip(poseStack, tooltipLines, Optional.empty(), mouseX, mouseY, item);
			}
		}

		@Override
		public boolean mouseClicked(double mouseX, double mouseY, int button) {
			//System.out.println("Cliqué sur : " + this.text);
			return true;
		}
	}
}

