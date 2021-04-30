package info.u_team.enhanced_anvil.block;

import java.util.Optional;
import java.util.Random;
import java.util.function.BiFunction;

import info.u_team.enhanced_anvil.container.EnhancedAnvilContainer;
import info.u_team.enhanced_anvil.entity.EnhancedAnvilFallingBlockEntity;
import info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks;
import info.u_team.enhanced_anvil.init.EnhancedAnvilItemGroups;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class EnhancedAnvilBlock extends UAnvilBlock {
	
	private static final TranslationTextComponent NAME = new TranslationTextComponent("container.enhancedanvil.enhanved_anvil");
	
	public EnhancedAnvilBlock() {
		super(EnhancedAnvilItemGroups.GROUP, Properties.create(Material.ANVIL, MaterialColor.IRON).hardnessAndResistance(5.0F, 1200.0F).sound(SoundType.ANVIL));
	}
	
	@Override
	public INamedContainerProvider getContainer(BlockState state, World world, BlockPos pos) {
		return new SimpleNamedContainerProvider((id, playerInventory, player) -> new EnhancedAnvilContainer(id, playerInventory, new IWorldPosCallable() {
			
			@Override
			public <T> Optional<T> apply(BiFunction<World, BlockPos, T> function) {
				return Optional.ofNullable(function.apply(world, pos));
			}
		}), NAME);
	}
	
	public BlockState damageAnvil(BlockState state) {
		final Block block = state.getBlock();
		if (block == EnhancedAnvilBlocks.ENHANCED_ANVIL.get()) {
			return EnhancedAnvilBlocks.CHIPPED_ENHANCED_ANVIL.get().getDefaultState().with(FACING, state.get(FACING));
		} else {
			return block == EnhancedAnvilBlocks.CHIPPED_ENHANCED_ANVIL.get() ? EnhancedAnvilBlocks.DAMAGED_ENHANCED_ANVIL.get().getDefaultState().with(FACING, state.get(FACING)) : null;
		}
	}
	
	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		if (world.isAirBlock(pos.down()) || canFallThrough(world.getBlockState(pos.down())) && pos.getY() >= 0) {
			if (!world.isRemote) {
				final FallingBlockEntity entity = new EnhancedAnvilFallingBlockEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, world.getBlockState(pos));
				onStartFalling(entity);
				world.addEntity(entity);
			}
		}
	}
}
