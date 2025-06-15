package fr.eriniumgroup.skyzeradventure.network;

import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

import fr.eriniumgroup.skyzeradventure.SkyzeradventureMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SkyzeradventureModVariables {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		SkyzeradventureMod.addNetworkMessage(SavedDataSyncMessage.class, SavedDataSyncMessage::buffer, SavedDataSyncMessage::new, SavedDataSyncMessage::handler);
		SkyzeradventureMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getPlayer().level.isClientSide())
				((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getPlayer());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getPlayer().level.isClientSide())
				((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getPlayer());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getPlayer().level.isClientSide())
				((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getPlayer());
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			clone.RPGHealth = original.RPGHealth;
			clone.RPGHealthMax = original.RPGHealthMax;
			clone.RPGLevel = original.RPGLevel;
			clone.RPGXp = original.RPGXp;
			clone.OnDamage = original.OnDamage;
			clone.RPGMaxXp = original.RPGMaxXp;
			clone.regentick = original.regentick;
			clone.LevelOverlayX = original.LevelOverlayX;
			clone.LevelOverlayY = original.LevelOverlayY;
			clone.overlayConfigTarget = original.overlayConfigTarget;
			clone.OverlayConfigPrecision = original.OverlayConfigPrecision;
			clone.earningNotifTick = original.earningNotifTick;
			clone.earning_text1 = original.earning_text1;
			clone.earning_text2 = original.earning_text2;
			clone.earning_text3 = original.earning_text3;
			clone.earningNotifX = original.earningNotifX;
			clone.earningNotifY = original.earningNotifY;
			clone.stats_scale = original.stats_scale;
			clone.earning_lastvalue = original.earning_lastvalue;
			clone.playerxpmultiplier = original.playerxpmultiplier;
			clone.buysearch = original.buysearch;
			clone.sellsearch = original.sellsearch;
			clone.shop_money = original.shop_money;
			if (!event.isWasDeath()) {
				clone.OnDamageTick = original.OnDamageTick;
				clone.EarningWikiTarget = original.EarningWikiTarget;
				clone.tempitem = original.tempitem;
				clone.tempitemprice = original.tempitemprice;
				clone.tempshopactiontype = original.tempshopactiontype;
			}
		}

		@SubscribeEvent
		public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getPlayer().level.isClientSide()) {
				SavedData mapdata = MapVariables.get(event.getPlayer().level);
				SavedData worlddata = WorldVariables.get(event.getPlayer().level);
				if (mapdata != null)
					SkyzeradventureMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getPlayer()), new SavedDataSyncMessage(0, mapdata));
				if (worlddata != null)
					SkyzeradventureMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getPlayer()), new SavedDataSyncMessage(1, worlddata));
			}
		}

		@SubscribeEvent
		public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getPlayer().level.isClientSide()) {
				SavedData worlddata = WorldVariables.get(event.getPlayer().level);
				if (worlddata != null)
					SkyzeradventureMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getPlayer()), new SavedDataSyncMessage(1, worlddata));
			}
		}
	}

	public static class WorldVariables extends SavedData {
		public static final String DATA_NAME = "skyzeradventure_worldvars";

		public static WorldVariables load(CompoundTag tag) {
			WorldVariables data = new WorldVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level level && !level.isClientSide())
				SkyzeradventureMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(level::dimension), new SavedDataSyncMessage(1, this));
		}

		static WorldVariables clientSide = new WorldVariables();

		public static WorldVariables get(LevelAccessor world) {
			if (world instanceof ServerLevel level) {
				return level.getDataStorage().computeIfAbsent(e -> WorldVariables.load(e), WorldVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class MapVariables extends SavedData {
		public static final String DATA_NAME = "skyzeradventure_mapvars";
		public double serverxpmultiplier = 1.0;

		public static MapVariables load(CompoundTag tag) {
			MapVariables data = new MapVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
			serverxpmultiplier = nbt.getDouble("serverxpmultiplier");
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			nbt.putDouble("serverxpmultiplier", serverxpmultiplier);
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level && !world.isClientSide())
				SkyzeradventureMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new SavedDataSyncMessage(0, this));
		}

		static MapVariables clientSide = new MapVariables();

		public static MapVariables get(LevelAccessor world) {
			if (world instanceof ServerLevelAccessor serverLevelAcc) {
				return serverLevelAcc.getLevel().getServer().getLevel(Level.OVERWORLD).getDataStorage().computeIfAbsent(e -> MapVariables.load(e), MapVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class SavedDataSyncMessage {
		private final int type;
		private SavedData data;

		public SavedDataSyncMessage(FriendlyByteBuf buffer) {
			this.type = buffer.readInt();
			CompoundTag nbt = buffer.readNbt();
			if (nbt != null) {
				this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
				if (this.data instanceof MapVariables mapVariables)
					mapVariables.read(nbt);
				else if (this.data instanceof WorldVariables worldVariables)
					worldVariables.read(nbt);
			}
		}

		public SavedDataSyncMessage(int type, SavedData data) {
			this.type = type;
			this.data = data;
		}

		public static void buffer(SavedDataSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeInt(message.type);
			if (message.data != null)
				buffer.writeNbt(message.data.save(new CompoundTag()));
		}

		public static void handler(SavedDataSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer() && message.data != null) {
					if (message.type == 0)
						MapVariables.clientSide = (MapVariables) message.data;
					else
						WorldVariables.clientSide = (WorldVariables) message.data;
				}
			});
			context.setPacketHandled(true);
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(new ResourceLocation("skyzeradventure", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public Tag serializeNBT() {
			return playerVariables.writeNBT();
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			playerVariables.readNBT(nbt);
		}
	}

	public static class PlayerVariables {
		public double RPGHealth = 20.0;
		public double RPGHealthMax = 20.0;
		public double RPGLevel = 0;
		public double RPGXp = 0;
		public boolean OnDamage = false;
		public double OnDamageTick = 0;
		public double RPGMaxXp = 500.0;
		public double regentick = 0;
		public double LevelOverlayX = 0;
		public double LevelOverlayY = 0;
		public String overlayConfigTarget = "\"\"";
		public double OverlayConfigPrecision = 0.01;
		public double earningNotifTick = 0;
		public String earning_text1 = "\"\"";
		public String earning_text2 = "\"\"";
		public String earning_text3 = "\"\"";
		public double earningNotifX = 0;
		public double earningNotifY = 0;
		public double stats_scale = 1.0;
		public double earning_lastvalue = 0;
		public double playerxpmultiplier = 1.0;
		public String EarningWikiTarget = "";
		public String buysearch = "\"\"";
		public String sellsearch = "\"\"";
		public double shop_money = 1500.0;
		public ItemStack tempitem = ItemStack.EMPTY;
		public double tempitemprice = 0;
		public String tempshopactiontype = "\"\"";

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				SkyzeradventureMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
		}

		public Tag writeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putDouble("RPGHealth", RPGHealth);
			nbt.putDouble("RPGHealthMax", RPGHealthMax);
			nbt.putDouble("RPGLevel", RPGLevel);
			nbt.putDouble("RPGXp", RPGXp);
			nbt.putBoolean("OnDamage", OnDamage);
			nbt.putDouble("OnDamageTick", OnDamageTick);
			nbt.putDouble("RPGMaxXp", RPGMaxXp);
			nbt.putDouble("regentick", regentick);
			nbt.putDouble("LevelOverlayX", LevelOverlayX);
			nbt.putDouble("LevelOverlayY", LevelOverlayY);
			nbt.putString("overlayConfigTarget", overlayConfigTarget);
			nbt.putDouble("OverlayConfigPrecision", OverlayConfigPrecision);
			nbt.putDouble("earningNotifTick", earningNotifTick);
			nbt.putString("earning_text1", earning_text1);
			nbt.putString("earning_text2", earning_text2);
			nbt.putString("earning_text3", earning_text3);
			nbt.putDouble("earningNotifX", earningNotifX);
			nbt.putDouble("earningNotifY", earningNotifY);
			nbt.putDouble("stats_scale", stats_scale);
			nbt.putDouble("earning_lastvalue", earning_lastvalue);
			nbt.putDouble("playerxpmultiplier", playerxpmultiplier);
			nbt.putString("EarningWikiTarget", EarningWikiTarget);
			nbt.putString("buysearch", buysearch);
			nbt.putString("sellsearch", sellsearch);
			nbt.putDouble("shop_money", shop_money);
			nbt.put("tempitem", tempitem.save(new CompoundTag()));
			nbt.putDouble("tempitemprice", tempitemprice);
			nbt.putString("tempshopactiontype", tempshopactiontype);
			return nbt;
		}

		public void readNBT(Tag tag) {
			CompoundTag nbt = (CompoundTag) tag;
			RPGHealth = nbt.getDouble("RPGHealth");
			RPGHealthMax = nbt.getDouble("RPGHealthMax");
			RPGLevel = nbt.getDouble("RPGLevel");
			RPGXp = nbt.getDouble("RPGXp");
			OnDamage = nbt.getBoolean("OnDamage");
			OnDamageTick = nbt.getDouble("OnDamageTick");
			RPGMaxXp = nbt.getDouble("RPGMaxXp");
			regentick = nbt.getDouble("regentick");
			LevelOverlayX = nbt.getDouble("LevelOverlayX");
			LevelOverlayY = nbt.getDouble("LevelOverlayY");
			overlayConfigTarget = nbt.getString("overlayConfigTarget");
			OverlayConfigPrecision = nbt.getDouble("OverlayConfigPrecision");
			earningNotifTick = nbt.getDouble("earningNotifTick");
			earning_text1 = nbt.getString("earning_text1");
			earning_text2 = nbt.getString("earning_text2");
			earning_text3 = nbt.getString("earning_text3");
			earningNotifX = nbt.getDouble("earningNotifX");
			earningNotifY = nbt.getDouble("earningNotifY");
			stats_scale = nbt.getDouble("stats_scale");
			earning_lastvalue = nbt.getDouble("earning_lastvalue");
			playerxpmultiplier = nbt.getDouble("playerxpmultiplier");
			EarningWikiTarget = nbt.getString("EarningWikiTarget");
			buysearch = nbt.getString("buysearch");
			sellsearch = nbt.getString("sellsearch");
			shop_money = nbt.getDouble("shop_money");
			tempitem = ItemStack.of(nbt.getCompound("tempitem"));
			tempitemprice = nbt.getDouble("tempitemprice");
			tempshopactiontype = nbt.getString("tempshopactiontype");
		}
	}

	public static class PlayerVariablesSyncMessage {
		private final PlayerVariables data;

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this.data = new PlayerVariables();
			this.data.readNBT(buffer.readNbt());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt((CompoundTag) message.data.writeNBT());
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
					variables.RPGHealth = message.data.RPGHealth;
					variables.RPGHealthMax = message.data.RPGHealthMax;
					variables.RPGLevel = message.data.RPGLevel;
					variables.RPGXp = message.data.RPGXp;
					variables.OnDamage = message.data.OnDamage;
					variables.OnDamageTick = message.data.OnDamageTick;
					variables.RPGMaxXp = message.data.RPGMaxXp;
					variables.regentick = message.data.regentick;
					variables.LevelOverlayX = message.data.LevelOverlayX;
					variables.LevelOverlayY = message.data.LevelOverlayY;
					variables.overlayConfigTarget = message.data.overlayConfigTarget;
					variables.OverlayConfigPrecision = message.data.OverlayConfigPrecision;
					variables.earningNotifTick = message.data.earningNotifTick;
					variables.earning_text1 = message.data.earning_text1;
					variables.earning_text2 = message.data.earning_text2;
					variables.earning_text3 = message.data.earning_text3;
					variables.earningNotifX = message.data.earningNotifX;
					variables.earningNotifY = message.data.earningNotifY;
					variables.stats_scale = message.data.stats_scale;
					variables.earning_lastvalue = message.data.earning_lastvalue;
					variables.playerxpmultiplier = message.data.playerxpmultiplier;
					variables.EarningWikiTarget = message.data.EarningWikiTarget;
					variables.buysearch = message.data.buysearch;
					variables.sellsearch = message.data.sellsearch;
					variables.shop_money = message.data.shop_money;
					variables.tempitem = message.data.tempitem;
					variables.tempitemprice = message.data.tempitemprice;
					variables.tempshopactiontype = message.data.tempshopactiontype;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
