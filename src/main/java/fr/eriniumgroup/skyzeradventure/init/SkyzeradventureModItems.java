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

import fr.eriniumgroup.skyzeradventure.item.WritedPaperItem;
import fr.eriniumgroup.skyzeradventure.item.SacrificeKnifeItem;
import fr.eriniumgroup.skyzeradventure.item.RegenerationGumItem;
import fr.eriniumgroup.skyzeradventure.item.ExponiaMeteoriteGemItem;
import fr.eriniumgroup.skyzeradventure.item.ExplosiveOrbItem;
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
	public static final RegistryObject<Item> ADETIUM_ORE = blockCMT(SkyzeradventureModBlocks.ADETIUM_ORE, SkyzeradventureModTabs.TAB_ORE_TAB);
	public static final RegistryObject<Item> ADETIUM_INGOT = REGISTRY.register("adetium_ingot", AdetiumIngotItem::new);
	public static final RegistryObject<Item> ADETIUM_ARMOR_HELMET = REGISTRY.register("adetium_armor_helmet", AdetiumArmorItem.Helmet::new);
	public static final RegistryObject<Item> ADETIUM_ARMOR_CHESTPLATE = REGISTRY.register("adetium_armor_chestplate", AdetiumArmorItem.Chestplate::new);
	public static final RegistryObject<Item> ADETIUM_ARMOR_LEGGINGS = REGISTRY.register("adetium_armor_leggings", AdetiumArmorItem.Leggings::new);
	public static final RegistryObject<Item> ADETIUM_ARMOR_BOOTS = REGISTRY.register("adetium_armor_boots", AdetiumArmorItem.Boots::new);
	public static final RegistryObject<Item> ADETIUM_SWORD = REGISTRY.register("adetium_sword", AdetiumSwordItem::new);
	public static final RegistryObject<Item> ADETIUM_PICKAXE = REGISTRY.register("adetium_pickaxe", AdetiumPickaxeItem::new);
	public static final RegistryObject<Item> ADETIUM_SHOVEL = REGISTRY.register("adetium_shovel", AdetiumShovelItem::new);
	public static final RegistryObject<Item> ADETIUM_AXE = REGISTRY.register("adetium_axe", AdetiumAxeItem::new);
	public static final RegistryObject<Item> ADETIUM_HOE = REGISTRY.register("adetium_hoe", AdetiumHoeItem::new);
	public static final RegistryObject<Item> SACRIFICE_KNIFE = REGISTRY.register("sacrifice_knife", SacrificeKnifeItem::new);
	public static final RegistryObject<Item> BOSS_SPAWNER_ITEM = REGISTRY.register("boss_spawner_item", BossSpawnerItemItem::new);
	public static final RegistryObject<Item> REGENERATION_GUM = REGISTRY.register("regeneration_gum", RegenerationGumItem::new);
	public static final RegistryObject<Item> WRITED_PAPER = REGISTRY.register("writed_paper", WritedPaperItem::new);
	public static final RegistryObject<Item> CASTLE_BOSS_SPAWNER = blockCMT(SkyzeradventureModBlocks.CASTLE_BOSS_SPAWNER, SkyzeradventureModTabs.TAB_BLOCKS_TAB);
	public static final RegistryObject<Item> BASIC_LUCKY_BLOCK = blockCMT(SkyzeradventureModBlocks.BASIC_LUCKY_BLOCK, SkyzeradventureModTabs.TAB_BLOCKS_TAB);
	public static final RegistryObject<Item> AUTO_SELLER = blockCMT(SkyzeradventureModBlocks.AUTO_SELLER, SkyzeradventureModTabs.TAB_BLOCKS_TAB);
	public static final RegistryObject<Item> EXPONIA_METEORITE = blockCMT(SkyzeradventureModBlocks.EXPONIA_METEORITE, SkyzeradventureModTabs.TAB_BLOCKS_TAB);
	public static final RegistryObject<Item> ENERGY_SELLER_BLOCK = blockCMT(SkyzeradventureModBlocks.ENERGY_SELLER_BLOCK, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Item> CASTLE_BOSS_SPAWN_EGG = REGISTRY.register("castle_boss_spawn_egg",
			() -> new ForgeSpawnEggItem(SkyzeradventureModEntities.CASTLE_BOSS, -6710887, -3355393, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> EXPLOSIVE_ORB = REGISTRY.register("explosive_orb", ExplosiveOrbItem::new);
	public static final RegistryObject<Item> EXPONIA_METEORITE_GEM = REGISTRY.register("exponia_meteorite_gem", ExponiaMeteoriteGemItem::new);

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> blockCMT(RegistryObject<Block> block, CreativeModeTab tab) {
		return block(block, new Item.Properties().tab(tab));
	}

	private static RegistryObject<Item> block(RegistryObject<Block> block, Item.Properties properties) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), properties));
	}
}