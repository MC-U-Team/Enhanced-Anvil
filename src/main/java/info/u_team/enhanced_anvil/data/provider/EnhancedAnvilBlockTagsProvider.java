package info.u_team.enhanced_anvil.data.provider;

import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.*;

import info.u_team.u_team_core.data.CommonBlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;

public class EnhancedAnvilBlockTagsProvider extends CommonBlockTagsProvider {
	
	public EnhancedAnvilBlockTagsProvider(DataGenerator generator) {
		super("Block-Tags", generator);
	}
	
	@Override
	protected void registerTags() {
		getBuilder(BlockTags.ANVIL).add(ENHANCED_ANVIL, CHIPPED_ENHANCED_ANVIL, DAMAGED_ENHANCED_ANVIL);
	}
	
}
