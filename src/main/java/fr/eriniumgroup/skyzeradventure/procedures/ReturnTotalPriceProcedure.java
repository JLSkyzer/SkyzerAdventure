package fr.eriniumgroup.skyzeradventure.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.entity.Entity;

import java.util.HashMap;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;

public class ReturnTotalPriceProcedure {
	public static String execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
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
		}.convert(guistate.containsKey("textin:amount") ? (String) guistate.get("textin:amount") : "")
				* (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).tempitemprice) {
			color = "\u00A7a";
		} else {
			color = "\u00A74";
		}
		return "Total price : " + color + new java.text.DecimalFormat("#,###.####").format(new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(guistate.containsKey("textin:amount") ? (String) guistate.get("textin:amount") : "")
				* (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).tempitemprice) + "$";
	}
}
