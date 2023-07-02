package info.u_team.enhanced_anvil.entity;

import java.util.function.Predicate;

import info.u_team.enhanced_anvil.block.EnhancedAnvilBlock;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.state.BlockState;

public class EnhancedAnvilFallingBlockEntity extends FallingBlockEntity {
	
	public EnhancedAnvilFallingBlockEntity(Level level, double x, double y, double z, BlockState state) {
		super(level, x, y, z, state);
	}
	
	@Override
	public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source) {
		final DamageSource damageSource;
		if (!hurtEntities) {
			return false;
		}
		final int fallDistanceRounded = Mth.ceil(fallDistance - 1.0f);
		if (fallDistanceRounded < 0) {
			return false;
		}
		final Predicate<Entity> predicate = EntitySelector.NO_CREATIVE_OR_SPECTATOR.and(EntitySelector.LIVING_ENTITY_STILL_ALIVE);
		if (blockState.getBlock() instanceof Fallable fallable) {
			damageSource = fallable.getFallDamageSource(this);
		} else {
			damageSource = damageSources().fallingBlock(this);
		}
		float damage = Math.min(Mth.floor(fallDistanceRounded * fallDamagePerDistance), fallDamageMax);
		level().getEntities(this, getBoundingBox(), predicate).forEach(entity -> entity.hurt(damageSource, damage));
		if (blockState.getBlock() instanceof final EnhancedAnvilBlock block && damage > 0.0f && this.random.nextFloat() < 0.05f + (float) fallDistanceRounded * 0.05f) {
			final BlockState damagedState = block.damageAnvil(blockState);
			if (damagedState == null) {
				cancelDrop = true;
			} else {
				blockState = damagedState;
			}
		}
		return false;
	}
}
