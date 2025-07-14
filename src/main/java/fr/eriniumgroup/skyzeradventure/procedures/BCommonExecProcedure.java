package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.Mth;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;

import java.util.Random;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;

public class BCommonExecProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, String arg) {
		if (entity == null || arg == null)
			return;
		java.util.List<Object> common = new java.util.ArrayList<>();
		String temp = "";
		double tempNumber = 0;
		temp = arg;
		if ((temp).equals("infestation")) {
			tempNumber = Mth.nextInt(new Random(), 1, 10);
			for (int index0 = 0; index0 < (int) tempNumber; index0++) {
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = new Zombie(EntityType.ZOMBIE, _level);
					entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof Mob _mobToSpawn)
						_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
					world.addFreshEntity(entityToSpawn);
				}
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("\u00A74Une infection se propage !"), false);
		} else if ((temp).equals("petit_cadeau")) {
			for (int index1 = 0; index1 < Mth.nextInt(new Random(), 1, 6); index1++) {
				if (world instanceof Level _level && !_level.isClientSide()) {
					ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.DIAMOND));
					entityToSpawn.setPickUpDelay(10);
					_level.addFreshEntity(entityToSpawn);
				}
			}
			for (int index2 = 0; index2 < Mth.nextInt(new Random(), 1, 4); index2++) {
				if (world instanceof Level _level && !_level.isClientSide()) {
					ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.EMERALD));
					entityToSpawn.setPickUpDelay(10);
					_level.addFreshEntity(entityToSpawn);
				}
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("\u00A7aJe suis de bonne humeur aujourd'hui :) \u00A75<3"), false);
		} else if ((temp).equals("give_xp_vanilla")) {
			if (entity instanceof Player _player)
				_player.giveExperiencePoints(Mth.nextInt(new Random(), 5, 15));
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("\u00A7aPas mal pour enchant des trucs ahah !"), false);
		} else if ((temp).equals("spawn_animals_chick")) {
			for (int index3 = 0; index3 < Mth.nextInt(new Random(), 3, 5); index3++) {
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = new Chicken(EntityType.CHICKEN, _level);
					entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof Mob _mobToSpawn)
						_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
					world.addFreshEntity(entityToSpawn);
				}
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("HMMMM \u00A74KFC"), false);
		} else if ((temp).equals("reward_iron_block")) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(Blocks.IRON_BLOCK).copy();
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("\u00A7eLe d\u00E9but d'une grande \u00E8re"), false);
		} else if ((temp).equals("flower_rain")) {
			for (int index4 = 0; index4 < 10; index4++) {
				if (world instanceof Level _level && !_level.isClientSide()) {
					ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z,
							new ItemStack((ForgeRegistries.ITEMS.tags().getTag(ItemTags.create(new ResourceLocation("minecraft:flowers"))).getRandomElement(new Random()).orElseGet(() -> Items.AIR))));
					entityToSpawn.setPickUpDelay(10);
					_level.addFreshEntity(entityToSpawn);
				}
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("\u00A7bC'est bon la nature"), false);
		} else if ((temp).equals("give_xp_job")) {
			tempNumber = Mth.nextDouble(new Random(), 2500, 5000);
			{
				double _setval = (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).RPGXp + tempNumber;
				entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.RPGXp = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent(("Tu a re\u00E7u : \u00A7a" + tempNumber + " d'xp")), false);
		} else if ((temp).equals("effect_speed")) {
			if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3600, 0));
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("\u00A7bDEJA VU"), false);
		}
	}
}
