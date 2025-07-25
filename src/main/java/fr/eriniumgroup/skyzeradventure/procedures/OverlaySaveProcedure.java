package fr.eriniumgroup.skyzeradventure.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModMenus;

public class OverlaySaveProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).overlayConfigTarget).equals("level")) {
			{
				double _setval = new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert((entity instanceof Player _entity0 && _entity0.containerMenu instanceof SkyzeradventureModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "xVal", "") : "");
				entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.LevelOverlayX = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert((entity instanceof Player _entity1 && _entity1.containerMenu instanceof SkyzeradventureModMenus.MenuAccessor _menu1) ? _menu1.getMenuState(0, "yVal", "") : "");
				entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.LevelOverlayY = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if (((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).overlayConfigTarget).equals("earning")) {
			{
				double _setval = new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert((entity instanceof Player _entity2 && _entity2.containerMenu instanceof SkyzeradventureModMenus.MenuAccessor _menu2) ? _menu2.getMenuState(0, "xVal", "") : "");
				entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.earningNotifX = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert((entity instanceof Player _entity3 && _entity3.containerMenu instanceof SkyzeradventureModMenus.MenuAccessor _menu3) ? _menu3.getMenuState(0, "yVal", "") : "");
				entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.earningNotifY = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		ConfiguratorThisGUIIsOpenedProcedure.execute(entity);
	}
}