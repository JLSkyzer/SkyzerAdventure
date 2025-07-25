package fr.eriniumgroup.skyzeradventure.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModMenus;

public class ReturnTotalPriceProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		String color = "";
		if ((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).shop_money >= new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((entity instanceof Player _entity0 && _entity0.containerMenu instanceof SkyzeradventureModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "amount", "") : "")
				* (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).tempitemprice) {
			color = "\u00A7a";
		} else {
			color = "\u00A74";
		}
		return "Total price : " + color + (new java.text.DecimalFormat("#,###.####").format(new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((entity instanceof Player _entity1 && _entity1.containerMenu instanceof SkyzeradventureModMenus.MenuAccessor _menu1) ? _menu1.getMenuState(0, "amount", "") : "")
				* (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).tempitemprice)) + "$";
	}
}