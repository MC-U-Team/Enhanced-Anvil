package info.u_team.enhanced_anvil.data.provider;

import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.*;

import info.u_team.u_team_core.data.*;

public class EnhancedAnvilItemModelsProvider extends CommonItemModelsProvider {
	
	public EnhancedAnvilItemModelsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerModels() {
		simpleBlock(ENHANCED_ANVIL);
		simpleBlock(CHIPPED_ENHANCED_ANVIL);
		simpleBlock(DAMAGED_ENHANCED_ANVIL);
	}
	
}
