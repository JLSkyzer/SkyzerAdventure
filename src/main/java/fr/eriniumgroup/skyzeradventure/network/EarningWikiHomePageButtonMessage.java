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

import fr.eriniumgroup.skyzeradventure.procedures.OpenSmeltingProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.OpenMiningProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.OpenKillingProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.OpenCraftingProcedure;
import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EarningWikiHomePageButtonMessage {
	private final int buttonID, x, y, z;

	public EarningWikiHomePageButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public EarningWikiHomePageButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(EarningWikiHomePageButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(EarningWikiHomePageButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> handleButtonAction(context.getSender(), message.buttonID, message.x, message.y, message.z));
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 1) {

			OpenCraftingProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 2) {

			OpenSmeltingProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 3) {

			OpenKillingProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 4) {

			OpenMiningProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		SkyzeradventureMod.addNetworkMessage(EarningWikiHomePageButtonMessage.class, EarningWikiHomePageButtonMessage::buffer, EarningWikiHomePageButtonMessage::new, EarningWikiHomePageButtonMessage::handler);
	}
}