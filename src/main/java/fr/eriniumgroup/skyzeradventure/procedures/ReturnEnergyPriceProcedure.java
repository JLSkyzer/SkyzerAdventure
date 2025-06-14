package fr.eriniumgroup.skyzeradventure.procedures;

import fr.eriniumgroup.skyzeradventure.configuration.ShopConfigConfiguration;

public class ReturnEnergyPriceProcedure {
	public static double execute() {
		return (double) ShopConfigConfiguration.FEPRICE.get();
	}
}
