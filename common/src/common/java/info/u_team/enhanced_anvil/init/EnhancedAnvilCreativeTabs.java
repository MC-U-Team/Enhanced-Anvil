package info.u_team.enhanced_anvil.init;

import info.u_team.enhanced_anvil.EnhancedAnvilReference;
import info.u_team.u_team_core.api.registry.CreativeModeTabRegister;
import info.u_team.u_team_core.api.registry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class EnhancedAnvilCreativeTabs {
	
	public static final CreativeModeTabRegister CREATIVE_TABS = CreativeModeTabRegister.create(EnhancedAnvilReference.MODID);
	
	public static final RegistryEntry<CreativeModeTab> TAB = CREATIVE_TABS.register("tab", builder -> {
		builder.icon(() -> new ItemStack(EnhancedAnvilBlocks.ENHANCED_ANVIL.get()));
		builder.displayItems((parameters, output) -> {
			EnhancedAnvilBlocks.BLOCKS.itemIterable().forEach(output::accept);
		});
	});
	
	static void register() {
		CREATIVE_TABS.register();
	}
}
