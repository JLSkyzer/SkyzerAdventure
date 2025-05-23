
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

import fr.eriniumgroup.skyzeradventure.world.inventory.ConfiguratorMenu;
import fr.eriniumgroup.skyzeradventure.procedures.YPlusProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.YMinusProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.XPlusProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.XMinusProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.ResetBtnProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.Precision1Procedure;
import fr.eriniumgroup.skyzeradventure.procedures.Precision10Procedure;
import fr.eriniumgroup.skyzeradventure.procedures.Precision01Procedure;
import fr.eriniumgroup.skyzeradventure.procedures.Precision001Procedure;
import fr.eriniumgroup.skyzeradventure.procedures.OverlaySaveProcedure;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConfiguratorButtonMessage {
	private final int buttonID, x, y, z;
	private HashMap<String, String> textstate;

	public ConfiguratorButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
		this.textstate = readTextState(buffer);
	}

	public ConfiguratorButtonMessage(int buttonID, int x, int y, int z, HashMap<String, String> textstate) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
		this.textstate = textstate;
	}

	public static void buffer(ConfiguratorButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		writeTextState(message.textstate, buffer);
	}

	public static void handler(ConfiguratorButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
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
		HashMap guistate = ConfiguratorMenu.guistate;
		for (Map.Entry<String, String> entry : textstate.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			guistate.put(key, value);
		}
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			YPlusProcedure.execute(world, entity);
		}
		if (buttonID == 1) {

			ResetBtnProcedure.execute(world, entity);
		}
		if (buttonID == 2) {

			YMinusProcedure.execute(world, entity);
		}
		if (buttonID == 3) {

			XPlusProcedure.execute(world, entity);
		}
		if (buttonID == 4) {

			XMinusProcedure.execute(world, entity);
		}
		if (buttonID == 5) {

			Precision001Procedure.execute(world, entity);
		}
		if (buttonID == 6) {

			Precision01Procedure.execute(world, entity);
		}
		if (buttonID == 7) {

			Precision1Procedure.execute(world, entity);
		}
		if (buttonID == 8) {

			Precision10Procedure.execute(world, entity);
		}
		if (buttonID == 9) {

			OverlaySaveProcedure.execute(world, entity, guistate);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		SkyzeradventureMod.addNetworkMessage(ConfiguratorButtonMessage.class, ConfiguratorButtonMessage::buffer, ConfiguratorButtonMessage::new, ConfiguratorButtonMessage::handler);
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
