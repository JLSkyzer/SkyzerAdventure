package fr.eriniumgroup.skyzeradventure.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;

import java.util.concurrent.atomic.AtomicReference;
import java.util.HashMap;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;

public class SellingSellProcedure {
	public static void execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		if (new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(guistate.containsKey("textin:amount") ? (String) guistate.get("textin:amount") : "") <= new Object() {
			private int returnItemNumber(ItemStack item, Entity entity) {
				ItemStack tempItem = item;
				double count = 0;
				{
					AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
					entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
					if (_iitemhandlerref.get() != null) {
						for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
							ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
							if (itemstackiterator.getItem() == tempItem.getItem()) {
								count = count + (itemstackiterator).getCount();
							}
						}
					}
				}
				return (int) count;
			}
		}.returnItemNumber(((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).tempitem), entity) && new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(guistate.containsKey("textin:amount") ? (String) guistate.get("textin:amount") : "") > 0) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = ((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).tempitem);
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(guistate.containsKey("textin:amount") ? (String) guistate.get("textin:amount") : ""), _player.inventoryMenu.getCraftSlots());
			}
			{
				double _setval = (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).shop_money + new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(guistate.containsKey("textin:amount") ? (String) guistate.get("textin:amount") : "")
						* (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).tempitemprice;
				entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.shop_money = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent(("\u00A7a+ " + new java.text.DecimalFormat("#,###.####").format(new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(guistate.containsKey("textin:amount") ? (String) guistate.get("textin:amount") : "")
						* (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).tempitemprice) + "$")), false);
			if (entity instanceof Player _player)
				_player.closeContainer();
			{
				double _setval = 0;
				entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.tempitemprice = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
