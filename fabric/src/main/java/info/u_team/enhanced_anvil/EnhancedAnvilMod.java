package info.u_team.enhanced_anvil;

import info.u_team.u_team_core.util.annotation.AnnotationManager;
import net.fabricmc.api.ModInitializer;

public class EnhancedAnvilMod implements ModInitializer {
	
	public static final String MODID = EnhancedAnvilReference.MODID;
	
	@Override
	public void onInitialize() {
		AnnotationManager.callAnnotations(MODID);
	}
	
}
