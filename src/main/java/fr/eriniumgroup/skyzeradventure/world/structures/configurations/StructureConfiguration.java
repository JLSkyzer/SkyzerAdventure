package fr.eriniumgroup.skyzeradventure.world.structures.configurations;

import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.core.Holder;

import java.util.Optional;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.Codec;

public record StructureConfiguration(Holder<StructureTemplatePool> startPool, int maxDepth, HeightProvider startHeight, Optional<Heightmap.Types> projectStartToHeightmap, int maxDistanceFromCenter) implements FeatureConfiguration {
	public static final Codec<StructureConfiguration> CODEC = RecordCodecBuilder.create(builder -> {
		return builder.group(StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(config -> {
			return config.startPool;
		}), Codec.intRange(0, 7).fieldOf("size").forGetter(config -> {
			return config.maxDepth;
		}), HeightProvider.CODEC.fieldOf("start_height").forGetter(config -> {
			return config.startHeight;
		}), Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(config -> {
			return config.projectStartToHeightmap;
		}), Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(config -> {
			return config.maxDistanceFromCenter;
		})).apply(builder, StructureConfiguration::new);
	});
}