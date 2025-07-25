package fr.eriniumgroup.skyzeradventure.world.structures;

import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.core.BlockPos;

import java.util.Random;
import java.util.Optional;

import fr.eriniumgroup.skyzeradventure.world.structures.configurations.StructureConfiguration;

public class BaseStructure extends StructureFeature<StructureConfiguration> {
	public BaseStructure() {
		super(StructureConfiguration.CODEC, BaseStructure::createPiecesGenerator);
	}

	@Override
	public GenerationStep.Decoration step() {
		return null;
	}

	public static Optional<PieceGenerator<StructureConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<StructureConfiguration> context) {
		BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);
		if (!context.config().projectStartToHeightmap().isEmpty()) {
			int topLandY = context.chunkGenerator().getFirstFreeHeight(blockpos.getX(), blockpos.getZ(), context.config().projectStartToHeightmap().get(), context.heightAccessor());
			blockpos = blockpos.atY(topLandY + context.config().startHeight().sample(new Random(), new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor())));
		} else {
			blockpos = blockpos.atY(context.config().startHeight().sample(new Random(), new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor())));
		}
		Pools.bootstrap();
		JigsawConfiguration jigsawConfig = new JigsawConfiguration(context.config().startPool(), context.config().maxDepth());
		PieceGeneratorSupplier.Context<JigsawConfiguration> jigsawContext = new PieceGeneratorSupplier.Context<>(context.chunkGenerator(), context.biomeSource(), context.seed(), context.chunkPos(), jigsawConfig, context.heightAccessor(),
				context.validBiome(), context.structureManager(), context.registryAccess());
		Optional<PieceGenerator<JigsawConfiguration>> jigsawResult = JigsawPlacement.addPieces(jigsawContext, PoolElementStructurePiece::new, blockpos, false, false);
		return (Optional<PieceGenerator<StructureConfiguration>>) (Optional<?>) jigsawResult;
	}
}