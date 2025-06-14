
package fr.eriniumgroup.skyzeradventure.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModTabs;

public class AdetiumIngotItem extends Item {
	public AdetiumIngotItem() {
		super(new Item.Properties().tab(SkyzeradventureModTabs.TAB_INGOTS_TAB).stacksTo(64).rarity(Rarity.COMMON));
	}
}
