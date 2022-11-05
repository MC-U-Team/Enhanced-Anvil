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
		if (hurtEntities) {
			final int fallDistanceRounded = Mth.ceil(fallDistance - 1.0F);
			if (fallDistanceRounded < 0) {
				return false;
			} else {
				final Predicate<Entity> predicate;
				final DamageSource damagesource;
				if (blockState.getBlock() instanceof final Fallable fallable) {
					predicate = fallable.getHurtsEntitySelector();
					damagesource = fallable.getFallDamageSource();
				} else {
					predicate = EntitySelector.NO_SPECTATORS;
					damagesource = DamageSource.FALLING_BLOCK;
				}
				final float damage = Math.min(Mth.floor(fallDistanceRounded * fallDamagePerDistance), fallDamageMax);
				level.getEntities(this, getBoundingBox(), predicate).forEach(entity -> entity.hurt(damagesource, damage));
				
				if (blockState.getBlock() instanceof final EnhancedAnvilBlock block && damage > 0.0F && random.nextFloat() < 0.05F + fallDistanceRounded * 0.05F) {
					final BlockState damagedState = block.damageAnvil(blockState);
					if (damagedState == null) {
						cancelDrop = true;
					} else {
						blockState = damagedState;
					}
				}
			}
		}
		return false;
	}
}
