package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.core.Registry;

import java.util.Objects;

public class EntityOutputProcedure {
	public static void execute() {
		WriteOutputProcedure.execute("");
		WriteOutputProcedure.execute("===== START ENTITY OUTPUT =====");
		WriteOutputProcedure.execute("");
		Registry.ENTITY_TYPE.stream().forEach(entityType -> {
			//System.out.println("EntityType : " + entityType.getDescriptionId() + " / " + entityType.getRegistryName());
			WriteOutputProcedure.execute(Objects.requireNonNull(entityType.getRegistryName()).toString());
			// ... Ton code ici
		});
		WriteOutputProcedure.execute("");
		WriteOutputProcedure.execute("===== FINISH ENTITY OUTPUT =====");
		System.out.println("FINISH");
	}
}
