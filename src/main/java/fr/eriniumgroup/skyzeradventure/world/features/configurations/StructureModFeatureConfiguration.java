package fr.eriniumgroup.skyzeradventure.world.features.configurations;

import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.block.Block;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Vec3i;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.Registry;
import net.minecraft.core.HolderSet;
import net.minecraft.client.Minecraft;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.Codec;

public record StructureModFeatureConfiguration(ResourceLocation structure, boolean randomRotation, boolean randomMirror, HolderSet<Block> ignoredBlocks, Vec3i offset) implements FeatureConfiguration {
	public StructureModFeatureConfiguration(ResourceLocation structure, boolean randomRotation, boolean randomMirror, TagKey<Block> ignoredBlocks, Vec3i offset) {
		this(structure, randomRotation, randomMirror, Minecraft.getInstance().level.registryAccess().registryOrThrow(Registry.BLOCK_REGISTRY).getTag(ignoredBlocks).<HolderSet<Block>>map(tag -> tag).orElseGet(() -> HolderSet.<Block>direct()), offset);
	}

	public static final Codec<StructureModFeatureConfiguration> CODEC = RecordCodecBuilder.create(builder -> {
		return builder.group(ResourceLocation.CODEC.fieldOf("structure").forGetter(config -> {
			return config.structure;
		}), Codec.BOOL.fieldOf("random_rotation").orElse(false).forGetter(config -> {
			return config.randomRotation;
		}), Codec.BOOL.fieldOf("random_mirror").orElse(false).forGetter(config -> {
			return config.randomMirror;
		}), RegistryCodecs.homogeneousList(Registry.BLOCK_REGISTRY).fieldOf("ignored_blocks").forGetter(config -> {
			return config.ignoredBlocks;
		}), Vec3i.offsetCodec(48).optionalFieldOf("offset", Vec3i.ZERO).forGetter(config -> {
			return config.offset;
		})).apply(builder, StructureModFeatureConfiguration::new);
	});
}