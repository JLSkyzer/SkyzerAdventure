package fr.eriniumgroup.skyzeradventure.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;

import java.util.concurrent.atomic.AtomicReference;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModMenus;

public class SellingSellProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((entity instanceof Player _entity0 && _entity0.containerMenu instanceof SkyzeradventureModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "amount", "") : "") <= new Object() {
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
		}.convert((entity instanceof Player _entity2 && _entity2.containerMenu instanceof SkyzeradventureModMenus.MenuAccessor _menu2) ? _menu2.getMenuState(0, "amount", "") : "") > 0) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = ((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).tempitem);
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove == p, (int) new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert((entity instanceof Player _entity3 && _entity3.containerMenu instanceof SkyzeradventureModMenus.MenuAccessor _menu3) ? _menu3.getMenuState(0, "amount", "") : ""), _player.inventoryMenu.getCraftSlots());
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
				}.convert((entity instanceof Player _entity5 && _entity5.containerMenu instanceof SkyzeradventureModMenus.MenuAccessor _menu5) ? _menu5.getMenuState(0, "amount", "") : "")
						* (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).tempitemprice;
				entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.shop_money = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent(("\u00A7a+ " + (new java.text.DecimalFormat("#,###.####").format(new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert((entity instanceof Player _entity6 && _entity6.containerMenu instanceof SkyzeradventureModMenus.MenuAccessor _menu6) ? _menu6.getMenuState(0, "amount", "") : "")
						* (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).tempitemprice)) + "$")), false);
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