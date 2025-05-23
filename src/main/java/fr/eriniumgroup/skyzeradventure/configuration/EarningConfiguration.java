package fr.eriniumgroup.skyzeradventure.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class EarningConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.ConfigValue<String> MINING;
	public static final ForgeConfigSpec.ConfigValue<String> CRAFTING;
	public static final ForgeConfigSpec.ConfigValue<String> SMELTING;
	public static final ForgeConfigSpec.ConfigValue<String> KILLING;
	static {
		BUILDER.push("Main");
		MINING = BUILDER.comment("List on items to won xp with mining ex : \"modid:blockid:xpearn:minlevel:maxlevel:{JsonFormat} %SEP% etc...\" for JsonFormat see wiki").define("Mining earning", "modid:blockid:xpearn:minlevel:maxlevel:{}");
		CRAFTING = BUILDER.comment("List on items to won xp with mining ex : \"modid:blockid:xpearn:minlevel:maxlevel:{JsonFormat} %SEP% etc...\" for JsonFormat see wiki").define("Crafting earning", "modid:blockid:xpearn:minlevel:maxlevel:{}");
		SMELTING = BUILDER.comment("List on items to won xp with mining ex : \"modid:blockid:xpearn:minlevel:maxlevel:{JsonFormat} %SEP% etc...\" for JsonFormat see wiki").define("Smelting Earning", "modid:blockid:xpearn:minlevel:maxlevel:{}");
		KILLING = BUILDER.comment("List on items to won xp with mining ex : \"modid:blockid:xpearn:minlevel:maxlevel:{JsonFormat} %SEP% etc...\" for JsonFormat see wiki").define("Killing Earning", "modid:blockid:xpearn:minlevel:maxlevel:{}");
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
