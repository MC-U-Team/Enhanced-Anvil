package info.u_team.enhanced_anvil.init;

import info.u_team.enhanced_anvil.EnhancedAnvilReference;
import info.u_team.enhanced_anvil.menu.EnhancedAnvilMenu;
import info.u_team.u_team_core.api.registry.CommonRegister;
import info.u_team.u_team_core.api.registry.RegistryEntry;
import info.u_team.u_team_core.menutype.UMenuType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.MenuType.MenuSupplier;

public class EnhancedAnvilMenuTypes {
	
	public static final CommonRegister<MenuType<?>> MENU_TYPES = CommonRegister.create(Registries.MENU, EnhancedAnvilReference.MODID);
	
	public static final RegistryEntry<UMenuType<EnhancedAnvilMenu>> ENHANCED_ANVIL = MENU_TYPES.register("enhanced_anvil", () -> new UMenuType<>((MenuSupplier<EnhancedAnvilMenu>) EnhancedAnvilMenu::new));
	
	static void register() {
		MENU_TYPES.register();
	}
}
