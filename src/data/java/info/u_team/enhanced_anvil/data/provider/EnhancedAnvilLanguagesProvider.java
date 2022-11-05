package info.u_team.enhanced_anvil.data.provider;

import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.CHIPPED_ENHANCED_ANVIL;
import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.DAMAGED_ENHANCED_ANVIL;
import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.ENHANCED_ANVIL;
import static info.u_team.enhanced_anvil.init.EnhancedAnvilCreativeTabs.TAB;

import info.u_team.u_team_core.data.CommonLanguagesProvider;
import info.u_team.u_team_core.data.GenerationData;

public class EnhancedAnvilLanguagesProvider extends CommonLanguagesProvider {
	
	public EnhancedAnvilLanguagesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	public void addTranslations() {
		final String container = "container.enhancedanvil.enhanved_anvil";
		
		// English
		add(TAB, "Enhanced Anvil");
		addBlock(ENHANCED_ANVIL, "Enhanced Anvil");
		addBlock(CHIPPED_ENHANCED_ANVIL, "Chipped Enhanced Anvil");
		addBlock(DAMAGED_ENHANCED_ANVIL, "Damaged Enhanced Anvil");
		add(container, "Enhanced Repair");
		
		// German
		add("de_de", TAB, "Verbesserter Amboss");
		addBlock("de_de", ENHANCED_ANVIL, "Verbesserter Amboss");
		addBlock("de_de", CHIPPED_ENHANCED_ANVIL, "Angeschlagener Verbesserter Amboss");
		addBlock("de_de", DAMAGED_ENHANCED_ANVIL, "Beschädigter Verbesserter Amboss");
		add("de_de", container, "Bessere Reparatur");
	}
	
}
