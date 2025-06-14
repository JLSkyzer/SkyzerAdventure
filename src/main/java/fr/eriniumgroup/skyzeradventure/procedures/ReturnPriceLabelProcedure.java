package fr.eriniumgroup.skyzeradventure.procedures;

public class ReturnPriceLabelProcedure {
	public static String execute() {
		return "Price for 1 FE : " + new java.text.DecimalFormat("#,###.####").format(ReturnEnergyPriceProcedure.execute()) + "$";
	}
}
