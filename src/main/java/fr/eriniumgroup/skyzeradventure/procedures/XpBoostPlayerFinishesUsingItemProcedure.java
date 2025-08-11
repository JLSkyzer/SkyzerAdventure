package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModMobEffects;

public class XpBoostPlayerFinishesUsingItemProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		String temp = "";
		if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
			_entity.addEffect(new MobEffectInstance(SkyzeradventureModMobEffects.PLAYER_XP_MULTIPLIER.get(), 36000, (int) (Math.round(itemstack.getOrCreateTag().getDouble("boost") / 0.25) - 4)));
	}
}