package fr.eriniumgroup.skyzeradventure.mixin;

import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.core.Holder;

import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModBiomes;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;

@Mixin(NoiseGeneratorSettings.class)
public class NoiseGeneratorSettingsMixin implements SkyzeradventureModBiomes.SkyzeradventureModNoiseGeneratorSettings {
	@Unique
	private Holder<DimensionType> skyzeradventure_dimensionTypeReference;

	@WrapMethod(method = "surfaceRule")
	public SurfaceRules.RuleSource surfaceRule(Operation<SurfaceRules.RuleSource> original) {
		SurfaceRules.RuleSource retval = original.call();
		if (this.skyzeradventure_dimensionTypeReference != null) {
			retval = SkyzeradventureModBiomes.adaptSurfaceRule(retval, this.skyzeradventure_dimensionTypeReference);
		}
		return retval;
	}

	@Override
	public void setskyzeradventureDimensionTypeReference(Holder<DimensionType> dimensionType) {
		this.skyzeradventure_dimensionTypeReference = dimensionType;
	}
}