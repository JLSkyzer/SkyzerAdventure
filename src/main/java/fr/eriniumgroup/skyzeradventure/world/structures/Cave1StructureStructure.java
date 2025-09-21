package fr.eriniumgroup.skyzeradventure.world.structures;

import net.minecraft.world.level.levelgen.GenerationStep;

public class Cave1StructureStructure extends SkyzeradventureModStructureBase {
	@Override
	public GenerationStep.Decoration step() {
		return GenerationStep.Decoration.UNDERGROUND_ORES;
	}
}