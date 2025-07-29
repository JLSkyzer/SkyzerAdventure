package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.util.Mth;

import java.util.Random;

public class Cave2AdditionalGenerationConditionProcedure {
	public static boolean execute() {
		return Mth.nextInt(new Random(), 1, 20) == 5;
	}
}