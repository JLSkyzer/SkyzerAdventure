package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import java.util.Random;

import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModEntities;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModBlocks;
import fr.eriniumgroup.skyzeradventure.entity.CastleBossEntity;

public class BossSpawnerItemRightclickedOnBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (blockstate.getBlock() == SkyzeradventureModBlocks.CASTLE_BOSS_SPAWNER.get()) {
			world.setBlock(new BlockPos(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = new CastleBossEntity(SkyzeradventureModEntities.CASTLE_BOSS.get(), _level);
				entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
				if (entityToSpawn instanceof Mob _mobToSpawn)
					_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
				world.addFreshEntity(entityToSpawn);
			}
			for (int index0 = 0; index0 < Mth.nextInt(new Random(), 1, 10); index0++) {
				if (world instanceof ServerLevel _level) {
					LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
					entityToSpawn.moveTo(Vec3.atBottomCenterOf(new BlockPos(Mth.nextInt(new Random(), (int) (x - 5), (int) (x + 5)), y, Mth.nextInt(new Random(), (int) (z - 5), (int) (z + 5)))));;
					_level.addFreshEntity(entityToSpawn);
				}
			}
		}
	}
}