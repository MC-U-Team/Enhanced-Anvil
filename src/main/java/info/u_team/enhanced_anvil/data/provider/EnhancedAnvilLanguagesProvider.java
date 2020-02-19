package info.u_team.enhanced_anvil.data.provider;

import static info.u_team.enhanced_anvil.init.EnhancedAnvilItemGroups.GROUP;
import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.*;

import info.u_team.u_team_core.data.*;

public class EnhancedAnvilLanguagesProvider extends CommonLanguagesProvider {
	
	public EnhancedAnvilLanguagesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	public void addTranslations() {
		final String container = "container.enhancedanvil.enhanved_anvil";
		
		// English
		add(GROUP, "Enhanced Anvil");
		add(ENHANCED_ANVIL, "Enhanced Anvil");
		add(CHIPPED_ENHANCED_ANVIL, "Chipped Enhanced Anvil");
		add(DAMAGED_ENHANCED_ANVIL, "Damaged Enhanced Anvil");
		add(container, "Enhanced Repair");
	}
	
}
