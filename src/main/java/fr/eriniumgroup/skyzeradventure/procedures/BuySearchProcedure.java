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

import io.netty.buffer.Unpooled;

import fr.eriniumgroup.skyzeradventure.world.inventory.ShopBuyMenu;
import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;

public class BuySearchProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		{
			String _setval = guistate.containsKey("textin:search") ? (String) guistate.get("textin:search") : "";
			entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.buysearch = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (entity instanceof Player _player)
			_player.closeContainer();
		if (entity instanceof ServerPlayer _ent) {
			BlockPos _bpos = new BlockPos(x, y, z);
			NetworkHooks.openGui((ServerPlayer) _ent, new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return new TextComponent("ShopBuy");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new ShopBuyMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
				}
			}, _bpos);
		}
	}
}
