package fr.eriniumgroup.skyzeradventure.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.world.BlockEvent;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import fr.eriniumgroup.skyzeradventure.configuration.EarningConfiguration;

@Mod.EventBusSubscriber
public class OnMiningProcedure {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		execute(event, event.getWorld(), event.getState(), event.getPlayer());
	}

	public static void execute(LevelAccessor world, BlockState blockstate, Entity entity) {
		execute(null, world, blockstate, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		String temp = "";
		double whilecounter = 0;
		boolean jsonchecker = false;
		if (entity instanceof ServerPlayer) {
			temp = EarningConfiguration.MINING.get();
			if (temp.contains(ForgeRegistries.BLOCKS.getKey(blockstate.getBlock()).toString())) {
				for (int index0 = 0; index0 < (int) new Object() {
					private int returnSize(String text, String separator) {
						String[] resultTxt = (text).split(separator);
						return resultTxt.length;
					}
				}.returnSize(temp, ";"); index0++) {
					if (new Object() {
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
					}.returnValue(temp, (int) whilecounter, ";").contains(ForgeRegistries.BLOCKS.getKey(blockstate.getBlock()).toString())) {
						temp = new Object() {
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
						}.returnValue(temp, (int) whilecounter, ";");
						break;
					}
					whilecounter = whilecounter + 1;
				}
				temp = temp.replace(ForgeRegistries.BLOCKS.getKey(blockstate.getBlock()).toString() + ":", "");
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
				}.returnValue(temp, 0, ":")) > 0) {
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
					}.returnValue(temp, 1, ":"))) {
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
						}.returnValue(temp, 2, ":")) == 0 || (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).RPGLevel <= new Object() {
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
						}.returnValue(temp, 2, ":"))) {
							if (true) {
								{
									double _setval = (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).RPGXp + new Object() {
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
									}.returnValue(temp, 0, ":")) * SkyzeradventureModVariables.MapVariables.get(world).serverxpmultiplier
											* (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).playerxpmultiplier;
									entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.RPGXp = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
								{
									String _setval = "\u00A76Mining";
									entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.earning_text1 = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
								if ((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).earningNotifTick > 0) {
									{
										double _setval = (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).earning_lastvalue + new Object() {
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
										}.returnValue(temp, 0, ":"));
										entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.earning_lastvalue = _setval;
											capability.syncPlayerVariables(entity);
										});
									}
									{
										String _setval = "\u00A76+ " + (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).earning_lastvalue + " xp";
										entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.earning_text2 = _setval;
											capability.syncPlayerVariables(entity);
										});
									}
								} else {
									{
										double _setval = new Object() {
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
										}.returnValue(temp, 0, ":"));
										entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.earning_lastvalue = _setval;
											capability.syncPlayerVariables(entity);
										});
									}
									{
										String _setval = "\u00A76+ " + new Object() {
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
										}.returnValue(temp, 0, ":")) + " xp";
										entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.earning_text2 = _setval;
											capability.syncPlayerVariables(entity);
										});
									}
								}
								{
									String _setval = "";
									entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.earning_text3 = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
								{
									double _setval = 60;
									entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.earningNotifTick = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
							}
						}
					}
				}
			}
		}
	}
}
