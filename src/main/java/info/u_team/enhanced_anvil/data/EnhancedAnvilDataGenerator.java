package info.u_team.enhanced_anvil.data;

import info.u_team.enhanced_anvil.EnhancedAnvilMod;
import info.u_team.enhanced_anvil.data.provider.*;
import info.u_team.u_team_core.data.GenerationData;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = EnhancedAnvilMod.MODID, bus = Bus.MOD)
public class EnhancedAnvilDataGenerator {
	
	@SubscribeEvent
	public static void data(GatherDataEvent event) {
		final GenerationData data = new GenerationData(EnhancedAnvilMod.MODID, event);
		if (event.includeClient()) {
			data.addProvider(EnhancedAnvilLanguagesProvider::new);
		}
		if (event.includeServer()) {
			data.addProvider(EnhancedAnvilBlockTagsProvider::new);
			data.addProvider(EnhancedAnvilItemTagsProvider::new);
			
			data.addProvider(EnhancedAnvilRecipesProvider::new);
			data.addProvider(EnhancedAnvilLootTablesProvider::new);
		}
	}
}
