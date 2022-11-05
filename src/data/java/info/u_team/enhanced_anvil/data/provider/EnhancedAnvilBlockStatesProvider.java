package info.u_team.enhanced_anvil.data.provider;

import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.CHIPPED_ENHANCED_ANVIL;
import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.DAMAGED_ENHANCED_ANVIL;
import static info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks.ENHANCED_ANVIL;

import info.u_team.u_team_core.data.CommonBlockStatesProvider;
import info.u_team.u_team_core.data.GenerationData;
import net.minecraft.world.level.block.Block;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;

public class EnhancedAnvilBlockStatesProvider extends CommonBlockStatesProvider {
	
	public EnhancedAnvilBlockStatesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerStatesAndModels() {
		allAnvilBlock("anvil", ENHANCED_ANVIL.get(), CHIPPED_ENHANCED_ANVIL.get(), DAMAGED_ENHANCED_ANVIL.get());
	}
	
	private void allAnvilBlock(String name, Block base, Block chipped, Block damaged) {
		baseAnvilBlock(base, getBlockPath(name), getBlockPath(name + "_top"));
		variantAnvilBlock(chipped, getBlockPath(getPath(base)), getBlockPath("chipped_" + name + "_top"));
		variantAnvilBlock(damaged, getBlockPath(getPath(base)), getBlockPath("damaged_" + name + "_top"));
	}
	
	private void variantAnvilBlock(Block block, ResourceLocation base, ResourceLocation top) {
		horizontalBlock(block, variantAnvil(getPath(block), base, top), 0);
	}
	
	private void baseAnvilBlock(Block block, ResourceLocation body, ResourceLocation top) {
		horizontalBlock(block, baseAnvil(getPath(block), body, top), 0);
	}
	
	private ModelFile variantAnvil(String name, ResourceLocation base, ResourceLocation top) {
		return models().getBuilder(name) //
				.parent(new UncheckedModelFile(base)) //
				.texture("top", top);
	}
	
	private ModelFile baseAnvil(String name, ResourceLocation body, ResourceLocation top) {
		return models().withExistingParent(name, "block/anvil") //
				.texture("particle", body) //
				.texture("body", body) //
				.texture("top", top);
	}
	
	private ResourceLocation getBlockPath(String name) {
		return modLoc("block/" + name);
	}
	
}
