package info.u_team.enhanced_anvil;

import info.u_team.enhanced_anvil.init.*;
import info.u_team.u_team_core.util.registry.BusRegister;
import info.u_team.u_team_core.util.verify.JarSignVerifier;
import net.minecraftforge.fml.common.Mod;

@Mod(EnhancedAnvilMod.MODID)
public class EnhancedAnvilMod {
	
	public static final String MODID = "enhancedanvil";
	
	public EnhancedAnvilMod() {
		JarSignVerifier.checkSigned(MODID);
		register();
	}
	
	private void register() {
		BusRegister.registerMod(EnhancedAnvilBlocks::register);
		BusRegister.registerMod(EnhancedAnvilContainerTypes::register);
	}
	
}
