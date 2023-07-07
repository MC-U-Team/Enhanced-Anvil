package info.u_team.enhanced_anvil.data.provider;

import info.u_team.enhanced_anvil.init.EnhancedAnvilCommonTags;
import info.u_team.u_team_core.data.CommonBlockTagsProvider;
import info.u_team.u_team_core.data.CommonItemTagsProvider;
import info.u_team.u_team_core.data.GenerationData;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

public class EnhancedAnvilItemTagsProvider extends CommonItemTagsProvider {
	
	public EnhancedAnvilItemTagsProvider(GenerationData generationData, CommonBlockTagsProvider blockTagsProvider) {
		super(generationData, blockTagsProvider);
	}
	
	@Override
	public void register(HolderLookup.Provider provider) {
		copy(BlockTags.ANVIL, ItemTags.ANVIL);
		
		tag(EnhancedAnvilCommonTags.IRON_INGOT).add(Items.IRON_INGOT).addOptionalTag(Tags.Items.INGOTS_IRON.location());
		tag(EnhancedAnvilCommonTags.IRON_BLOCK).add(Items.IRON_BLOCK).addOptionalTag(Tags.Items.STORAGE_BLOCKS_IRON.location());
	}
	
}
