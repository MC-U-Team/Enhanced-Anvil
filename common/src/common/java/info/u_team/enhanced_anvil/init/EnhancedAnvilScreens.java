package info.u_team.enhanced_anvil.init;

import info.u_team.enhanced_anvil.screen.EnhancedAnvilScreen;
import info.u_team.u_team_core.api.registry.client.MenuScreenRegister;
import net.minecraft.Util;

public class EnhancedAnvilScreens {
	
	private static final MenuScreenRegister MENU_SCREENS = Util.make(MenuScreenRegister.create(), menuScreens -> {
		menuScreens.register(EnhancedAnvilMenuTypes.ENHANCED_ANVIL, EnhancedAnvilScreen::new);
	});
	
	static void register() {
		MENU_SCREENS.register();
	}
	
}
