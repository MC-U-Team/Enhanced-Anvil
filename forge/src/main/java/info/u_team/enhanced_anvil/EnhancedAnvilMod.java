package info.u_team.enhanced_anvil;

import info.u_team.u_team_core.util.annotation.AnnotationManager;
import info.u_team.u_team_core.util.verify.JarSignVerifier;
import net.minecraftforge.fml.common.Mod;

@Mod(EnhancedAnvilMod.MODID)
public class EnhancedAnvilMod {
	
	public static final String MODID = EnhancedAnvilReference.MODID;
	
	public EnhancedAnvilMod() {
		JarSignVerifier.checkSigned(MODID);
		
		AnnotationManager.callAnnotations(MODID);
	}
	
}
