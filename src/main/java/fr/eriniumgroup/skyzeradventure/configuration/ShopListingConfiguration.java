package fr.eriniumgroup.skyzeradventure.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class ShopListingConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.ConfigValue<String> BUY_LISTING;
	public static final ForgeConfigSpec.ConfigValue<String> SELL_LISTING;
	static {
		BUILDER.push("Listing");
		BUY_LISTING = BUILDER.comment("Follow this format : \"modid:id:price; modid:id:price\"").define("Buy Listing", "modid:id1:0; modid:id2:120");
		SELL_LISTING = BUILDER.comment("Follow this format : \"modid:id:price; modid:id:price\"").define("Sell Listing", "modid:id1:0; modid:id2:120");
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
