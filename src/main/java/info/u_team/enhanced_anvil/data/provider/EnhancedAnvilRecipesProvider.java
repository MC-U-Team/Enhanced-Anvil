package info.u_team.enhanced_anvil.data.provider;

import java.util.function.Consumer;

import info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks;
import info.u_team.u_team_core.data.CommonRecipesProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraftforge.common.Tags;

public class EnhancedAnvilRecipesProvider extends CommonRecipesProvider {
	
	public EnhancedAnvilRecipesProvider(DataGenerator generator) {
		super(generator);
	}
	
	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		ShapedRecipeBuilder.shapedRecipe(EnhancedAnvilBlocks.ENHANCED_ANVIL) //
				.patternLine("III") //
				.patternLine(" A ") //
				.patternLine("IBI") //
				.key('I', getIngredientOfTag(Tags.Items.INGOTS_IRON)) //
				.key('A', Blocks.ANVIL) //
				.key('B', getIngredientOfTag(Tags.Items.STORAGE_BLOCKS_IRON)) //
				.addCriterion("has_iron", hasItem(Tags.Items.INGOTS_IRON)) //
				.addCriterion("has_anvil", hasItem(Blocks.ANVIL)) //
				.build(consumer);
	}
}