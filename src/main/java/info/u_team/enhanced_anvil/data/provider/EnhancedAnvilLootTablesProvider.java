package info.u_team.enhanced_anvil.data.provider;

import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.*;

import java.io.IOException;
import java.util.function.BiConsumer;

import info.u_team.u_team_core.data.*;
import net.minecraft.data.DirectoryCache;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;

public class EnhancedAnvilLootTablesProvider extends CommonLootTablesProvider {
	
	public EnhancedAnvilLootTablesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	public void act(DirectoryCache cache) throws IOException {
		registerLootTables((id, lootTable) -> {
			try {
				write(cache, LootTableManager.toJson(lootTable), path.resolve(id.getNamespace()).resolve("loot_tables").resolve(id.getPath() + ".json"));
			} catch (IOException ex) {
				LOGGER.error(marker, "Could not write data.", ex);
			}
		});
	}
	
	@Override
	protected void registerLootTables(BiConsumer<ResourceLocation, LootTable> consumer) {
		registerBlock(ENHANCED_ANVIL, addBasicBlockLootTable(ENHANCED_ANVIL), consumer);
		registerBlock(CHIPPED_ENHANCED_ANVIL, addBasicBlockLootTable(CHIPPED_ENHANCED_ANVIL), consumer);
		registerBlock(DAMAGED_ENHANCED_ANVIL, addBasicBlockLootTable(DAMAGED_ENHANCED_ANVIL), consumer);
	}
	
}
