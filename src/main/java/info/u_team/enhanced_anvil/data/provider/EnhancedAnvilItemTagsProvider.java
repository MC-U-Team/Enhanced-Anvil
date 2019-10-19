package info.u_team.enhanced_anvil.data.provider;

import info.u_team.u_team_core.data.CommonItemTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.*;

public class EnhancedAnvilItemTagsProvider extends CommonItemTagsProvider {
	
	public EnhancedAnvilItemTagsProvider(DataGenerator generator) {
		super("Item-Tags", generator);
	}
	
	@Override
	protected void registerTags() {
		copy(BlockTags.ANVIL, ItemTags.ANVIL);
	}
	
}
