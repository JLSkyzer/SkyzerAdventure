package fr.eriniumgroup.skyzeradventure.item;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionResult;

import fr.eriniumgroup.skyzeradventure.procedures.BossSpawnerItemRightclickedOnBlockProcedure;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModTabs;

public class BossSpawnerItemItem extends Item {
	public BossSpawnerItemItem() {
		super(new Item.Properties().tab(SkyzeradventureModTabs.TAB_ITEMS_TAB).stacksTo(1));
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		BossSpawnerItemRightclickedOnBlockProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ(), context.getLevel().getBlockState(context.getClickedPos()));
		return InteractionResult.SUCCESS;
	}
}