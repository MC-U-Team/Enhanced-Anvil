package info.u_team.enhanced_anvil.init;

import info.u_team.enhanced_anvil.screen.EnhancedAnvilScreen;
import info.u_team.u_team_core.util.registry.ClientRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class EnhancedAnvilScreens {
	
	private static void setup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			ClientRegistry.registerScreen(EnhancedAnvilContainerTypes.ENHANCED_ANVIL, EnhancedAnvilScreen::new);
		});
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(EnhancedAnvilScreens::setup);
	}
	
}
