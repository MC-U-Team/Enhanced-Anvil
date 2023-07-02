package info.u_team.enhanced_anvil.data.provider;

import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.CHIPPED_ENHANCED_ANVIL;
import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.DAMAGED_ENHANCED_ANVIL;
import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.ENHANCED_ANVIL;

import info.u_team.u_team_core.data.CommonBlockTagsProvider;
import info.u_team.u_team_core.data.GenerationData;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

public class EnhancedAnvilBlockTagsProvider extends CommonBlockTagsProvider {
	
	public EnhancedAnvilBlockTagsProvider(GenerationData generationData) {
		super(generationData);
	}
	
	@Override
	public void register(HolderLookup.Provider provider) {
		tag(BlockTags.ANVIL).add(ENHANCED_ANVIL.get(), CHIPPED_ENHANCED_ANVIL.get(), DAMAGED_ENHANCED_ANVIL.get());
	}
	
}
