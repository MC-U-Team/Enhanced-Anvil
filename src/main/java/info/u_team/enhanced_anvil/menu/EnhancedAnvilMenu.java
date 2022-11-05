package info.u_team.enhanced_anvil.menu;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import info.u_team.enhanced_anvil.block.EnhancedAnvilBlock;
import info.u_team.enhanced_anvil.init.EnhancedAnvilMenuTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;

public class EnhancedAnvilMenu extends AnvilMenu {
	
	// Client
	public EnhancedAnvilMenu(int containerId, Inventory playerInventory) {
		this(containerId, playerInventory, ContainerLevelAccess.NULL);
	}
	
	// Server
	public EnhancedAnvilMenu(int containerId, Inventory playerInventory, ContainerLevelAccess levelAccess) {
		super(containerId, playerInventory, levelAccess);
		menuType = EnhancedAnvilMenuTypes.ENHANCED_ANVIL.get();
	}
	
	@Override
	protected void onTake(Player player, ItemStack stack) {
		if (!player.getAbilities().instabuild) {
			player.giveExperienceLevels(-cost.get());
		}
		
		final float breakChance = ForgeHooks.onAnvilRepair(player, stack, inputSlots.getItem(0), inputSlots.getItem(1));
		
		inputSlots.setItem(0, ItemStack.EMPTY);
		if (repairItemCountCost > 0) {
			final ItemStack itemstack = inputSlots.getItem(1);
			if (!itemstack.isEmpty() && itemstack.getCount() > repairItemCountCost) {
				itemstack.shrink(repairItemCountCost);
				inputSlots.setItem(1, itemstack);
			} else {
				inputSlots.setItem(1, ItemStack.EMPTY);
			}
		} else {
			inputSlots.setItem(1, ItemStack.EMPTY);
		}
		
		cost.set(0);
		access.execute((level, pos) -> {
			final BlockState blockState = level.getBlockState(pos);
			if (!player.getAbilities().instabuild && blockState.getBlock() instanceof final EnhancedAnvilBlock block && player.getRandom().nextFloat() < breakChance) {
				final BlockState damagedState = block.damageAnvil(blockState);
				if (damagedState == null) {
					level.removeBlock(pos, false);
					level.levelEvent(1029, pos, 0);
				} else {
					level.setBlock(pos, damagedState, 2);
					level.levelEvent(1030, pos, 0);
				}
			} else {
				level.levelEvent(1030, pos, 0);
			}
		});
	}
	
