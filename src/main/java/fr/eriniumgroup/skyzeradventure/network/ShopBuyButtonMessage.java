
package fr.eriniumgroup.skyzeradventure.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

import fr.eriniumgroup.skyzeradventure.world.inventory.ShopBuyMenu;
import fr.eriniumgroup.skyzeradventure.procedures.OpenShopMainProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.BuySearchProcedure;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ShopBuyButtonMessage {
	private final int buttonID, x, y, z;
	private HashMap<String, String> textstate;

	public ShopBuyButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
		this.textstate = readTextState(buffer);
	}

	public ShopBuyButtonMessage(int buttonID, int x, int y, int z, HashMap<String, String> textstate) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
		this.textstate = textstate;
	}

	public static void buffer(ShopBuyButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		writeTextState(message.textstate, buffer);
	}

	public static void handler(ShopBuyButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			HashMap<String, String> textstate = message.textstate;
			handleButtonAction(entity, buttonID, x, y, z, textstate);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z, HashMap<String, String> textstate) {
		Level world = entity.level;
		HashMap guistate = ShopBuyMenu.guistate;
		for (Map.Entry<String, String> entry : textstate.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			guistate.put(key, value);
		}
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			OpenShopMainProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 1) {

			BuySearchProcedure.execute(world, x, y, z, entity, guistate);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		SkyzeradventureMod.addNetworkMessage(ShopBuyButtonMessage.class, ShopBuyButtonMessage::buffer, ShopBuyButtonMessage::new, ShopBuyButtonMessage::handler);
	}

	public static void writeTextState(HashMap<String, String> map, FriendlyByteBuf buffer) {
		buffer.writeInt(map.size());
		for (Map.Entry<String, String> entry : map.entrySet()) {
			buffer.writeUtf(entry.getKey());
			buffer.writeUtf(entry.getValue());
		}
	}

	public static HashMap<String, String> readTextState(FriendlyByteBuf buffer) {
		int size = buffer.readInt();
		HashMap<String, String> map = new HashMap<>();
		for (int i = 0; i < size; i++) {
			String key = buffer.readUtf();
			String value = buffer.readUtf();
			map.put(key, value);
		}
		return map;
	}
}
