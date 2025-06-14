
package fr.eriniumgroup.skyzeradventure.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.HoeItem;

import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModTabs;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModItems;

public class AdetiumHoeItem extends HoeItem {
	public AdetiumHoeItem() {
		super(new Tier() {
			public int getUses() {
				return 1561;
			}

			public float getSpeed() {
				return 8f;
			}

			public float getAttackDamageBonus() {
				return 5f;
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
		}, 0, 96f, new Item.Properties().tab(SkyzeradventureModTabs.TAB_TOOLS_TAB));
	}
}
