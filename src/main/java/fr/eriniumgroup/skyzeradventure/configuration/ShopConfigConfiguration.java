package fr.eriniumgroup.skyzeradventure.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class ShopConfigConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	public static final ForgeConfigSpec.ConfigValue<Double> FEPRICE;
	static {
		BUILDER.push("Global Config");
		FEPRICE = BUILDER.comment("The price for 1 FE into the energy seller").define("One FE price", (double) 0.0012);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
