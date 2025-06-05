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
		MINING = BUILDER.comment("List of items to won xp with mining ex : \"modid:blockid:xpearn:minlevel:maxlevel:{JsonFormat}; etc...\" put 0 for maxlevel to disable limit, for JsonFormat see wiki").define("Mining earning",
				"modid:blockid:120:0:0:{}");
		CRAFTING = BUILDER.comment("List of items to won xp with mining ex : \"modid:blockid:xpearn:minlevel:maxlevel:{JsonFormat}; etc...\" put 0 for maxlevel to disable limit, for JsonFormat see wiki").define("Crafting earning",
				"modid:blockid:120:0:0:{}");
		SMELTING = BUILDER.comment("List of items to won xp with mining ex : \"modid:blockid:xpearn:minlevel:maxlevel:{JsonFormat}; etc...\" put 0 for maxlevel to disable limit, for JsonFormat see wiki").define("Smelting Earning",
				"modid:blockid:120:0:0:{}");
		KILLING = BUILDER.comment("List of items to won xp with mining ex : \"modid:blockid:xpearn:minlevel:maxlevel:{JsonFormat}; etc...\" put 0 for maxlevel to disable limit, for JsonFormat see wiki").define("Killing Earning",
				"modid:blockid:120:0:0:{}");
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
