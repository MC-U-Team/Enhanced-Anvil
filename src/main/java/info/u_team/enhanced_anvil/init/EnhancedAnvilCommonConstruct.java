package info.u_team.enhanced_anvil.init;

import info.u_team.enhanced_anvil.EnhancedAnvilMod;
import info.u_team.u_team_core.api.construct.Construct;
import info.u_team.u_team_core.api.construct.IModConstruct;
import info.u_team.u_team_core.util.registry.BusRegister;

@Construct(modid = EnhancedAnvilMod.MODID)
public class EnhancedAnvilCommonConstruct implements IModConstruct {
	
	@Override
	public void construct() {
		BusRegister.registerMod(EnhancedAnvilBlocks::register);
		BusRegister.registerMod(EnhancedAnvilContainerTypes::register);
	}
	
}
