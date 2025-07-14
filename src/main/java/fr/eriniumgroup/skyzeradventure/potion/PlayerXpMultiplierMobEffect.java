package fr.eriniumgroup.skyzeradventure.potion;

import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import fr.eriniumgroup.skyzeradventure.procedures.PlayerXpMultiplierOnEffectActiveTickProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.PlayerXpMultiplierEffectExpiresProcedure;

public class PlayerXpMultiplierMobEffect extends MobEffect {
	public PlayerXpMultiplierMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -6684928);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		PlayerXpMultiplierOnEffectActiveTickProcedure.execute(entity, amplifier);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		PlayerXpMultiplierEffectExpiresProcedure.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
