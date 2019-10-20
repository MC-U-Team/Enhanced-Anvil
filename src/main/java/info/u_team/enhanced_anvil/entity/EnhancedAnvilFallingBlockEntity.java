package info.u_team.enhanced_anvil.entity;

import java.util.List;

import com.google.common.collect.Lists;

import info.u_team.enhanced_anvil.block.EnhancedAnvilBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EnhancedAnvilFallingBlockEntity extends FallingBlockEntity {
	
	public EnhancedAnvilFallingBlockEntity(World world, double x, double y, double z, BlockState fallingBlockState) {
		super(world, x, y, z, fallingBlockState);
	}
	
	@Override
	public void fall(float distance, float damageMultiplier) {
		if (hurtEntities) {
			final int fallingHeightAttribute = MathHelper.ceil(distance - 1.0F);
			if (fallingHeightAttribute > 0) {
				final List<Entity> list = Lists.newArrayList(world.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox()));
				final boolean isAnvil = fallTile.getBlock() instanceof EnhancedAnvilBlock;
				final DamageSource source = isAnvil ? DamageSource.ANVIL : DamageSource.FALLING_BLOCK;
				
				for (final Entity entity : list) {
					entity.attackEntityFrom(source, Math.min(MathHelper.floor(fallingHeightAttribute * fallHurtAmount), fallHurtMax));
				}
				
				if (isAnvil && rand.nextFloat() < 0.05 + fallingHeightAttribute * 0.05) {
					final BlockState state = ((EnhancedAnvilBlock) fallTile.getBlock()).damageAnvil(fallTile);
					if (state == null) {
						dontSetBlock = true;
					} else {
						fallTile = state;
					}
				}
			}
		}
	}
}
