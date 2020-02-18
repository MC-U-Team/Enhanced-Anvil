package info.u_team.enhanced_anvil.data.provider;

import info.u_team.u_team_core.data.*;
import net.minecraft.tags.*;

public class EnhancedAnvilItemTagsProvider extends CommonItemTagsProvider {
	
	public EnhancedAnvilItemTagsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerTags() {
		copy(BlockTags.ANVIL, ItemTags.ANVIL);
	}
	
}
