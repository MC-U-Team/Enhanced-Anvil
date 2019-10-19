package info.u_team.enhanced_anvil.init;

import info.u_team.enhanced_anvil.EnhancedAnvilMod;
import info.u_team.enhanced_anvil.entity.EnhancedAnvilFallingBlockEntity;
import info.u_team.u_team_core.entitytype.UEntityType.UBuilder;
import info.u_team.u_team_core.util.registry.BaseRegistryUtil;
import net.minecraft.entity.*;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = EnhancedAnvilMod.MODID, bus = Bus.MOD)
public class EnhancedAnvilEntityTypes {
	
	public static final EntityType<EnhancedAnvilFallingBlockEntity> ENHANCED_ANVIL_FALLING_BLOCK = UBuilder.<EnhancedAnvilFallingBlockEntity> create("enhanced_anvil_falling_block", EnhancedAnvilFallingBlockEntity::new, EntityClassification.MISC).size(0.98F, 0.98F).build();
	
	@SubscribeEvent
	public static void register(Register<EntityType<?>> event) {
		BaseRegistryUtil.getAllGenericRegistryEntriesAndApplyNames(EnhancedAnvilMod.MODID, EntityType.class).forEach(event.getRegistry()::register);
	}
}
