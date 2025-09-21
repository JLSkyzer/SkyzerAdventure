package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModMobEffects;

@Mod.EventBusSubscriber
public class EntityDieProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity());
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof ServerPlayer) {
			{
				boolean _setval = entity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(SkyzeradventureModMobEffects.PLAYER_XP_MULTIPLIER.get());
				entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.playerxpboosthadpotionbeforedeath = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(SkyzeradventureModMobEffects.PLAYER_XP_MULTIPLIER.get()) ? _livEnt.getEffect(SkyzeradventureModMobEffects.PLAYER_XP_MULTIPLIER.get()).getAmplifier() : 0;
				entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.playerxpboostlevel = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(SkyzeradventureModMobEffects.PLAYER_XP_MULTIPLIER.get()) ? _livEnt.getEffect(SkyzeradventureModMobEffects.PLAYER_XP_MULTIPLIER.get()).getDuration() : 0;
				entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.playerxpboosttick = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = 1;
				entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.playerxpmultiplier = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}