package info.u_team.enhanced_anvil.data.provider;

import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.ENHANCED_ANVIL;

import java.util.function.Consumer;

import info.u_team.u_team_core.data.CommonRecipeProvider;
import info.u_team.u_team_core.data.GenerationData;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

public class EnhancedAnvilRecipeProvider extends CommonRecipeProvider {
	
	public EnhancedAnvilRecipeProvider(GenerationData generationData) {
		super(generationData);
	}
	
	@Override
	public void register(Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ENHANCED_ANVIL.get()) //
				.pattern("III") //
				.pattern(" A ") //
				.pattern("IBI") //
				.define('I', getIngredientOfTag(Tags.Items.INGOTS_IRON)) //
				.define('A', Blocks.ANVIL) //
				.define('B', getIngredientOfTag(Tags.Items.STORAGE_BLOCKS_IRON)) //
				.unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON)) //
				.unlockedBy("has_anvil", has(Blocks.ANVIL)) //
				.save(consumer);
	}
}
