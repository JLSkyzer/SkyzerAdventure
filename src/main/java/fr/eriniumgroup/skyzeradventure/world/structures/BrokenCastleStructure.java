package fr.eriniumgroup.skyzeradventure.world.structures;

import net.minecraft.world.level.levelgen.GenerationStep;

public class BrokenCastleStructure extends BaseStructure {
	@Override
	public GenerationStep.Decoration step() {
		return GenerationStep.Decoration.SURFACE_STRUCTURES;
	}
}