package info.u_team.enhanced_anvil.data.provider;

import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.CHIPPED_ENHANCED_ANVIL;
import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.DAMAGED_ENHANCED_ANVIL;
import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.ENHANCED_ANVIL;

import info.u_team.u_team_core.data.CommonItemModelsProvider;
import info.u_team.u_team_core.data.GenerationData;

public class EnhancedAnvilItemModelsProvider extends CommonItemModelsProvider {
	
	public EnhancedAnvilItemModelsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerModels() {
		simpleBlock(ENHANCED_ANVIL.get());
		simpleBlock(CHIPPED_ENHANCED_ANVIL.get());
		simpleBlock(DAMAGED_ENHANCED_ANVIL.get());
	}
	
}
