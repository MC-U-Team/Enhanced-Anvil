package info.u_team.enhanced_anvil.entity;

import java.util.List;

import com.google.common.collect.Lists;

import info.u_team.enhanced_anvil.block.EnhancedAnvilBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

public class EnhancedAnvilFallingBlockEntity extends FallingBlockEntity {
	
	public EnhancedAnvilFallingBlockEntity(Level world, double x, double y, double z, BlockState fallingBlockState) {
		super(world, x, y, z, fallingBlockState);
	}
	
	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier) {
		if (hurtEntities) {
			final int fallingHeightAttribute = Mth.ceil(distance - 1.0F);
			if (fallingHeightAttribute > 0) {
				final List<Entity> list = Lists.newArrayList(level.getEntities(this, getBoundingBox()));
				final boolean isAnvil = blockState.getBlock() instanceof EnhancedAnvilBlock;
				final DamageSource source = isAnvil ? DamageSource.ANVIL : DamageSource.FALLING_BLOCK;
				
				for (final Entity entity : list) {
					entity.hurt(source, Math.min(Mth.floor(fallingHeightAttribute * fallDamageAmount), fallDamageMax));
				}
				
				if (isAnvil && random.nextFloat() < 0.05 + fallingHeightAttribute * 0.05) {
					final BlockState state = ((EnhancedAnvilBlock) blockState.getBlock()).damageAnvil(blockState);
					if (state == null) {
						cancelDrop = true;
					} else {
						blockState = state;
					}
				}
			}
		}
		return false;
	}
	
}
