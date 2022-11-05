package info.u_team.enhanced_anvil.data.provider;

import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.ENHANCED_ANVIL;

import java.util.function.Consumer;

import info.u_team.u_team_core.data.CommonRecipesProvider;
import info.u_team.u_team_core.data.GenerationData;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraftforge.common.Tags;

public class EnhancedAnvilRecipesProvider extends CommonRecipesProvider {
	
	public EnhancedAnvilRecipesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerRecipes(Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(ENHANCED_ANVIL.get()) //
				.pattern("III") //
				.pattern(" A ") //
				.pattern("IBI") //
				.define('I', getIngredientOfTag(Tags.Items.INGOTS_IRON)) //
				.define('A', Blocks.ANVIL) //
				.define('B', getIngredientOfTag(Tags.Items.STORAGE_BLOCKS_IRON)) //
				.unlockedBy("has_iron", hasItem(Tags.Items.INGOTS_IRON)) //
				.unlockedBy("has_anvil", hasItem(Blocks.ANVIL)) //
				.save(consumer);
	}
}
