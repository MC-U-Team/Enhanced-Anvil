package info.u_team.enhanced_anvil.init;

import info.u_team.enhanced_anvil.EnhancedAnvilReference;
import info.u_team.u_team_core.api.construct.Construct;
import info.u_team.u_team_core.api.construct.ModConstruct;

@Construct(modid = EnhancedAnvilReference.MODID)
public class EnhancedAnvilCommonConstruct implements ModConstruct {
	
	@Override
	public void construct() {
		EnhancedAnvilBlocks.register();
		EnhancedAnvilMenuTypes.register();
	}
	
}
