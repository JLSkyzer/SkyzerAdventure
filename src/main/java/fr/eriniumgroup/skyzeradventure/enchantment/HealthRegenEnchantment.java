package fr.eriniumgroup.skyzeradventure.enchantment;

import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.entity.EquipmentSlot;

public class HealthRegenEnchantment extends Enchantment {
	private static final EnchantmentCategory ENCHANTMENT_CATEGORY = EnchantmentCategory.ARMOR_CHEST;

	public HealthRegenEnchantment() {
		super(Enchantment.Rarity.RARE, ENCHANTMENT_CATEGORY, new EquipmentSlot[]{EquipmentSlot.CHEST});
	}

	@Override
	public int getMinCost(int level) {
		return 1 + level * 10;
	}

	@Override
	public int getMaxCost(int level) {
		return 6 + level * 10;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}
}