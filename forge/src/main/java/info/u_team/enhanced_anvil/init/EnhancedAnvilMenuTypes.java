package info.u_team.enhanced_anvil.init;

import info.u_team.enhanced_anvil.EnhancedAnvilMod;
import info.u_team.enhanced_anvil.menu.EnhancedAnvilMenu;
import info.u_team.u_team_core.menutype.UMenuType;
import info.u_team.u_team_core.util.registry.CommonDeferredRegister;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.MenuType.MenuSupplier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EnhancedAnvilMenuTypes {
	
	public static final CommonDeferredRegister<MenuType<?>> MENU_TYPES = CommonDeferredRegister.create(ForgeRegistries.MENU_TYPES, EnhancedAnvilMod.MODID);
	
	public static final RegistryObject<UMenuType<EnhancedAnvilMenu>> ENHANCED_ANVIL = MENU_TYPES.register("enhanced_anvil", () -> new UMenuType<>((MenuSupplier<EnhancedAnvilMenu>) EnhancedAnvilMenu::new));
	
	public static void registerMod(IEventBus bus) {
		MENU_TYPES.register(bus);
	}
}
