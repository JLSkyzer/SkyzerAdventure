
package fr.eriniumgroup.skyzeradventure.world.features.ores;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTestType;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.core.Registry;
import net.minecraft.core.Holder;

import java.util.Set;
import java.util.Random;
import java.util.List;

import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModBlocks;

public class AdetiumOreFeature extends OreFeature {
	public static AdetiumOreFeature FEATURE = null;
	public static Holder<ConfiguredFeature<OreConfiguration, ?>> CONFIGURED_FEATURE = null;
	public static Holder<PlacedFeature> PLACED_FEATURE = null;

	public static Feature<?> feature() {
		FEATURE = new AdetiumOreFeature();
		CONFIGURED_FEATURE = FeatureUtils.register("skyzeradventure:adetium_ore", FEATURE, new OreConfiguration(AdetiumOreFeatureRuleTest.INSTANCE, SkyzeradventureModBlocks.ADETIUM_ORE.get().defaultBlockState(), 8));
		PLACED_FEATURE = PlacementUtils.register("skyzeradventure:adetium_ore", CONFIGURED_FEATURE,
				List.of(CountPlacement.of(1), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(16)), BiomeFilter.biome()));
		return FEATURE;
	}

	public static Holder<PlacedFeature> placedFeature() {
		return PLACED_FEATURE;
	}

	public static final Set<ResourceLocation> GENERATE_BIOMES = null;
	private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.OVERWORLD);

	public AdetiumOreFeature() {
		super(OreConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<OreConfiguration> context) {
		WorldGenLevel world = context.level();
		if (!generate_dimensions.contains(world.getLevel().dimension()))
			return false;
		return super.place(context);
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	private static class AdetiumOreFeatureRuleTest extends RuleTest {
		static final AdetiumOreFeatureRuleTest INSTANCE = new AdetiumOreFeatureRuleTest();
		private static final com.mojang.serialization.Codec<AdetiumOreFeatureRuleTest> CODEC = com.mojang.serialization.Codec.unit(() -> INSTANCE);
		private static final RuleTestType<AdetiumOreFeatureRuleTest> CUSTOM_MATCH = () -> CODEC;

		@SubscribeEvent
		public static void init(FMLCommonSetupEvent event) {
			Registry.register(Registry.RULE_TEST, new ResourceLocation("skyzeradventure:adetium_ore_match"), CUSTOM_MATCH);
		}

		public boolean test(BlockState blockstate, Random random) {
			return List.of(Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DEEPSLATE).contains(blockstate.getBlock());
		}

		protected RuleTestType<?> getType() {
			return CUSTOM_MATCH;
		}
	}
}
