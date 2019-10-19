package info.u_team.enhanced_anvil.init;

import info.u_team.enhanced_anvil.EnhancedAnvilMod;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.inventory.AnvilScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = EnhancedAnvilMod.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class EnhancedAnvilGuis {
	
	@SubscribeEvent
	public static void register(FMLClientSetupEvent event) {
		ScreenManager.registerFactory(EnhancedAnvilContainerTypes.ENHANCED_ANVIL, AnvilScreen::new);
	}
	
}
