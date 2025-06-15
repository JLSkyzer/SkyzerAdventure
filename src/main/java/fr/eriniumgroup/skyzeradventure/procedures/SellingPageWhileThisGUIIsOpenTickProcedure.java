package fr.eriniumgroup.skyzeradventure.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import java.util.concurrent.atomic.AtomicReference;
import java.util.HashMap;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModMenus;

public class SellingPageWhileThisGUIIsOpenTickProcedure {
	public static void execute(LevelAccessor world, Entity entity, HashMap guistate) {
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
		}.convert(guistate.containsKey("textin:amount") ? (String) guistate.get("textin:amount") : "") > new Object() {
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
			if (entity instanceof ServerPlayer _player && !world.isClientSide())
				SkyzeradventureModMenus.setText("amount", (new java.text.DecimalFormat("##").format(new Object() {
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
				}.returnItemNumber(((entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).tempitem), entity))), _player);
		}
	}
}
