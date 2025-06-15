package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedWriter;

import fr.eriniumgroup.skyzeradventure.configuration.ShopListingConfiguration;

public class FCShopListingTOMLProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		File file = new File("");
		double index = 0;
		String temptext = "";
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/filechecker/"), File.separator + "ShopListingPatch.txt");
		if (!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		try {
			FileWriter filewriter = new FileWriter(file);
			BufferedWriter filebw = new BufferedWriter(filewriter);
			{
				filebw.write("Remove last dot comma");
				filebw.newLine();
			}
			for (int index0 = 0; index0 < (int) new Object() {
				private int returnSize(String text, String separator) {
					String[] resultTxt = (text).split(separator);
					return resultTxt.length;
				}
			}.returnSize((ShopListingConfiguration.BUY_LISTING.get()), ";"); index0++) {
				if (!(ForgeRegistries.ITEMS.getValue(new ResourceLocation((((new Object() {
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
				}.returnValue((ShopListingConfiguration.BUY_LISTING.get()), (int) index, ";"), 0, ":") + ":" + new Object() {
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
				}.returnValue((ShopListingConfiguration.BUY_LISTING.get()), (int) index, ";"), 1, ":")).replace(" ", ""))).toLowerCase(java.util.Locale.ENGLISH))) == ItemStack.EMPTY.getItem())
						&& !(ForgeRegistries.ITEMS.getValue(new ResourceLocation((((new Object() {
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
						}.returnValue((ShopListingConfiguration.BUY_LISTING.get()), (int) index, ";"), 0, ":") + ":" + new Object() {
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
						}.returnValue((ShopListingConfiguration.BUY_LISTING.get()), (int) index, ";"), 1, ":")).replace(" ", ""))).toLowerCase(java.util.Locale.ENGLISH))) == Blocks.AIR.asItem())) {
					temptext = temptext + "" + new Object() {
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
					}.returnValue((ShopListingConfiguration.BUY_LISTING.get()), (int) index, ";") + "; ";
				}
				index = index + 1;
			}
			{
				filebw.write(("Buy : " + temptext));
				filebw.newLine();
			}
			index = 0;
			temptext = "";
			for (int index1 = 0; index1 < (int) new Object() {
				private int returnSize(String text, String separator) {
					String[] resultTxt = (text).split(separator);
					return resultTxt.length;
				}
			}.returnSize((ShopListingConfiguration.SELL_LISTING.get()), ";"); index1++) {
				if (!(ForgeRegistries.ITEMS.getValue(new ResourceLocation((((new Object() {
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
				}.returnValue((ShopListingConfiguration.SELL_LISTING.get()), (int) index, ";"), 0, ":") + ":" + new Object() {
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
				}.returnValue((ShopListingConfiguration.SELL_LISTING.get()), (int) index, ";"), 1, ":")).replace(" ", ""))).toLowerCase(java.util.Locale.ENGLISH))) == ItemStack.EMPTY.getItem())
						&& !(ForgeRegistries.ITEMS.getValue(new ResourceLocation((((new Object() {
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
						}.returnValue((ShopListingConfiguration.SELL_LISTING.get()), (int) index, ";"), 0, ":") + ":" + new Object() {
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
						}.returnValue((ShopListingConfiguration.SELL_LISTING.get()), (int) index, ";"), 1, ":")).replace(" ", ""))).toLowerCase(java.util.Locale.ENGLISH))) == Blocks.AIR.asItem())) {
					temptext = temptext + "" + new Object() {
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
					}.returnValue((ShopListingConfiguration.SELL_LISTING.get()), (int) index, ";") + "; ";
				}
				index = index + 1;
			}
			{
				filebw.write(("Sell : " + temptext));
				filebw.newLine();
			}
			filebw.close();
			filewriter.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(new TextComponent("Finish"), false);
	}
}
