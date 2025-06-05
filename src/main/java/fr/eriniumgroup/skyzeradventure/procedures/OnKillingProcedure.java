package fr.eriniumgroup.skyzeradventure.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import fr.eriniumgroup.skyzeradventure.configuration.EarningConfiguration;

@Mod.EventBusSubscriber
public class OnKillingProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level, event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		execute(null, world, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		String temp = "";
		double whilecounter = 0;
		boolean jsonchecker = false;
		if (sourceentity instanceof ServerPlayer) {
			temp = EarningConfiguration.KILLING.get();
			if (temp.contains(ForgeRegistries.ENTITIES.getKey(entity.getType()).toString())) {
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
					}.returnValue(temp, (int) whilecounter, ";").contains(ForgeRegistries.ENTITIES.getKey(entity.getType()).toString())) {
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
				temp = temp.replace(ForgeRegistries.ENTITIES.getKey(entity.getType()).toString() + ":", "");
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
					if ((sourceentity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).RPGLevel >= new Object() {
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
						}.returnValue(temp, 2, ":")) == 0 || (sourceentity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).RPGLevel <= new Object() {
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
									double _setval = (sourceentity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).RPGXp + new Object() {
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
											* (sourceentity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).playerxpmultiplier;
									sourceentity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.RPGXp = _setval;
										capability.syncPlayerVariables(sourceentity);
									});
								}
								{
									String _setval = "\u00A76Crafting";
									sourceentity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.earning_text1 = _setval;
										capability.syncPlayerVariables(sourceentity);
									});
								}
								if ((sourceentity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).earningNotifTick > 0) {
									{
										double _setval = (sourceentity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).earning_lastvalue + new Object() {
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
										sourceentity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.earning_lastvalue = _setval;
											capability.syncPlayerVariables(sourceentity);
										});
									}
									{
										String _setval = "\u00A76+ " + (sourceentity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).earning_lastvalue + " xp";
										sourceentity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.earning_text2 = _setval;
											capability.syncPlayerVariables(sourceentity);
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
										sourceentity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.earning_lastvalue = _setval;
											capability.syncPlayerVariables(sourceentity);
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
										sourceentity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.earning_text2 = _setval;
											capability.syncPlayerVariables(sourceentity);
										});
									}
								}
								{
									String _setval = "";
									sourceentity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.earning_text3 = _setval;
										capability.syncPlayerVariables(sourceentity);
									});
								}
								{
									double _setval = 60;
									sourceentity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.earningNotifTick = _setval;
										capability.syncPlayerVariables(sourceentity);
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
