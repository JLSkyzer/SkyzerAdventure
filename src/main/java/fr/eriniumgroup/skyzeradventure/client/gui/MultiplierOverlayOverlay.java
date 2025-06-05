
package fr.eriniumgroup.skyzeradventure.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.eriniumgroup.skyzeradventure.ARGBToInt;
import fr.eriniumgroup.skyzeradventure.network.SkyzeradventureModVariables;
import net.minecraft.client.gui.GuiComponent;
import org.checkerframework.checker.units.qual.h;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.client.Minecraft;

import java.text.DecimalFormat;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class MultiplierOverlayOverlay {
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGameOverlayEvent.Pre event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
			int w = event.getWindow().getGuiScaledWidth();
			int h = event.getWindow().getGuiScaledHeight();
			int posX = w / 2;
			int posY = h / 2;
			Level world = null;
			double x = 0;
			double y = 0;
			double z = 0;
			Player entity = Minecraft.getInstance().player;
			if (entity != null) {
				world = entity.level;
				x = entity.getX();
				y = entity.getY();
				z = entity.getZ();
			}

			assert entity != null;
			SkyzeradventureModVariables.PlayerVariables playercap = (entity.getCapability(SkyzeradventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SkyzeradventureModVariables.PlayerVariables()));
			PoseStack pose = event.getMatrixStack();

			String playerMulti = "Player bonus : x" + new DecimalFormat("##.#").format(playercap.playerxpmultiplier);
			String serverMulti = "Server bonus : x" + new DecimalFormat("##.#").format(SkyzeradventureModVariables.MapVariables.get(world).serverxpmultiplier);

			if (true) {
				GuiComponent.drawString(event.getMatrixStack(), Minecraft.getInstance().font, playerMulti, (int) w - Minecraft.getInstance().font.width(playerMulti), posY - 8, ARGBToInt.ARGBToInt(255, 255, 255, 255));
				GuiComponent.drawString(event.getMatrixStack(), Minecraft.getInstance().font, serverMulti, (int) w - Minecraft.getInstance().font.width(serverMulti), posY + 1, ARGBToInt.ARGBToInt(255, 255, 255, 255));
			}
		}
	}
}
