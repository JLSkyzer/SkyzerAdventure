package fr.eriniumgroup.skyzeradventure.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import java.util.concurrent.atomic.AtomicReference;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModMenus;

public class SellingPageWhileThisGUIIsOpenTickProcedure {
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
		}.convert((entity instanceof Player _entity0 && _entity0.containerMenu instanceof SkyzeradventureModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "amount", "") : "") > new Object() {
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
		}.returnItemNumber(((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).tempitem), entity)) {
			if (entity instanceof Player _player && _player.containerMenu instanceof SkyzeradventureModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 0, "amount", (new java.text.DecimalFormat("##").format(new Object() {
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
				}.returnItemNumber(((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).tempitem), entity))), true);
		}
	}
}