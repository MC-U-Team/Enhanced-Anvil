package info.u_team.enhanced_anvil.container;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import info.u_team.enhanced_anvil.block.EnhancedAnvilBlock;
import info.u_team.enhanced_anvil.init.EnhancedAnvilContainerTypes;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.RepairContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.ForgeHooks;

public class EnhancedAnvilContainer extends RepairContainer {
	
	// Client
	public EnhancedAnvilContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
		this(id, playerInventory, IWorldPosCallable.DUMMY);
	}
	
	// Server
	public EnhancedAnvilContainer(int id, PlayerInventory playerInventory, IWorldPosCallable posCallable) {
		super(id, playerInventory, posCallable);
		containerType = EnhancedAnvilContainerTypes.ENHANCED_ANVIL.get(); // Force set this container type
		final Slot replaceOutputSlot = new Slot(outputSlot, 2, 134, 47) { // Replace output slot with our slot
			
			@Override
			public boolean isItemValid(ItemStack stack) {
				return false;
			}
			
			@Override
			public boolean canTakeStack(PlayerEntity player) {
				return (player.abilities.isCreativeMode || player.experienceLevel >= maximumCost.get()) && maximumCost.get() > 0 && getHasStack();
			}
			
			@Override
			public ItemStack onTake(PlayerEntity player, ItemStack stack) {
				if (!player.abilities.isCreativeMode) {
					player.addExperienceLevel(-maximumCost.get());
				}
				
				final float breakChance = ForgeHooks.onAnvilRepair(player, stack, inputSlots.getStackInSlot(0), inputSlots.getStackInSlot(1));
				
				inputSlots.setInventorySlotContents(0, ItemStack.EMPTY);
				if (materialCost > 0) {
					final ItemStack materialStack = inputSlots.getStackInSlot(1);
					if (!materialStack.isEmpty() && materialStack.getCount() > materialCost) {
						materialStack.shrink(materialCost);
						inputSlots.setInventorySlotContents(1, materialStack);
					} else {
						inputSlots.setInventorySlotContents(1, ItemStack.EMPTY);
					}
				} else {
					inputSlots.setInventorySlotContents(1, ItemStack.EMPTY);
				}
				
				maximumCost.set(0);
				posCallable.consume((world, pos) -> {
					final BlockState state = world.getBlockState(pos);
					if (!player.abilities.isCreativeMode && state.getBlock() instanceof EnhancedAnvilBlock && player.getRNG().nextFloat() < breakChance) {
						final BlockState newState = ((EnhancedAnvilBlock) state.getBlock()).damageAnvil(state);
						if (newState == null) {
							world.removeBlock(pos, false);
							world.playEvent(1029, pos, 0);
						} else {
							world.setBlockState(pos, newState, 2);
							world.playEvent(1030, pos, 0);
						}
					} else {
						world.playEvent(1030, pos, 0);
					}
				});
				return stack;
			}
		};
		replaceOutputSlot.slotNumber = 2;
		inventorySlots.set(2, replaceOutputSlot);
	}
	
	@Override
	public void updateRepairOutput() {
		final ItemStack itemstack = inputSlots.getStackInSlot(0);
		maximumCost.set(1);
		int i = 0;
		int j = 0;
		int k = 0;
		if (itemstack.isEmpty()) {
			outputSlot.setInventorySlotContents(0, ItemStack.EMPTY);
			maximumCost.set(0);
		} else {
			ItemStack itemstack1 = itemstack.copy();
			final ItemStack itemstack2 = inputSlots.getStackInSlot(1);
			final Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(itemstack1);
			j = j + itemstack.getRepairCost() + (itemstack2.isEmpty() ? 0 : itemstack2.getRepairCost());
			materialCost = 0;
			boolean flag = false;
			
			if (!itemstack2.isEmpty()) {
				if (!ForgeHooks.onAnvilChange(this, itemstack, itemstack2, outputSlot, repairedItemName, j))
					return;
				flag = itemstack2.getItem() == Items.ENCHANTED_BOOK && !EnchantedBookItem.getEnchantments(itemstack2).isEmpty();
				if (itemstack1.isDamageable() && itemstack1.getItem().getIsRepairable(itemstack, itemstack2)) {
					int l2 = Math.min(itemstack1.getDamage(), itemstack1.getMaxDamage() / 4);
					if (l2 <= 0) {
						outputSlot.setInventorySlotContents(0, ItemStack.EMPTY);
						maximumCost.set(0);
						return;
					}
					
					int i3;
					for (i3 = 0; l2 > 0 && i3 < itemstack2.getCount(); ++i3) {
						final int j3 = itemstack1.getDamage() - l2;
						itemstack1.setDamage(j3);
						++i;
						l2 = Math.min(itemstack1.getDamage(), itemstack1.getMaxDamage() / 4);
					}
					
					materialCost = i3;
				} else {
					if (!flag && (itemstack1.getItem() != itemstack2.getItem() || !itemstack1.isDamageable())) {
						outputSlot.setInventorySlotContents(0, ItemStack.EMPTY);
						maximumCost.set(0);
						return;
					}
					
					if (itemstack1.isDamageable() && !flag) {
						final int l = itemstack.getMaxDamage() - itemstack.getDamage();
						final int i1 = itemstack2.getMaxDamage() - itemstack2.getDamage();
						final int j1 = i1 + itemstack1.getMaxDamage() * 12 / 100;
						final int k1 = l + j1;
						int l1 = itemstack1.getMaxDamage() - k1;
						if (l1 < 0) {
							l1 = 0;
						}
						
						if (l1 < itemstack1.getDamage()) {
							itemstack1.setDamage(l1);
							i += 2;
						}
					}
					
					final Map<Enchantment, Integer> map1 = EnchantmentHelper.getEnchantments(itemstack2);
					boolean flag2 = false;
					boolean flag3 = false;
					
					for (final Enchantment enchantment1 : map1.keySet()) {
						if (enchantment1 != null) {
							final int i2 = map.containsKey(enchantment1) ? map.get(enchantment1) : 0;
							int j2 = map1.get(enchantment1);
							j2 = i2 == j2 ? j2 + 1 : Math.max(j2, i2);
							boolean flag1 = enchantment1.canApply(itemstack);
							if (player.abilities.isCreativeMode || itemstack.getItem() == Items.ENCHANTED_BOOK) {
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
						outputSlot.setInventorySlotContents(0, ItemStack.EMPTY);
						maximumCost.set(0);
						return;
					}
				}
			}
			if (StringUtils.isBlank(repairedItemName)) {
				if (itemstack.hasDisplayName()) {
					k = 1;
					i += k;
					itemstack1.clearCustomName();
				}
			} else if (!repairedItemName.equals(itemstack.getDisplayName().getString())) {
				k = 1;
				i += k;
				itemstack1.setDisplayName(new StringTextComponent(repairedItemName));
			}
			if (flag && !itemstack1.isBookEnchantable(itemstack2))
				itemstack1 = ItemStack.EMPTY;
			
			maximumCost.set(j + i + 1);
			if (i <= 0) {
				itemstack1 = ItemStack.EMPTY;
			}
			
			if (k == i && k > 0 && maximumCost.get() >= 40) {
				maximumCost.set(39);
			}
			
			if (!itemstack1.isEmpty()) {
				int k2 = itemstack1.getRepairCost();
				if (!itemstack2.isEmpty() && k2 < itemstack2.getRepairCost()) {
					k2 = itemstack2.getRepairCost();
				}
				
				if (k != i || k == 0) {
					k2 = getNewRepairCost(k2);
				}
				
				itemstack1.setRepairCost(k2);
				EnchantmentHelper.setEnchantments(map, itemstack1);
			}
			outputSlot.setInventorySlotContents(0, itemstack1);
			detectAndSendChanges();
		}
	}
}
