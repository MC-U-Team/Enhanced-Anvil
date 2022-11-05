package info.u_team.enhanced_anvil.data;

import info.u_team.enhanced_anvil.EnhancedAnvilMod;
import info.u_team.enhanced_anvil.data.provider.EnhancedAnvilBlockStateProvider;
import info.u_team.enhanced_anvil.data.provider.EnhancedAnvilBlockTagsProvider;
import info.u_team.enhanced_anvil.data.provider.EnhancedAnvilItemModelProvider;
import info.u_team.enhanced_anvil.data.provider.EnhancedAnvilItemTagsProvider;
import info.u_team.enhanced_anvil.data.provider.EnhancedAnvilLanguagesProvider;
import info.u_team.enhanced_anvil.data.provider.EnhancedAnvilLootTableProvider;
import info.u_team.enhanced_anvil.data.provider.EnhancedAnvilRecipeProvider;
import info.u_team.u_team_core.data.GenerationData;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = EnhancedAnvilMod.MODID, bus = Bus.MOD)
public class EnhancedAnvilDataGenerator {
	
	@SubscribeEvent
	public static void data(GatherDataEvent event) {
		final GenerationData data = new GenerationData(EnhancedAnvilMod.MODID, event);
		data.addProvider(event.includeClient(), EnhancedAnvilBlockStateProvider::new);
		data.addProvider(event.includeClient(), EnhancedAnvilItemModelProvider::new);
		data.addProvider(event.includeClient(), EnhancedAnvilLanguagesProvider::new);
		
		data.addProvider(event.includeServer(), EnhancedAnvilBlockTagsProvider::new, EnhancedAnvilItemTagsProvider::new);
		data.addProvider(event.includeServer(), EnhancedAnvilRecipeProvider::new);
		data.addProvider(event.includeServer(), EnhancedAnvilLootTableProvider::new);
	}
}
