package fr.eriniumgroup.skyzeradventure.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import java.util.concurrent.atomic.AtomicReference;
import java.util.ArrayList;

import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import fr.eriniumgroup.skyzeradventure.configuration.ShopListingConfiguration;

public class AutoSellerOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		String temptext = "";
		double index = 0;
		for (Entity entityiterator : new ArrayList<>(world.players())) {
			if ((entityiterator.getDisplayName().getString()).equals(getBlockNBTString(world, new BlockPos(x, y, z), "target"))) {
				temptext = ShopListingConfiguration.SELL_LISTING.get();
				if (temptext.contains(ForgeRegistries.ITEMS.getKey((itemFromBlockInventory(world, new BlockPos(x, y, z), 0).copy()).getItem()).toString())) {
					for (int index0 = 0; index0 < (int) new Object() {
						private int returnSize(String text, String separator) {
							String[] resultTxt = (text).split(separator);
							return resultTxt.length;
						}
					}.returnSize(temptext, ";"); index0++) {
						if (new Object() {
							private String returnValue(String string, int Index, String sep) {
								try {
									return ((string).split(sep)[Index]);
									// Utilisez account ici
								} catch (ArrayIndexOutOfBoundsException e) {
									// Gérer l'erreur ici, par exemple :
									System.out.println("Valeur null !");
									return "";
								}
							}
						}.returnValue(temptext, (int) index, ";").contains(ForgeRegistries.ITEMS.getKey((itemFromBlockInventory(world, new BlockPos(x, y, z), 0).copy()).getItem()).toString())) {
							temptext = new Object() {
								private String returnValue(String string, int Index, String sep) {
									try {
										return ((string).split(sep)[Index]);
										// Utilisez account ici
									} catch (ArrayIndexOutOfBoundsException e) {
										// Gérer l'erreur ici, par exemple :
										System.out.println("Valeur null !");
										return "";
									}
								}
							}.returnValue(temptext, (int) index, ";");
							{
								double _setval = (entityiterator.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables())).shop_money
										+ ((itemFromBlockInventory(world, new BlockPos(x, y, z), 0).copy())).getCount() * new Object() {
											double convert(String s) {
												try {
													return Double.parseDouble(s.trim());
												} catch (Exception e) {
												}
												return 0;
											}
										}.convert(new Object() {
											private String returnValue(String string, int Index, String sep) {
												try {
													return ((string).split(sep)[Index]);
													// Utilisez account ici
												} catch (ArrayIndexOutOfBoundsException e) {
													// Gérer l'erreur ici, par exemple :
													System.out.println("Valeur null !");
													return "";
												}
											}
										}.returnValue(temptext, 2, ":"));
								entityiterator.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.shop_money = _setval;
									capability.syncPlayerVariables(entityiterator);
								});
							}
							{
								double _setval = 100;
								entityiterator.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.desc_tick = _setval;
									capability.syncPlayerVariables(entityiterator);
								});
							}
							{
								String _setval = "\u00A7a+ " + new java.text.DecimalFormat("#,###.####").format(((itemFromBlockInventory(world, new BlockPos(x, y, z), 0).copy())).getCount() * new Object() {
									double convert(String s) {
										try {
											return Double.parseDouble(s.trim());
										} catch (Exception e) {
										}
										return 0;
									}
								}.convert(new Object() {
									private String returnValue(String string, int Index, String sep) {
										try {
											return ((string).split(sep)[Index]);
											// Utilisez account ici
										} catch (ArrayIndexOutOfBoundsException e) {
											// Gérer l'erreur ici, par exemple :
											System.out.println("Valeur null !");
											return "";
										}
									}
								}.returnValue(temptext, 2, ":"))) + " $";
								entityiterator.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.desc_val = _setval;
									capability.syncPlayerVariables(entityiterator);
								});
							}
							{
								BlockEntity _ent = world.getBlockEntity(new BlockPos(x, y, z));
								if (_ent != null) {
									final int _slotid = 0;
									final ItemStack _setstack = new ItemStack(Blocks.AIR).copy();
									_setstack.setCount(1);
									_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
										if (capability instanceof IItemHandlerModifiable)
											((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
									});
								}
							}
							break;
						}
						index = index + 1;
					}
				} else {
					{
						BlockEntity _ent = world.getBlockEntity(new BlockPos(x, y, z));
						if (_ent != null) {
							final int _slotid = 0;
							final ItemStack _setstack = new ItemStack(Blocks.AIR).copy();
							_setstack.setCount(1);
							_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
								if (capability instanceof IItemHandlerModifiable)
									((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
							});
						}
					}
				}
				break;
			}
		}
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getTileData().getString(tag);
		return "";
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor level, BlockPos pos, int slot) {
		AtomicReference<ItemStack> result = new AtomicReference<>(ItemStack.EMPTY);
		BlockEntity entity = level.getBlockEntity(pos);
		if (entity != null)
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> result.set(capability.getStackInSlot(slot)));
		return result.get();
	}
}
