package info.u_team.enhanced_anvil.init;

import info.u_team.enhanced_anvil.EnhancedAnvilReference;
import info.u_team.enhanced_anvil.block.EnhancedAnvilBlock;
import info.u_team.u_team_core.api.registry.BlockRegister;
import info.u_team.u_team_core.api.registry.BlockRegistryEntry;
import net.minecraft.world.item.BlockItem;

public class EnhancedAnvilBlocks {
	
	public static final BlockRegister BLOCKS = BlockRegister.create(EnhancedAnvilReference.MODID);
	
	public static final BlockRegistryEntry<EnhancedAnvilBlock, BlockItem> ENHANCED_ANVIL = BLOCKS.register("enhanced_anvil", EnhancedAnvilBlock::new);
	public static final BlockRegistryEntry<EnhancedAnvilBlock, BlockItem> CHIPPED_ENHANCED_ANVIL = BLOCKS.register("chipped_enhanced_anvil", EnhancedAnvilBlock::new);
	public static final BlockRegistryEntry<EnhancedAnvilBlock, BlockItem> DAMAGED_ENHANCED_ANVIL = BLOCKS.register("damaged_enhanced_anvil", EnhancedAnvilBlock::new);
	
	static void register() {
		BLOCKS.register();
	}
	
}
