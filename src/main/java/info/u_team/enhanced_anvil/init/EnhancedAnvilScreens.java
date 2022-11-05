package info.u_team.enhanced_anvil.init;

import info.u_team.enhanced_anvil.screen.EnhancedAnvilScreen;
import info.u_team.u_team_core.event.RegisterMenuScreensEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class EnhancedAnvilScreens {
	
	private static void register(RegisterMenuScreensEvent event) {
		event.registerScreen(EnhancedAnvilMenuTypes.ENHANCED_ANVIL, EnhancedAnvilScreen::new);
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(EnhancedAnvilScreens::register);
	}
	
}
