package info.u_team.enhanced_anvil.impl;

import info.u_team.enhanced_anvil.menu.EnhancedAnvilMenu;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;

public class ForgeEnhancedAnvilMenuLoaderImpl implements EnhancedAnvilMenu.LoaderImpl {
	
	@Override
	public float getBreakChance(Player player, ItemStack output, ItemStack left, ItemStack right) {
		return ForgeHooks.onAnvilRepair(player, output, left, right);
	}
	
	@Override
	public boolean anvilChange(AnvilMenu container, ItemStack left, ItemStack right, Container outputSlot, String name, int baseCost, Player player) {
		return ForgeHooks.onAnvilChange(container, left, right, outputSlot, name, baseCost, player);
	}
	
	@Override
	public boolean enchantable(ItemStack stack, ItemStack book) {
		return stack.isBookEnchantable(book);
	}
	
}
