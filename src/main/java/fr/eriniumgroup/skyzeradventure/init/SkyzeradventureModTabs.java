
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.skyzeradventure.init;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class SkyzeradventureModTabs {
	public static CreativeModeTab TAB_INGOTS_TAB;
	public static CreativeModeTab TAB_ORE_TAB;
	public static CreativeModeTab TAB_ARMORS_TAB;
	public static CreativeModeTab TAB_TOOLS_TAB;

	public static void load() {
		TAB_INGOTS_TAB = new CreativeModeTab("tabingots_tab") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(SkyzeradventureModItems.ADETIUM_INGOT.get());
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
		TAB_ORE_TAB = new CreativeModeTab("tabore_tab") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(SkyzeradventureModBlocks.ADETIUM_ORE.get());
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
		TAB_ARMORS_TAB = new CreativeModeTab("tabarmors_tab") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(SkyzeradventureModItems.ADETIUM_ARMOR_HELMET.get());
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
		TAB_TOOLS_TAB = new CreativeModeTab("tabtools_tab") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(SkyzeradventureModItems.ADETIUM_SWORD.get());
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
}
