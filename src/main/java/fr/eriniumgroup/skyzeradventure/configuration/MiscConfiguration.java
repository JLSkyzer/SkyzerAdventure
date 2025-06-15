package fr.eriniumgroup.skyzeradventure.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class MiscConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	public static final ForgeConfigSpec.ConfigValue<String> STAFFPERM;
	static {
		BUILDER.push("Permissions");
		STAFFPERM = BUILDER.comment("To have access to Staff's command about the mod, Put player's name separated by space").define("Staff Permissions", "");
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
