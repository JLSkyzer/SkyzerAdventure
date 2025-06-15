package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import java.util.HashMap;

import java.io.File;

import io.netty.buffer.Unpooled;

import fr.eriniumgroup.skyzeradventure.world.inventory.ShopMainGuiMenu;

public class CmdTempProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		File file = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		java.util.Map<String, Double> hashmap = new HashMap<>();
		if (entity instanceof ServerPlayer _ent) {
			BlockPos _bpos = new BlockPos(x, y, z);
			NetworkHooks.openGui((ServerPlayer) _ent, new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return new TextComponent("ShopMainGui");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new ShopMainGuiMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
				}
			}, _bpos);
		}
	}
}
