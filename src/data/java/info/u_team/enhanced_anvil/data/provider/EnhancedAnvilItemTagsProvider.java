package info.u_team.enhanced_anvil.data.provider;

import info.u_team.u_team_core.data.CommonBlockTagsProvider;
import info.u_team.u_team_core.data.CommonItemTagsProvider;
import info.u_team.u_team_core.data.GenerationData;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;

public class EnhancedAnvilItemTagsProvider extends CommonItemTagsProvider {
	
	public EnhancedAnvilItemTagsProvider(GenerationData generationData, CommonBlockTagsProvider blockTagsProvider) {
		super(generationData, blockTagsProvider);
	}
	
	@Override
	public void register() {
		copy(BlockTags.ANVIL, ItemTags.ANVIL);
	}
	
}
