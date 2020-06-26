package info.u_team.enhanced_anvil.init;

import info.u_team.enhanced_anvil.EnhancedAnvilMod;
import info.u_team.enhanced_anvil.screen.EnhancedAnvilScreen;
import info.u_team.u_team_core.util.registry.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = EnhancedAnvilMod.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class EnhancedAnvilScreens {
	
	@SubscribeEvent
	public static void register(FMLClientSetupEvent event) {
		MainThreadWorker.run(() -> {
			ClientRegistry.registryScreen(EnhancedAnvilContainerTypes.ENHANCED_ANVIL, EnhancedAnvilScreen::new);
		});
	}
	
}
