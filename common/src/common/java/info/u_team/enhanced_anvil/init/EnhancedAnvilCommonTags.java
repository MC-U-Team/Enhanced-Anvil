package info.u_team.enhanced_anvil.init;

import info.u_team.enhanced_anvil.EnhancedAnvilReference;
import info.u_team.u_team_core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class EnhancedAnvilCommonTags {
	
	public static final TagKey<Item> IRON_INGOT = TagUtil.createItemTag(EnhancedAnvilReference.MODID, "ingot/iron");
	
	public static final TagKey<Item> IRON_BLOCK = TagUtil.createItemTag(EnhancedAnvilReference.MODID, "block/iron");
	
}
