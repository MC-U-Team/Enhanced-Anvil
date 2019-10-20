package info.u_team.enhanced_anvil.block;

import info.u_team.enhanced_anvil.container.EnhancedAnvilContainer;
import info.u_team.enhanced_anvil.entity.EnhancedAnvilFallingBlockEntity;
import info.u_team.enhanced_anvil.init.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.inventory.container.*;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class EnhancedAnvilBlock extends UAnvilBlock {
	
	private static final TranslationTextComponent NAME = new TranslationTextComponent("container.enhancedanvil.enhanved_anvil");
	
	public EnhancedAnvilBlock(String name) {
		super(name, EnhancedAnvilItemGroups.GROUP, Properties.create(Material.ANVIL, MaterialColor.IRON).hardnessAndResistance(5.0F, 1200.0F).sound(SoundType.ANVIL));
	}
	
	@Override
	public INamedContainerProvider getContainer(BlockState state, World world, BlockPos pos) {
		return new SimpleNamedContainerProvider((id, playerInventory, player) -> new EnhancedAnvilContainer(id, playerInventory, IWorldPosCallable.of(world, pos)), NAME);
	}
	
	public BlockState damageAnvil(BlockState state) {
		final Block block = state.getBlock();
		if (block == EnhancedAnvilBlocks.ENHANCED_ANVIL) {
			return EnhancedAnvilBlocks.CHIPPED_ENHANCED_ANVIL.getDefaultState().with(FACING, state.get(FACING));
		} else {
			return block == EnhancedAnvilBlocks.CHIPPED_ENHANCED_ANVIL ? EnhancedAnvilBlocks.DAMAGED_ENHANCED_ANVIL.getDefaultState().with(FACING, state.get(FACING)) : null;
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
