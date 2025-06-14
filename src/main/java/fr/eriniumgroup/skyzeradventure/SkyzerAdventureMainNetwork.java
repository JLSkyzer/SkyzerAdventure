/**
 * The code of this mod element is always locked.
 *
 * You can register new events in this class too.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside fr.eriniumgroup.skyzeradventure as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 *
 * This class will be added in the mod root package.
*/
package fr.eriniumgroup.skyzeradventure;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import fr.eriniumgroup.skyzeradventure.procedures.OpenBuyPageProcedure;
import fr.eriniumgroup.skyzeradventure.procedures.OpenSellingPageProcedure;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SkyzerAdventureMainNetwork {
	private final int buttonID, x, y, z;
	private String type;
	private ItemStack itemStack;
	private double price;

	public SkyzerAdventureMainNetwork(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
		this.type = buffer.readUtf();
		this.itemStack = buffer.readItem();
		this.price = buffer.readDouble();
	}

	public SkyzerAdventureMainNetwork(int buttonID, int x, int y, int z, String type, ItemStack itemStack, Double price) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = type;
		this.itemStack = itemStack;
		this.price = price;
	}

	public static void buffer(SkyzerAdventureMainNetwork message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		buffer.writeUtf(message.type);
		buffer.writeItem(message.itemStack);
		buffer.writeDouble(message.price);
	}

	public static void handler(SkyzerAdventureMainNetwork message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			String type = message.type;
			ItemStack itemStack = message.itemStack;
			Double price = message.price;
			handleButtonAction(entity, buttonID, x, y, z, type, itemStack, price);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z, String type, ItemStack itemStack, Double price) {
		Level world = entity.level;
		SkyzeradventureModVariables.PlayerVariables playercap = entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables());

		playercap.tempshopactiontype = type;
		playercap.tempitem = itemStack;
		playercap.tempitemprice = price;
		playercap.syncPlayerVariables(entity);

		//HashMap guistate = BankerAccountManagerMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {
			OpenBuyPageProcedure.execute(entity.getLevel(), entity.getX(), entity.getY(), entity.getZ(), entity);
			//BankerCreateAccountBtnProcedure.execute(world, entity, guistate);
		} else if (buttonID == 1) {
			OpenSellingPageProcedure.execute(entity.getLevel(), entity.getX(), entity.getY(), entity.getZ(), entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		SkyzeradventureMod.addNetworkMessage(SkyzerAdventureMainNetwork.class, SkyzerAdventureMainNetwork::buffer, SkyzerAdventureMainNetwork::new, SkyzerAdventureMainNetwork::handler);
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
