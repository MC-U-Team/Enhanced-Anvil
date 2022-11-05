package info.u_team.enhanced_anvil.init;

import info.u_team.enhanced_anvil.EnhancedAnvilMod;
import info.u_team.u_team_core.api.construct.Construct;
import info.u_team.u_team_core.api.construct.ModConstruct;
import info.u_team.u_team_core.util.registry.BusRegister;

@Construct(modid = EnhancedAnvilMod.MODID)
public class EnhancedAnvilCommonConstruct implements ModConstruct {
	
	@Override
	public void construct() {
		BusRegister.registerMod(EnhancedAnvilBlocks::registerMod);
		BusRegister.registerMod(EnhancedAnvilMenuTypes::registerMod);
	}
	
}
