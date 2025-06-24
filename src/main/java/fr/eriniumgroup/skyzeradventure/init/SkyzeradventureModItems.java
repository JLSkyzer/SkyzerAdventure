
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.skyzeradventure.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

import fr.eriniumgroup.skyzeradventure.item.BossSpawnerItemItem;
import fr.eriniumgroup.skyzeradventure.item.AdetiumSwordItem;
import fr.eriniumgroup.skyzeradventure.item.AdetiumShovelItem;
import fr.eriniumgroup.skyzeradventure.item.AdetiumPickaxeItem;
import fr.eriniumgroup.skyzeradventure.item.AdetiumIngotItem;
import fr.eriniumgroup.skyzeradventure.item.AdetiumHoeItem;
import fr.eriniumgroup.skyzeradventure.item.AdetiumAxeItem;
import fr.eriniumgroup.skyzeradventure.item.AdetiumArmorItem;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

public class SkyzeradventureModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, SkyzeradventureMod.MODID);
	public static final RegistryObject<Item> ADETIUM_ORE = block(SkyzeradventureModBlocks.ADETIUM_ORE, SkyzeradventureModTabs.TAB_ORE_TAB);
	public static final RegistryObject<Item> ADETIUM_INGOT = REGISTRY.register("adetium_ingot", () -> new AdetiumIngotItem());
	public static final RegistryObject<Item> ADETIUM_ARMOR_HELMET = REGISTRY.register("adetium_armor_helmet", () -> new AdetiumArmorItem.Helmet());
	public static final RegistryObject<Item> ADETIUM_ARMOR_CHESTPLATE = REGISTRY.register("adetium_armor_chestplate", () -> new AdetiumArmorItem.Chestplate());
	public static final RegistryObject<Item> ADETIUM_ARMOR_LEGGINGS = REGISTRY.register("adetium_armor_leggings", () -> new AdetiumArmorItem.Leggings());
	public static final RegistryObject<Item> ADETIUM_ARMOR_BOOTS = REGISTRY.register("adetium_armor_boots", () -> new AdetiumArmorItem.Boots());
	public static final RegistryObject<Item> ADETIUM_SWORD = REGISTRY.register("adetium_sword", () -> new AdetiumSwordItem());
	public static final RegistryObject<Item> ADETIUM_PICKAXE = REGISTRY.register("adetium_pickaxe", () -> new AdetiumPickaxeItem());
	public static final RegistryObject<Item> ADETIUM_SHOVEL = REGISTRY.register("adetium_shovel", () -> new AdetiumShovelItem());
	public static final RegistryObject<Item> ADETIUM_AXE = REGISTRY.register("adetium_axe", () -> new AdetiumAxeItem());
	public static final RegistryObject<Item> ADETIUM_HOE = REGISTRY.register("adetium_hoe", () -> new AdetiumHoeItem());
	public static final RegistryObject<Item> BOSS_SPAWNER_ITEM = REGISTRY.register("boss_spawner_item", () -> new BossSpawnerItemItem());
	public static final RegistryObject<Item> CASTLE_BOSS_SPAWNER = block(SkyzeradventureModBlocks.CASTLE_BOSS_SPAWNER, SkyzeradventureModTabs.TAB_BLOCKS_TAB);
	public static final RegistryObject<Item> BASIC_LUCKY_BLOCK = block(SkyzeradventureModBlocks.BASIC_LUCKY_BLOCK, SkyzeradventureModTabs.TAB_BLOCKS_TAB);
	public static final RegistryObject<Item> ENERGY_SELLER_BLOCK = block(SkyzeradventureModBlocks.ENERGY_SELLER_BLOCK, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Item> CASTLE_BOSS_SPAWN_EGG = REGISTRY.register("castle_boss_spawn_egg",
			() -> new ForgeSpawnEggItem(SkyzeradventureModEntities.CASTLE_BOSS, -6710887, -3355393, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
