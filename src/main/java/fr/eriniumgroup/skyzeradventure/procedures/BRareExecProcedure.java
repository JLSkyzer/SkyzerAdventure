package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.monster.Skeleton;
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

import java.util.Random;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModMobEffects;

public class BRareExecProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, String arg) {
		if (entity == null || arg == null)
			return;
		java.util.List<Object> common = new java.util.ArrayList<>();
		String temp = "";
		double tempNumber = 0;
		double whilecounter = 0;
		temp = arg;
		if ((temp).equals("spawn_villager_trader")) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = new WanderingTrader(EntityType.WANDERING_TRADER, _level);
				entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
				if (entityToSpawn instanceof Mob _mobToSpawn)
					_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
				world.addFreshEntity(entityToSpawn);
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("imagine c'est le bon ?"), false);
		} else if ((temp).equals("give_diamond_tools")) {
			if (world instanceof Level _level && !_level.isClientSide()) {
				ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.DIAMOND_PICKAXE));
				entityToSpawn.setPickUpDelay(10);
				_level.addFreshEntity(entityToSpawn);
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("Je savais pas trop quoi te donner"), false);
		} else if ((temp).equals("give_xp_job")) {
			tempNumber = Mth.nextInt(new Random(), 4000, 10000);
			{
				double _setval = (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).RPGXp + tempNumber;
				entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.RPGXp = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = tempNumber;
				entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.earning_lastvalue = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				String _setval = "\u00A76+ " + tempNumber + " xp";
				entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.earning_text2 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				String _setval = "";
				entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.earning_text3 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = 60;
				entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.earningNotifTick = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("bah bravo \u00A7a$$$"), false);
		} else if ((temp).equals("curse_of_blindness")) {
			if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 6000, 0));
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("\u00A74J'en ai vu des malchanceux mais alors la "), false);
		} else if ((temp).equals("damage_of_death")) {
			if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
				_entity.addEffect(new MobEffectInstance(SkyzeradventureModMobEffects.DAMAGEOFDEATH.get(), 2400, 0));
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("\u00A74ECOUTE MOI BIEN \u00A7bpendant 2 minutes si tu prend un seul d\u00E9gat tu meurt instant ! tu peux enlever cet effet comme un effet vanilla ou avec du miel"), false);
		} else if ((temp).equals("famine")) {
			if (entity instanceof Player _player)
				_player.getFoodData().setFoodLevel(1);
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("bah oui bah oui"), false);
		} else if ((temp).equals("bone_raid")) {
			for (int index0 = 0; index0 < 10; index0++) {
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = new Skeleton(EntityType.SKELETON, _level);
					entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof Mob _mobToSpawn)
						_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
					world.addFreshEntity(entityToSpawn);
				}
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("\u00A7dPrend du recule, au moins ta de la bone meal !"), false);
		}
	}
}