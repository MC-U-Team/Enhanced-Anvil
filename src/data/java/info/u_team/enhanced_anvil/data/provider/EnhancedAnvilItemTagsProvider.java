package info.u_team.enhanced_anvil.data.provider;

import info.u_team.u_team_core.data.CommonItemTagsProvider;
import info.u_team.u_team_core.data.GenerationData;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;

public class EnhancedAnvilItemTagsProvider extends CommonItemTagsProvider {
	
	public EnhancedAnvilItemTagsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerTags() {
		copy(BlockTags.ANVIL, ItemTags.ANVIL);
	}
	
}
