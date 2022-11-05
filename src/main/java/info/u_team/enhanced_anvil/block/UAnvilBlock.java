package info.u_team.enhanced_anvil.block;

import info.u_team.u_team_core.api.registry.IBlockItemProvider;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class UAnvilBlock extends AnvilBlock implements IBlockItemProvider {
	
	protected final BlockItem blockItem;
	
	public UAnvilBlock(Properties properties) {
		this(null, properties);
	}
	
	public UAnvilBlock(CreativeModeTab group, Properties properties) {
		this(group, properties, null);
	}
	
	public UAnvilBlock(Properties properties, Item.Properties blockItemProperties) {
		this(null, properties, blockItemProperties);
	}
	
	public UAnvilBlock(CreativeModeTab group, Properties properties, Item.Properties blockItemProperties) {
		super(properties);
		blockItem = createBlockItem(blockItemProperties == null ? new Item.Properties().tab(group) : group == null ? blockItemProperties : blockItemProperties.tab(group));
	}
	
	protected BlockItem createBlockItem(Item.Properties blockItemProperties) {
		return new BlockItem(this, blockItemProperties);
	}
	
	@Override
	public BlockItem getBlockItem() {
		return blockItem;
	}
}
