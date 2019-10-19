package info.u_team.enhanced_anvil.block;

import info.u_team.enhanced_anvil.entity.EnhancedAnvilFallingBlockEntity;
import info.u_team.enhanced_anvil.init.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnhancedAnvilBlock extends UAnvilBlock {
	
	public EnhancedAnvilBlock(String name) {
		super(name, EnhancedAnvilItemGroups.GROUP, Properties.create(Material.ANVIL, MaterialColor.IRON).hardnessAndResistance(5.0F, 1200.0F).sound(SoundType.ANVIL));
	}
	
	public BlockState damageAnvil(BlockState state) {
		final Block block = state.getBlock();
		if (block == EnhancedAnvilBlocks.ENHANCED_ANVIL) {
			return EnhancedAnvilBlocks.ENHANCED_CHIPPED_ANVIL.getDefaultState().with(FACING, state.get(FACING));
		} else {
			return block == EnhancedAnvilBlocks.ENHANCED_CHIPPED_ANVIL ? EnhancedAnvilBlocks.ENHANCED_DAMAGED_ANVIL.getDefaultState().with(FACING, state.get(FACING)) : null;
		}
	}
	
	@Override
	protected void checkFallable(World world, BlockPos pos) {
		if (world.isAirBlock(pos.down()) || canFallThrough(world.getBlockState(pos.down())) && pos.getY() >= 0) {
			if (!world.isRemote) {
				final FallingBlockEntity entity = new EnhancedAnvilFallingBlockEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, world.getBlockState(pos));
				onStartFalling(entity);
				world.addEntity(entity);
			}
		}
	}
}
