package info.u_team.enhanced_anvil.init;

import java.util.List;

import info.u_team.enhanced_anvil.EnhancedAnvilMod;
import info.u_team.enhanced_anvil.block.EnhancedAnvilBlock;
import info.u_team.u_team_core.util.registry.BaseRegistryUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = EnhancedAnvilMod.MODID, bus = Bus.MOD)
public class EnhancedAnvilBlocks {
	
	public static final EnhancedAnvilBlock ENHANCED_ANVIL = new EnhancedAnvilBlock("enhanced_anvil");
	public static final EnhancedAnvilBlock ENHANCED_CHIPPED_ANVIL = new EnhancedAnvilBlock("chipped_enhanced_anvil");
	public static final EnhancedAnvilBlock ENHANCED_DAMAGED_ANVIL = new EnhancedAnvilBlock("damaged_enhanced_anvil");
	
	@SubscribeEvent
	public static void register(Register<Block> event) {
		entries = BaseRegistryUtil.getAllRegistryEntriesAndApplyNames(EnhancedAnvilMod.MODID, Block.class);
		entries.forEach(event.getRegistry()::register);
	}
	
	@SubscribeEvent
	public static void registerBlockItem(Register<Item> event) {
		BaseRegistryUtil.getBlockItems(entries).forEach(event.getRegistry()::register);
		entries = null; // Dereference list as it is no longer needed
	}
	
	// Just a cache for the block item registry for performance
	private static List<Block> entries;
	
}
