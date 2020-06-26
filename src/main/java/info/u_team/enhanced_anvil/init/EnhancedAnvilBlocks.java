package info.u_team.enhanced_anvil.init;

import info.u_team.enhanced_anvil.EnhancedAnvilMod;
import info.u_team.enhanced_anvil.block.EnhancedAnvilBlock;
import info.u_team.u_team_core.util.registry.BlockDeferredRegister;
import net.minecraftforge.eventbus.api.IEventBus;

public class EnhancedAnvilBlocks {
	
	public static final BlockDeferredRegister BLOCKS = BlockDeferredRegister.create(EnhancedAnvilMod.MODID);
	
	public static final EnhancedAnvilBlock ENHANCED_ANVIL = new EnhancedAnvilBlock("enhanced_anvil");
	public static final EnhancedAnvilBlock CHIPPED_ENHANCED_ANVIL = new EnhancedAnvilBlock("chipped_enhanced_anvil");
	public static final EnhancedAnvilBlock DAMAGED_ENHANCED_ANVIL = new EnhancedAnvilBlock("damaged_enhanced_anvil");
	
	public static void register(IEventBus bus) {
		BLOCKS.register(bus);
	}
	
}
