package fr.eriniumgroup.skyzeradventure.world.features;

import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.core.Vec3i;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Holder;

import java.util.Set;
import java.util.List;

import fr.eriniumgroup.skyzeradventure.world.features.configurations.StructureModFeatureConfiguration;
import fr.eriniumgroup.skyzeradventure.procedures.Cave2AdditionalGenerationConditionProcedure;

public class Cave2Feature extends StructureModFeature {
	private static Cave2Feature FEATURE = null;
	public static Holder<ConfiguredFeature<StructureModFeatureConfiguration, ?>> CONFIGURED_FEATURE = null;
	private static Holder<PlacedFeature> PLACED_FEATURE = null;

	public Cave2Feature() {
		super(StructureModFeatureConfiguration.CODEC);
	}

	public static Feature<?> feature() {
		FEATURE = new Cave2Feature();
		CONFIGURED_FEATURE = FeatureUtils.register("skyzeradventure:cave_2", FEATURE,
				new StructureModFeatureConfiguration(new ResourceLocation("skyzeradventure:cave2"), true, false, HolderSet.direct(Block::builtInRegistryHolder, Blocks.STRUCTURE_BLOCK), new Vec3i(0, -19, 0)));
		PLACED_FEATURE = PlacementUtils.register("skyzeradventure:cave_2", CONFIGURED_FEATURE, List.of(RarityFilter.onAverageOnceEvery(20), InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR)));
		return FEATURE;
	}

	public static Holder<PlacedFeature> placedFeature() {
		return PLACED_FEATURE;
	}

	public static final Set<ResourceLocation> GENERATE_BIOMES = Set.of(new ResourceLocation("skyzeradventure:corrupted"));

	@Override
	public boolean place(FeaturePlaceContext<StructureModFeatureConfiguration> context) {
		WorldGenLevel world = context.level();
		int x = context.origin().getX();
		int y = context.origin().getY();
		int z = context.origin().getZ();
		if (!Cave2AdditionalGenerationConditionProcedure.execute())
			return false;
		return super.place(context);
	}
}