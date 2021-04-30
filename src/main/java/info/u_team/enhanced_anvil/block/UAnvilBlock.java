package info.u_team.enhanced_anvil.block;

import info.u_team.u_team_core.api.registry.IBlockItemProvider;
import net.minecraft.block.AnvilBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class UAnvilBlock extends AnvilBlock implements IBlockItemProvider {
	
	protected final BlockItem blockItem;
	
	public UAnvilBlock(Properties properties) {
		this(null, properties);
	}
	
	public UAnvilBlock(ItemGroup group, Properties properties) {
		this(group, properties, null);
	}
	
	public UAnvilBlock(Properties properties, Item.Properties blockItemProperties) {
		this(null, properties, blockItemProperties);
	}
	
	public UAnvilBlock(ItemGroup group, Properties properties, Item.Properties blockItemProperties) {
		super(properties);
		blockItem = createBlockItem(blockItemProperties == null ? new Item.Properties().group(group) : group == null ? blockItemProperties : blockItemProperties.group(group));
	}
	
	protected BlockItem createBlockItem(Item.Properties blockItemProperties) {
		return new BlockItem(this, blockItemProperties);
	}
	
	@Override
	public BlockItem getBlockItem() {
		return blockItem;
	}
}
