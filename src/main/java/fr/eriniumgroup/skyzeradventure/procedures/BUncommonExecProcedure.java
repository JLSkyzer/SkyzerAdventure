package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.core.BlockPos;

import java.util.Random;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;

import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModMobEffects;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModItems;

public class BUncommonExecProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, String arg) {
		if (entity == null || arg == null)
			return;
		java.util.List<Object> common = new java.util.ArrayList<>();
		String temp = "";
		double tempNumber = 0;
		temp = arg;
		if ((temp).equals("kaboom")) {
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Blocks.TNT));
				entityToSpawn.setPickUpDelay(10);
				_level.addFreshEntity(entityToSpawn);
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("kaboom!"), false);
		} else if ((temp).equals("adetium_party")) {
			for (int index0 = 0; index0 < Mth.nextInt(new Random(), 4, 10); index0++) {
				if (world instanceof ServerLevel _level) {
					ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(SkyzeradventureModItems.ADETIUM_INGOT.get()));
					entityToSpawn.setPickUpDelay(10);
					_level.addFreshEntity(entityToSpawn);
				}
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("Its an \u00A71adetium party"), false);
		} else if ((temp).equals("attentat")) {
			for (int index1 = 0; index1 < Mth.nextInt(new Random(), 3, 7); index1++) {
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = new Creeper(EntityType.CREEPER, _level);
					entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof Mob _mobToSpawn)
						_mobToSpawn.finalizeSpawn(_level, _level.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
					_level.addFreshEntity(entityToSpawn);
				}
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("\u00A7boulaaa"), false);
		} else if ((temp).equals("reward_emeralds")) {
			for (int index2 = 0; index2 < Mth.nextInt(new Random(), 1, 3); index2++) {
				if (world instanceof ServerLevel _level) {
					ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.EMERALD));
					entityToSpawn.setPickUpDelay(10);
					_level.addFreshEntity(entityToSpawn);
				}
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("JACKPOT"), false);
		} else if ((temp).equals("effect_jump_boost")) {
			if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 1800, 1));
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("ok buggs bunny"), false);
		} else if ((temp).equals("random_job_xp_boost")) {
			if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
				_entity.addEffect(new MobEffectInstance(SkyzeradventureModMobEffects.PLAYER_XP_MULTIPLIER.get(), 2400, 4));
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("Depeche toi d'aller farm ! ta 2 minutes"), false);
		} else if ((temp).equals("reward_random_food")) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(Items.COOKED_PORKCHOP).copy();
				_setstack.setCount(Mth.nextInt(new Random(), 5, 10));
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("\u00A78Tiens prend \u00E7a, \u00E7a sera d\u00E9j\u00E0 \u00E7a"), false);
		} else if ((temp).equals("nightmare_phantoms")) {
			for (int index3 = 0; index3 < Mth.nextInt(new Random(), 3, 6); index3++) {
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = new Phantom(EntityType.PHANTOM, _level);
					entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof Mob _mobToSpawn)
						_mobToSpawn.finalizeSpawn(_level, _level.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
					_level.addFreshEntity(entityToSpawn);
				}
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("FUIIIIIIIT"), false);
		} else if ((temp).equals("web_trap")) {
			int radius = 4; // Rayon du carré en X et Z
			int maxY = world.getHeight(); // Couche maximale en Y
			net.minecraft.world.entity.Entity skyentity = entity;
			int startX = (int) skyentity.getBlockX() - (radius / 2);
			int startZ = (int) skyentity.getBlockZ() - (radius / 2);
			List<BlockPos> blockPositions = new ArrayList<>();
			for (int xPos = startX; xPos < startX + radius; xPos++) {
				for (int zPos = startZ; zPos < startZ + radius; zPos++) {
					for (int yPos = 0; yPos < maxY; yPos++) {
						BlockPos blockPos = new BlockPos(xPos, yPos, zPos);
						blockPositions.add(blockPos);
					}
				}
			}
			for (BlockPos blockPos : blockPositions) {
				BlockState skyblockstate = world.getBlockState(blockPos);
				// Effectuez ici les opérations souhaitées sur le bloc trouvé
				if ((skyblockstate).getBlock().defaultBlockState() == Blocks.AIR.defaultBlockState()) {
					world.setBlock(new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ()), Blocks.COBWEB.defaultBlockState(), 3);
				}
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("ta peur ou pas ? "), false);
		} else if ((temp).equals("hot_feet")) {
			{
				final Vec3 _center = new Vec3(x, y, z);
				for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(4 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
					entityiterator.setSecondsOnFire(5);
					if (entityiterator instanceof Player _player && !_player.level.isClientSide())
						_player.displayClientMessage(new TextComponent("\u00A79Ta pris tes tongues ? "), false);
				}
			}
		}
	}
}