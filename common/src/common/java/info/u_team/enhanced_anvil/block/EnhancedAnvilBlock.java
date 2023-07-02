package info.u_team.enhanced_anvil.block;

import org.jetbrains.annotations.Nullable;

import info.u_team.enhanced_anvil.entity.EnhancedAnvilFallingBlockEntity;
import info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks;
import info.u_team.enhanced_anvil.menu.EnhancedAnvilMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class EnhancedAnvilBlock extends UAnvilBlock {
	
	private static final Component CONTAINER_TITLE = Component.translatable("container.enhancedanvil.enhanved_anvil");
	
	public EnhancedAnvilBlock() {
		super(Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(5, 1200).sound(SoundType.ANVIL).pushReaction(PushReaction.BLOCK));
	}
	
	@Override
	@Nullable
	public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
		return new SimpleMenuProvider((id, playerInventory, player) -> {
			return new EnhancedAnvilMenu(id, playerInventory, ContainerLevelAccess.create(level, pos));
		}, CONTAINER_TITLE);
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
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (isFree(level.getBlockState(pos.below())) && pos.getY() >= level.getMinBuildHeight()) {
			final FallingBlockEntity entity = new EnhancedAnvilFallingBlockEntity(level, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, state.hasProperty(BlockStateProperties.WATERLOGGED) ? state.setValue(BlockStateProperties.WATERLOGGED, false) : state);
			level.setBlock(pos, state.getFluidState().createLegacyBlock(), 3);
			level.addFreshEntity(entity);
			falling(entity);
		}
	}
}
