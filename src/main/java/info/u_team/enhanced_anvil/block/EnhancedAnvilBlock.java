package info.u_team.enhanced_anvil.block;

import java.util.Optional;
import java.util.Random;
import java.util.function.BiFunction;

import info.u_team.enhanced_anvil.container.EnhancedAnvilContainer;
import info.u_team.enhanced_anvil.entity.EnhancedAnvilFallingBlockEntity;
import info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks;
import info.u_team.enhanced_anvil.init.EnhancedAnvilItemGroups;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class EnhancedAnvilBlock extends UAnvilBlock {
	
	private static final TranslatableComponent NAME = new TranslatableComponent("container.enhancedanvil.enhanved_anvil");
	
	public EnhancedAnvilBlock() {
		super(EnhancedAnvilItemGroups.GROUP, Properties.of(Material.HEAVY_METAL, MaterialColor.METAL).strength(5.0F, 1200.0F).sound(SoundType.ANVIL));
	}
	
	@Override
	public MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos) {
		return new SimpleMenuProvider((id, playerInventory, player) -> new EnhancedAnvilContainer(id, playerInventory, new ContainerLevelAccess() {
			
			@Override
			public <T> Optional<T> evaluate(BiFunction<Level, BlockPos, T> function) {
				return Optional.ofNullable(function.apply(world, pos));
			}
		}), NAME);
	}
	
	public BlockState damageAnvil(BlockState state) {
		final Block block = state.getBlock();
		if (block == EnhancedAnvilBlocks.ENHANCED_ANVIL.get()) {
			return EnhancedAnvilBlocks.CHIPPED_ENHANCED_ANVIL.get().defaultBlockState().setValue(FACING, state.getValue(FACING));
		} else {
			return block == EnhancedAnvilBlocks.CHIPPED_ENHANCED_ANVIL.get() ? EnhancedAnvilBlocks.DAMAGED_ENHANCED_ANVIL.get().defaultBlockState().setValue(FACING, state.getValue(FACING)) : null;
		}
	}
	
	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, Random rand) {
		if (world.isEmptyBlock(pos.below()) || isFree(world.getBlockState(pos.below())) && pos.getY() >= 0) {
			if (!world.isClientSide) {
				final FallingBlockEntity entity = new EnhancedAnvilFallingBlockEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, world.getBlockState(pos));
				falling(entity);
				world.addFreshEntity(entity);
			}
		}
	}
}
