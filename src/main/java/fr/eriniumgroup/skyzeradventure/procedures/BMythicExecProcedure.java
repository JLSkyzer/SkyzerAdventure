package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.core.BlockPos;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModItems;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModBlocks;

public class BMythicExecProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, String arg) {
		if (entity == null || arg == null)
			return;
		java.util.List<Object> common = new java.util.ArrayList<>();
		String temp = "";
		double tempNumber = 0;
		double whilecounter = 0;
		ItemStack item = ItemStack.EMPTY;
		temp = arg;
		if ((temp).equals("mythic_item_compagnon")) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(Items.NETHER_STAR).copy();
				_setstack.setCount(6);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(Items.DIAMOND).copy();
				_setstack.setCount(32);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(Blocks.IRON_BLOCK).copy();
				_setstack.setCount(64);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(Items.NETHERITE_INGOT).copy();
				_setstack.setCount(8);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(Items.TOTEM_OF_UNDYING).copy();
				_setstack.setCount(4);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(SkyzeradventureModItems.REGENERATION_GUM.get()).copy();
				_setstack.setCount(12);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(SkyzeradventureModBlocks.BASIC_LUCKY_BLOCK.get()).copy();
				_setstack.setCount(4);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(Blocks.BEACON).copy();
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("Id\u00E9e non d\u00E9velopper donc voici une compensation : "), false);
		} else if ((temp).equals("its_a_jackpot")) {
			{
				double _setval = (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).shop_money + 7500;
				entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.shop_money = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("\u00A7aVous avez gagner 7500$"), false);
		} else if ((temp).equals("stone_falling")) {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("Force \u00E0 toi sah, la categorie la plus rare pour \u00E7a mdrr"), false);
			if (world instanceof ServerLevel _level)
				FallingBlockEntity.fall(_level, new BlockPos(entity.getX(), entity.getY() + 5, entity.getZ()), Blocks.STONE.defaultBlockState());
			if (world instanceof ServerLevel _level)
				FallingBlockEntity.fall(_level, new BlockPos(entity.getX(), entity.getY() + 6, entity.getZ()), Blocks.STONE.defaultBlockState());
			if (world instanceof ServerLevel _level)
				FallingBlockEntity.fall(_level, new BlockPos(entity.getX(), entity.getY() + 7, entity.getZ()), Blocks.STONE.defaultBlockState());
			if (world instanceof ServerLevel _level)
				FallingBlockEntity.fall(_level, new BlockPos(entity.getX(), entity.getY() + 8, entity.getZ()), Blocks.STONE.defaultBlockState());
			if (world instanceof ServerLevel _level)
				FallingBlockEntity.fall(_level, new BlockPos(entity.getX(), entity.getY() + 9, entity.getZ()), Blocks.STONE.defaultBlockState());
			if (world instanceof ServerLevel _level)
				FallingBlockEntity.fall(_level, new BlockPos(entity.getX(), entity.getY() + 10, entity.getZ()), Blocks.STONE.defaultBlockState());
		} else if ((temp).equals("mythic_rnd_xp_boost_item")) {
			item = new ItemStack(SkyzeradventureModItems.RANDOM_XP_BOOST.get()).copy();
			item.getOrCreateTag().putString("type", "mythic");
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, item);
				entityToSpawn.setPickUpDelay(10);
				_level.addFreshEntity(entityToSpawn);
			}
		}
	}
}