	@Override
	public void createResult() {
		final ItemStack itemstack = inputSlots.getItem(0);
		cost.set(1);
		int i = 0;
		int j = 0;
		int k = 0;
		if (itemstack.isEmpty()) {
			resultSlots.setItem(0, ItemStack.EMPTY);
			cost.set(0);
		} else {
			ItemStack itemstack1 = itemstack.copy();
			final ItemStack itemstack2 = inputSlots.getItem(1);
			final Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(itemstack1);
			j += itemstack.getBaseRepairCost() + (itemstack2.isEmpty() ? 0 : itemstack2.getBaseRepairCost());
			repairItemCountCost = 0;
			boolean flag = false;
			
			if (!ForgeHooks.onAnvilChange(this, itemstack, itemstack2, resultSlots, itemName, j, player))
				return;
			if (!itemstack2.isEmpty()) {
				flag = itemstack2.getItem() == Items.ENCHANTED_BOOK && !EnchantedBookItem.getEnchantments(itemstack2).isEmpty();
				if (itemstack1.isDamageableItem() && itemstack1.getItem().isValidRepairItem(itemstack, itemstack2)) {
					int l2 = Math.min(itemstack1.getDamageValue(), itemstack1.getMaxDamage() / 4);
					if (l2 <= 0) {
						resultSlots.setItem(0, ItemStack.EMPTY);
						cost.set(0);
						return;
					}
					
					int i3;
					for (i3 = 0; l2 > 0 && i3 < itemstack2.getCount(); ++i3) {
						final int j3 = itemstack1.getDamageValue() - l2;
						itemstack1.setDamageValue(j3);
						++i;
						l2 = Math.min(itemstack1.getDamageValue(), itemstack1.getMaxDamage() / 4);
					}
					
					repairItemCountCost = i3;
				} else {
					if (!flag && (!itemstack1.is(itemstack2.getItem()) || !itemstack1.isDamageableItem())) {
						resultSlots.setItem(0, ItemStack.EMPTY);
						cost.set(0);
						return;
					}
					
					if (itemstack1.isDamageableItem() && !flag) {
						final int l = itemstack.getMaxDamage() - itemstack.getDamageValue();
						final int i1 = itemstack2.getMaxDamage() - itemstack2.getDamageValue();
						final int j1 = i1 + itemstack1.getMaxDamage() * 12 / 100;
						final int k1 = l + j1;
						int l1 = itemstack1.getMaxDamage() - k1;
						if (l1 < 0) {
							l1 = 0;
						}
						
						if (l1 < itemstack1.getDamageValue()) {
							itemstack1.setDamageValue(l1);
							i += 2;
						}
					}
					
					final Map<Enchantment, Integer> map1 = EnchantmentHelper.getEnchantments(itemstack2);
					boolean flag2 = false;
					boolean flag3 = false;
					
					for (final Enchantment enchantment1 : map1.keySet()) {
						if (enchantment1 != null) {
							final int i2 = map.getOrDefault(enchantment1, 0);
							int j2 = map1.get(enchantment1);
							j2 = i2 == j2 ? j2 + 1 : Math.max(j2, i2);
							boolean flag1 = enchantment1.canEnchant(itemstack);
							if (player.getAbilities().instabuild || itemstack.is(Items.ENCHANTED_BOOK)) {
								flag1 = true;
							}
							
							for (final Enchantment enchantment : map.keySet()) {
								if (enchantment != enchantment1 && !enchantment1.isCompatibleWith(enchantment)) {
									flag1 = false;
									++i;
								}
							}
							
							if (!flag1) {
								flag3 = true;
							} else {
								flag2 = true;
								if (j2 > enchantment1.getMaxLevel()) {
									j2 = enchantment1.getMaxLevel();
								}
								
								map.put(enchantment1, j2);
								int k3 = 0;
								switch (enchantment1.getRarity()) {
								case COMMON:
									k3 = 1;
									break;
								case UNCOMMON:
									k3 = 2;
									break;
								case RARE:
									k3 = 4;
									break;
								case VERY_RARE:
									k3 = 8;
								}
								
								if (flag) {
									k3 = Math.max(1, k3 / 2);
								}
								
								i += k3 * j2;
								if (itemstack.getCount() > 1) {
									i = 40;
								}
							}
						}
					}
					
					if (flag3 && !flag2) {
						resultSlots.setItem(0, ItemStack.EMPTY);
						cost.set(0);
						return;
					}
				}
			}
			
			if (StringUtils.isBlank(itemName)) {
				if (itemstack.hasCustomHoverName()) {
					k = 1;
					i += k;
					itemstack1.resetHoverName();
				}
			} else if (!itemName.equals(itemstack.getHoverName().getString())) {
				k = 1;
				i += k;
				itemstack1.setHoverName(Component.literal(itemName));
			}
			if (flag && !itemstack1.isBookEnchantable(itemstack2))
				itemstack1 = ItemStack.EMPTY;
			
			cost.set(j + i);
			if (i <= 0) {
				itemstack1 = ItemStack.EMPTY;
			}
			
			if (k == i && k > 0 && cost.get() >= 40) {
				cost.set(39);
			}
			
			if (!itemstack1.isEmpty()) {
				int k2 = itemstack1.getBaseRepairCost();
				if (!itemstack2.isEmpty() && k2 < itemstack2.getBaseRepairCost()) {
					k2 = itemstack2.getBaseRepairCost();
				}
				
				if (k != i || k == 0) {
					k2 = calculateIncreasedRepairCost(k2);
				}
				
				itemstack1.setRepairCost(k2);
				EnchantmentHelper.setEnchantments(map, itemstack1);
			}
			
			resultSlots.setItem(0, itemstack1);
			broadcastChanges();
		}
	}
	
}
