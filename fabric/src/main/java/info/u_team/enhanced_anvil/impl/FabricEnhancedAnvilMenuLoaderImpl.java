package info.u_team.enhanced_anvil.impl;

import info.u_team.enhanced_anvil.menu.EnhancedAnvilMenu;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;

public class FabricEnhancedAnvilMenuLoaderImpl implements EnhancedAnvilMenu.LoaderImpl {
	
	@Override
	public float getBreakChance(Player player, ItemStack output, ItemStack left, ItemStack right) {
		return 0.12f;
	}
	
	@Override
	public boolean anvilChange(AnvilMenu container, ItemStack left, ItemStack right, Container outputSlot, String name, int baseCost, Player player) {
		return true;
	}
	
	@Override
	public boolean enchantable(ItemStack stack, ItemStack book) {
		return true;
	}
	
}
