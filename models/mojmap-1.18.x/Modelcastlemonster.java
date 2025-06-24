// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelcastlemonster<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "castlemonster"), "main");
	private final ModelPart Left_leg;
	private final ModelPart Right_leg;
	private final ModelPart Body;
	private final ModelPart Left_arm;
	private final ModelPart Right_arm;
	private final ModelPart Head;

	public Modelcastlemonster(ModelPart root) {
		this.Left_leg = root.getChild("Left_leg");
		this.Right_leg = root.getChild("Right_leg");
		this.Body = root.getChild("Body");
		this.Left_arm = root.getChild("Left_arm");
		this.Right_arm = root.getChild("Right_arm");
		this.Head = root.getChild("Head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Left_leg = partdefinition.addOrReplaceChild("Left_leg",
				CubeListBuilder.create().texOffs(82, 97)
						.addBox(-4.0F, 14.0F, -2.3333F, 8.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(0, 102)
						.addBox(-3.0F, 0.0F, -1.3333F, 6.0F, 14.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(33, 49)
						.addBox(-4.0F, 26.0F, -4.3333F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(15.0F, -4.0F, -1.6667F));

		PartDefinition Right_leg = partdefinition.addOrReplaceChild("Right_leg",
				CubeListBuilder.create().texOffs(90, 74)
						.addBox(-4.0F, 14.0F, -2.3333F, 8.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(25, 102)
						.addBox(-3.0F, 0.0F, -1.3333F, 6.0F, 14.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(33, 54)
						.addBox(-4.0F, 26.0F, -4.3333F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-15.0F, -4.0F, -1.6667F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-20.0F, -62.0F, -7.0F, 40.0F, 34.0F, 14.0F, new CubeDeformation(0.0F)).texOffs(109, 15)
						.addBox(-3.0F, -65.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(95, 0)
						.addBox(-12.0F, -50.0F, -8.0F, 5.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = Body.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(109, 25)
						.addBox(-2.5F, -23.5F, -0.5F, 2.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-2.5F, -6.2F, -0.5F, 5.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(14.5F, -44.5F, -7.5F, 0.0F, 0.0F, -0.9163F));

		PartDefinition Left_arm = partdefinition.addOrReplaceChild("Left_arm",
				CubeListBuilder.create().texOffs(45, 49)
						.addBox(-5.0F, -3.0F, -6.0F, 10.0F, 40.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(109, 0)
						.addBox(-3.0F, 37.0F, -4.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offset(25.0F, -33.0F, 0.0F));

		PartDefinition Right_arm = partdefinition.addOrReplaceChild("Right_arm",
				CubeListBuilder.create().texOffs(0, 49)
						.addBox(-5.0F, -3.0F, -6.0F, 10.0F, 40.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(50, 102)
						.addBox(-3.0F, 37.0F, -4.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-25.0F, -33.0F, 0.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(90, 49)
				.addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -41.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		Left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.Head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.Head.xRot = headPitch / (180F / (float) Math.PI);
		this.Left_arm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.Right_arm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.Left_leg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.Right_leg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
	}
}