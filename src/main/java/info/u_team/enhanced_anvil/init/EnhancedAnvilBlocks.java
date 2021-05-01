package info.u_team.enhanced_anvil.init;

import info.u_team.enhanced_anvil.EnhancedAnvilMod;
import info.u_team.enhanced_anvil.block.EnhancedAnvilBlock;
import info.u_team.u_team_core.util.registry.BlockDeferredRegister;
import info.u_team.u_team_core.util.registry.BlockRegistryObject;
import net.minecraft.item.BlockItem;
import net.minecraftforge.eventbus.api.IEventBus;

public class EnhancedAnvilBlocks {
	
	public static final BlockDeferredRegister BLOCKS = BlockDeferredRegister.create(EnhancedAnvilMod.MODID);
	
	public static final BlockRegistryObject<EnhancedAnvilBlock, BlockItem> ENHANCED_ANVIL = BLOCKS.register("enhanced_anvil", EnhancedAnvilBlock::new);
	public static final BlockRegistryObject<EnhancedAnvilBlock, BlockItem> CHIPPED_ENHANCED_ANVIL = BLOCKS.register("chipped_enhanced_anvil", EnhancedAnvilBlock::new);
	public static final BlockRegistryObject<EnhancedAnvilBlock, BlockItem> DAMAGED_ENHANCED_ANVIL = BLOCKS.register("damaged_enhanced_anvil", EnhancedAnvilBlock::new);
	
	public static void registerMod(IEventBus bus) {
		BLOCKS.register(bus);
	}
	
}
