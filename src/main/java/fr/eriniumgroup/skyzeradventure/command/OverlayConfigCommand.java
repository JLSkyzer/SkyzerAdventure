
package fr.eriniumgroup.skyzeradventure.command;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.common.util.FakePlayerFactory;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.Commands;

import fr.eriniumgroup.skyzeradventure.procedures.OverlayConfigLevelProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.OverlayConfigEarningProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.OpenStatsScaleProcedure;

@Mod.EventBusSubscriber
public class OverlayConfigCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("overlayconfig")

				.then(Commands.literal("Level").executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					OverlayConfigLevelProcedure.execute(world, x, y, z, entity);
					return 0;
				})).then(Commands.literal("Earning").executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					OverlayConfigEarningProcedure.execute(world, x, y, z, entity);
					return 0;
				})).then(Commands.literal("stats").executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					OpenStatsScaleProcedure.execute(world, x, y, z, entity);
					return 0;
				})));
	}
}
