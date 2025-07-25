package fr.eriniumgroup.skyzeradventure.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.Packet;

import java.util.Random;

import fr.eriniumgroup.skyzeradventure.procedures.EplosiveOrbAmmoProjectileHitsBlockProcedure;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModItems;
import fr.eriniumgroup.skyzeradventure.init.SkyzeradventureModEntities;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class EplosiveOrbAmmoEntity extends AbstractArrow implements ItemSupplier {
	public static final ItemStack PROJECTILE_ITEM = new ItemStack(SkyzeradventureModItems.EXPLOSIVE_ORB.get());

	public EplosiveOrbAmmoEntity(PlayMessages.SpawnEntity packet, Level world) {
		super(SkyzeradventureModEntities.EPLOSIVE_ORB_AMMO.get(), world);
		setNoGravity(true);
	}

	public EplosiveOrbAmmoEntity(EntityType<? extends EplosiveOrbAmmoEntity> type, Level world) {
		super(type, world);
		setNoGravity(true);
	}

	public EplosiveOrbAmmoEntity(EntityType<? extends EplosiveOrbAmmoEntity> type, double x, double y, double z, Level world) {
		super(type, x, y, z, world);
		setNoGravity(true);
	}

	public EplosiveOrbAmmoEntity(EntityType<? extends EplosiveOrbAmmoEntity> type, LivingEntity entity, Level world) {
		super(type, entity, world);
		setNoGravity(true);
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem() {
		return PROJECTILE_ITEM;
	}

	@Override
	protected ItemStack getPickupItem() {
		return PROJECTILE_ITEM;
	}

	@Override
	protected void doPostHurtEffects(LivingEntity entity) {
		super.doPostHurtEffects(entity);
		entity.setArrowCount(entity.getArrowCount() - 1);
	}

	@Override
	public void onHitBlock(BlockHitResult blockHitResult) {
		super.onHitBlock(blockHitResult);
		EplosiveOrbAmmoProjectileHitsBlockProcedure.execute(this.level, blockHitResult.getBlockPos().getX(), blockHitResult.getBlockPos().getY(), blockHitResult.getBlockPos().getZ());
	}

	@Override
	public void tick() {
		super.tick();
		if (this.inGround)
			this.discard();
	}

	public static EplosiveOrbAmmoEntity shoot(Level world, LivingEntity entity, Random source) {
		return shoot(world, entity, source, 1f, 0, 0);
	}

	public static EplosiveOrbAmmoEntity shoot(Level world, LivingEntity entity, Random source, float pullingPower) {
		return shoot(world, entity, source, pullingPower * 1f, 0, 0);
	}

	public static EplosiveOrbAmmoEntity shoot(Level world, LivingEntity entity, Random random, float power, double damage, int knockback) {
		EplosiveOrbAmmoEntity entityarrow = new EplosiveOrbAmmoEntity(SkyzeradventureModEntities.EPLOSIVE_ORB_AMMO.get(), entity, world);
		entityarrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
		entityarrow.setSilent(true);
		entityarrow.setCritArrow(false);
		entityarrow.setBaseDamage(damage);
		entityarrow.setKnockback(knockback);
		world.addFreshEntity(entityarrow);
		world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")), SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return entityarrow;
	}

	public static EplosiveOrbAmmoEntity shoot(LivingEntity entity, LivingEntity target) {
		EplosiveOrbAmmoEntity entityarrow = new EplosiveOrbAmmoEntity(SkyzeradventureModEntities.EPLOSIVE_ORB_AMMO.get(), entity, entity.level);
		double dx = target.getX() - entity.getX();
		double dy = target.getY() + target.getEyeHeight() - 1.1;
		double dz = target.getZ() - entity.getZ();
		entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 1f * 2, 12.0F);
		entityarrow.setSilent(true);
		entityarrow.setBaseDamage(0);
		entityarrow.setKnockback(0);
		entityarrow.setCritArrow(false);
		entity.level.addFreshEntity(entityarrow);
		entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")), SoundSource.PLAYERS, 1, 1f / (new Random().nextFloat() * 0.5f + 1));
		return entityarrow;
	}
}