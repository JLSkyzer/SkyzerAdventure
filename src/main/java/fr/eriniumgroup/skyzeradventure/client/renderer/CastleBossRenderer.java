package fr.eriniumgroup.skyzeradventure.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import fr.eriniumgroup.skyzeradventure.entity.CastleBossEntity;
import fr.eriniumgroup.skyzeradventure.client.model.Modelcastlemonster;

public class CastleBossRenderer extends MobRenderer<CastleBossEntity, Modelcastlemonster<CastleBossEntity>> {
	public CastleBossRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelcastlemonster<CastleBossEntity>(context.bakeLayer(Modelcastlemonster.LAYER_LOCATION)), 3f);
	}

	@Override
	public ResourceLocation getTextureLocation(CastleBossEntity entity) {
		return new ResourceLocation("skyzeradventure:textures/entities/texture_castleboss.png");
	}
}
