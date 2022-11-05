package info.u_team.enhanced_anvil.integration.jei;

import info.u_team.enhanced_anvil.EnhancedAnvilMod;
import info.u_team.enhanced_anvil.init.EnhancedAnvilBlocks;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class EnhancedAnvilJeiPlugin implements IModPlugin {
	
	private final ResourceLocation id = new ResourceLocation(EnhancedAnvilMod.MODID, "jei");
	
	@Override
	public ResourceLocation getPluginUid() {
		return id;
	}
	
	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(EnhancedAnvilBlocks.ENHANCED_ANVIL.get()), RecipeTypes.ANVIL);
	}
	
}
