
package fr.eriniumgroup.skyzeradventure.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModTabs;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModItems;

public class AdetiumSwordItem extends SwordItem {
	public AdetiumSwordItem() {
		super(new Tier() {
			public int getUses() {
				return 1561;
			}

			public float getSpeed() {
				return 8f;
			}

			public float getAttackDamageBonus() {
				return 3f;
			}

			public int getLevel() {
				return 3;
			}

			public int getEnchantmentValue() {
				return 10;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(SkyzeradventureModItems.ADETIUM_INGOT.get()));
			}
		}, 3, 96f, new Item.Properties().tab(SkyzeradventureModTabs.TAB_TOOLS_TAB));
	}
}
