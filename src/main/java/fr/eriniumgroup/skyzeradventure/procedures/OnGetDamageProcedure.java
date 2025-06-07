package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModGameRules;

@Mod.EventBusSubscriber
public class OnGetDamageProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level, event.getSource(), event.getEntity(), event.getAmount());
		}
	}

	public static void execute(LevelAccessor world, DamageSource damagesource, Entity entity, double amount) {
		execute(null, world, damagesource, entity, amount);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, DamageSource damagesource, Entity entity, double amount) {
		if (entity == null)
			return;
		double FinalDamage = 0;
		double damageReduction = 0;
		if (entity instanceof ServerPlayer && entity.isAlive()) {
			if (world.getLevelData().getGameRules().getBoolean(SkyzeradventureModGameRules.RPG_MODE_GAMERULE)) {
				if (entity instanceof LivingEntity _entity)
					_entity.setHealth(20);
				FinalDamage = amount;
				damageReduction = damageReduction + (entity instanceof LivingEntity _livEnt ? _livEnt.getArmorValue() : 0) * 0.02;
				if (damagesource == DamageSource.CRAMMING || damagesource == DamageSource.IN_FIRE || damagesource == DamageSource.LAVA || damagesource == DamageSource.ON_FIRE) {
					damageReduction = damageReduction + (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_PROTECTION, (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY))
							+ EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_PROTECTION, (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY))
							+ EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_PROTECTION, (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY))
							+ EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_PROTECTION, (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY))) * 0.05;
				} else if (damagesource == DamageSource.FALL) {
					damageReduction = damageReduction + (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FALL_PROTECTION, (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY))
							+ EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FALL_PROTECTION, (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY))
							+ EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FALL_PROTECTION, (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY))
							+ EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FALL_PROTECTION, (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY))) * 0.1;
				} else {
					damageReduction = damageReduction + (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.ALL_DAMAGE_PROTECTION, (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY))
							+ EnchantmentHelper.getItemEnchantmentLevel(Enchantments.ALL_DAMAGE_PROTECTION, (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY))
							+ EnchantmentHelper.getItemEnchantmentLevel(Enchantments.ALL_DAMAGE_PROTECTION, (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY))
							+ EnchantmentHelper.getItemEnchantmentLevel(Enchantments.ALL_DAMAGE_PROTECTION, (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY))) * 0.08;
				}
				if (damageReduction > 0) {
					FinalDamage = FinalDamage / (1 + damageReduction);
				}
				{
					double _setval = (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).RPGHealth - FinalDamage;
					entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.RPGHealth = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					double _setval = 200;
					entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.OnDamageTick = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
	}
}
