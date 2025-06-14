
package fr.eriniumgroup.skyzeradventure.item;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import fr.eriniumgroup.skyzeradventure.procedures.AdetiumArmorHelmetTickEventProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.AdetiumArmorChestplateTickEventProcedure;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModTabs;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModItems;

public abstract class AdetiumArmorItem extends ArmorItem {
	public AdetiumArmorItem(EquipmentSlot slot, Item.Properties properties) {
		super(new ArmorMaterial() {
			@Override
			public int getDurabilityForSlot(EquipmentSlot slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 33;
			}

			@Override
			public int getDefenseForSlot(EquipmentSlot slot) {
				return new int[]{3, 6, 8, 3}[slot.getIndex()];
			}

			@Override
			public int getEnchantmentValue() {
				return 10;
			}

			@Override
			public SoundEvent getEquipSound() {
				return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
			}

			@Override
			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(SkyzeradventureModItems.ADETIUM_INGOT.get()));
			}

			@Override
			public String getName() {
				return "adetium_armor";
			}

			@Override
			public float getToughness() {
				return 2f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0f;
			}
		}, slot, properties);
	}

	public static class Helmet extends AdetiumArmorItem {
		public Helmet() {
			super(EquipmentSlot.HEAD, new Item.Properties().tab(SkyzeradventureModTabs.TAB_ARMORS_TAB));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "skyzeradventure:textures/models/armor/adetium_armor_layer_1.png";
		}

		@Override
		public void onArmorTick(ItemStack itemstack, Level world, Player entity) {
			AdetiumArmorHelmetTickEventProcedure.execute(entity);
		}
	}

	public static class Chestplate extends AdetiumArmorItem {
		public Chestplate() {
			super(EquipmentSlot.CHEST, new Item.Properties().tab(SkyzeradventureModTabs.TAB_ARMORS_TAB));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "skyzeradventure:textures/models/armor/adetium_armor_layer_1.png";
		}

		@Override
		public void onArmorTick(ItemStack itemstack, Level world, Player entity) {
			AdetiumArmorChestplateTickEventProcedure.execute(entity);
		}
	}

	public static class Leggings extends AdetiumArmorItem {
		public Leggings() {
			super(EquipmentSlot.LEGS, new Item.Properties().tab(SkyzeradventureModTabs.TAB_ARMORS_TAB));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "skyzeradventure:textures/models/armor/adetium_armor_layer_2.png";
		}
	}

	public static class Boots extends AdetiumArmorItem {
		public Boots() {
			super(EquipmentSlot.FEET, new Item.Properties().tab(SkyzeradventureModTabs.TAB_ARMORS_TAB));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "skyzeradventure:textures/models/armor/adetium_armor_layer_1.png";
		}
	}
}
