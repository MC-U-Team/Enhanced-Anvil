package info.u_team.enhanced_anvil.data.provider;

import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.*;

import java.util.function.BiConsumer;

import info.u_team.u_team_core.data.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;

public class EnhancedAnvilLootTablesProvider extends CommonLootTablesProvider {
	
	public EnhancedAnvilLootTablesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerLootTables(BiConsumer<ResourceLocation, LootTable> consumer) {
		registerBlock(ENHANCED_ANVIL, addBasicBlockLootTable(ENHANCED_ANVIL), consumer);
		registerBlock(CHIPPED_ENHANCED_ANVIL, addBasicBlockLootTable(CHIPPED_ENHANCED_ANVIL), consumer);
		registerBlock(DAMAGED_ENHANCED_ANVIL, addBasicBlockLootTable(DAMAGED_ENHANCED_ANVIL), consumer);
	}
	
}
