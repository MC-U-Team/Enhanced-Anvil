package info.u_team.enhanced_anvil.block;

import java.util.function.Supplier;

import com.google.common.base.Suppliers;

import info.u_team.u_team_core.api.block.BlockItemProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.AnvilBlock;

public class UAnvilBlock extends AnvilBlock implements BlockItemProvider {
	
	protected final Supplier<BlockItem> blockItem;
	
	public UAnvilBlock(Properties properties) {
		this(properties, null);
	}
	
	public UAnvilBlock(Properties properties, Item.Properties blockItemProperties) {
		super(properties);
		blockItem = Suppliers.memoize(() -> createBlockItem(blockItemProperties == null ? new Item.Properties() : blockItemProperties));
	}
	
	protected BlockItem createBlockItem(Item.Properties blockItemProperties) {
		return new BlockItem(this, blockItemProperties);
	}
	
	@Override
	public BlockItem blockItem() {
		return blockItem.get();
	}
}
