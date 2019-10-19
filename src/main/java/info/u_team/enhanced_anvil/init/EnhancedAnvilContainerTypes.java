package info.u_team.enhanced_anvil.init;

import info.u_team.enhanced_anvil.EnhancedAnvilMod;
import info.u_team.enhanced_anvil.container.EnhancedAnvilContainer;
import info.u_team.u_team_core.containertype.UContainerType;
import info.u_team.u_team_core.util.registry.BaseRegistryUtil;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = EnhancedAnvilMod.MODID, bus = Bus.MOD)
public class EnhancedAnvilContainerTypes {
	
	public static final UContainerType<EnhancedAnvilContainer> ENHANCED_ANVIL = new UContainerType<EnhancedAnvilContainer>("enhanced_anvil", EnhancedAnvilContainer::new);
	
	@SubscribeEvent
	public static void register(Register<ContainerType<?>> event) {
		BaseRegistryUtil.getAllGenericRegistryEntriesAndApplyNames(EnhancedAnvilMod.MODID, ContainerType.class).forEach(event.getRegistry()::register);
	}
}
