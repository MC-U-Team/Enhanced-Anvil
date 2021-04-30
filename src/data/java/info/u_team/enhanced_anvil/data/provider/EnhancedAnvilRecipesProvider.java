package info.u_team.enhanced_anvil.data.provider;

import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.ENHANCED_ANVIL;

import java.util.function.Consumer;

import info.u_team.u_team_core.data.CommonRecipesProvider;
import info.u_team.u_team_core.data.GenerationData;
import net.minecraft.block.Blocks;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraftforge.common.Tags;

public class EnhancedAnvilRecipesProvider extends CommonRecipesProvider {
	
	public EnhancedAnvilRecipesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		ShapedRecipeBuilder.shapedRecipe(ENHANCED_ANVIL.get()) //
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
