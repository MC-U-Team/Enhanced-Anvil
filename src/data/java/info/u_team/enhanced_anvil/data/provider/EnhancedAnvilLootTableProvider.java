package info.u_team.enhanced_anvil.data.provider;

import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.CHIPPED_ENHANCED_ANVIL;
import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.DAMAGED_ENHANCED_ANVIL;
import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.ENHANCED_ANVIL;

import java.util.function.BiConsumer;

import info.u_team.u_team_core.data.CommonLootTableProvider;
import info.u_team.u_team_core.data.GenerationData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

public class EnhancedAnvilLootTableProvider extends CommonLootTableProvider {
	
	public EnhancedAnvilLootTableProvider(GenerationData generationData) {
		super(generationData);
	}
	
	@Override
	public void register(BiConsumer<ResourceLocation, LootTable> consumer) {
		registerBlock(ENHANCED_ANVIL, addBasicBlockLootTable(ENHANCED_ANVIL.get()), consumer);
		registerBlock(CHIPPED_ENHANCED_ANVIL, addBasicBlockLootTable(CHIPPED_ENHANCED_ANVIL.get()), consumer);
		registerBlock(DAMAGED_ENHANCED_ANVIL, addBasicBlockLootTable(DAMAGED_ENHANCED_ANVIL.get()), consumer);
	}
	
}
