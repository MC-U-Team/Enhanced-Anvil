package info.u_team.enhanced_anvil.data.provider;

import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.ENHANCED_ANVIL;

import java.util.function.Consumer;

import info.u_team.enhanced_anvil.init.EnhancedAnvilCommonTags;
import info.u_team.u_team_core.data.CommonRecipeProvider;
import info.u_team.u_team_core.data.GenerationData;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.block.Blocks;

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
				.define('I', EnhancedAnvilCommonTags.IRON_INGOT) //
				.define('A', Blocks.ANVIL) //
				.define('B', EnhancedAnvilCommonTags.IRON_BLOCK) //
				.unlockedBy("has_iron", has(EnhancedAnvilCommonTags.IRON_INGOT)) //
				.unlockedBy("has_anvil", has(Blocks.ANVIL)) //
				.save(consumer);
	}
}
