package info.u_team.enhanced_anvil.init;

import info.u_team.enhanced_anvil.EnhancedAnvilMod;
import info.u_team.enhanced_anvil.container.EnhancedAnvilContainer;
import info.u_team.u_team_core.containertype.UContainerType;
import info.u_team.u_team_core.util.registry.CommonDeferredRegister;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;

public class EnhancedAnvilContainerTypes {
	
	public static final CommonDeferredRegister<ContainerType<?>> CONTAINER_TYPES = CommonDeferredRegister.create(ForgeRegistries.CONTAINERS, EnhancedAnvilMod.MODID);
	
	public static final UContainerType<EnhancedAnvilContainer> ENHANCED_ANVIL = new UContainerType<EnhancedAnvilContainer>("enhanced_anvil", EnhancedAnvilContainer::new);
	
	public static void register(IEventBus bus) {
		CONTAINER_TYPES.register(bus);
	}
}
