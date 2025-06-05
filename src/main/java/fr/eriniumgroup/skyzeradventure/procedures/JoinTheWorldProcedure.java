package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;

@Mod.EventBusSubscriber
public class JoinTheWorldProcedure {
	@SubscribeEvent
	public static void onEntityJoin(EntityJoinWorldEvent event) {
		execute(event, event.getWorld(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof ServerPlayer) {
			if ((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).RPGHealthMax == 0) {
				{
					double _setval = 20;
					entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.RPGHealth = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					double _setval = 20;
					entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.RPGHealthMax = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if ((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).RPGMaxXp == 0) {
				{
					double _setval = 500;
					entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.RPGMaxXp = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if (SkyzeradventureModVariables.MapVariables.get(world).serverxpmultiplier == 0) {
				SkyzeradventureModVariables.MapVariables.get(world).serverxpmultiplier = 1;
				SkyzeradventureModVariables.MapVariables.get(world).syncData(world);
			}
			if ((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).playerxpmultiplier == 0) {
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
}
