package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;

import java.util.Random;

import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModBlocks;

public class BasicLuckyBlockBreakingProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		java.util.List<Object> listevent = new java.util.ArrayList<>();
		String arg = "";
		double random = 0;
		double random2 = 0;
		if (!world.isClientSide()) {
			if (!entity.isShiftKeyDown()) {
				random = Math.random() * 100;
				if (random < 62) {
					listevent = BCommoListProcedure.execute();
				} else if (random < 87) {
					listevent = BUncommonListProcedure.execute();
				} else if (random < 93) {
					listevent = BRareListProcedure.execute();
				} else if (random < 98.39) {
					listevent = BEpicListProcedure.execute();
				} else if (random < 99.39) {
					listevent = BLegendListProcedure.execute();
				} else {
					listevent = BMythicListProcedure.execute();
				}
				random2 = Mth.nextInt(new Random(), 0, (int) (listevent.size() - 1));
				arg = (String) listevent.get((int) random2);
				if (random < 62) {
					BCommonExecProcedure.execute(world, x, y, z, entity, arg);
				} else if (random < 87) {
					BUncommonExecProcedure.execute(world, x, y, z, entity, arg);
				} else if (random < 93) {
					BRareExecProcedure.execute(world, x, y, z, entity, arg);
				} else if (random < 98.39) {
					BEpicExecProcedure.execute(world, x, y, z, entity, arg);
				} else if (random < 99.39) {
					BLegendaryExecProcedure.execute(world, x, y, z, entity, arg);
				} else {
					BMythicExecProcedure.execute(world, x, y, z, entity, arg);
				}
			} else {
				if (world instanceof Level _level && !_level.isClientSide()) {
					ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(SkyzeradventureModBlocks.BASIC_LUCKY_BLOCK.get()));
					entityToSpawn.setPickUpDelay(0);
					_level.addFreshEntity(entityToSpawn);
				}
			}
		}
	}
}