package info.u_team.enhanced_anvil.block;

import info.u_team.u_team_core.api.registry.IUBlockRegistryType;
import net.minecraft.block.AnvilBlock;
import net.minecraft.item.*;

public class UAnvilBlock extends AnvilBlock implements IUBlockRegistryType {
	
	protected final String name;
	
	protected final BlockItem blockItem;
	
	public UAnvilBlock(String name, Properties properties) {
		this(name, null, properties);
	}
	
	public UAnvilBlock(String name, ItemGroup group, Properties properties) {
		this(name, group, properties, null);
	}
	
	public UAnvilBlock(String name, Properties properties, Item.Properties blockItemProperties) {
		this(name, null, properties, blockItemProperties);
	}
	
	public UAnvilBlock(String name, ItemGroup group, Properties properties, Item.Properties blockItemProperties) {
		super(properties);
		this.name = name;
		blockItem = createBlockItem(blockItemProperties == null ? new Item.Properties().group(group) : group == null ? blockItemProperties : blockItemProperties.group(group));
	}
	
	protected BlockItem createBlockItem(Item.Properties blockItemProperties) {
		return new BlockItem(this, blockItemProperties);
	}
	
	@Override
	public String getEntryName() {
		return name;
	}
	
	@Override
	public BlockItem getBlockItem() {
		return blockItem;
	}
}
