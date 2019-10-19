package info.u_team.enhanced_anvil.container;

import info.u_team.enhanced_anvil.block.EnhancedAnvilBlock;
import info.u_team.enhanced_anvil.init.EnhancedAnvilContainerTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.common.ForgeHooks;

public class EnhancedAnvilContainer extends RepairContainer {
	
	// Client
	public EnhancedAnvilContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
		this(id, playerInventory, IWorldPosCallable.DUMMY);
	}
	
	// Server
	public EnhancedAnvilContainer(int id, PlayerInventory playerInventory, IWorldPosCallable posCallable) {
		super(id, playerInventory, posCallable);
		containerType = EnhancedAnvilContainerTypes.ENHANCED_ANVIL; // Force set this container type
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
						BlockState newState = ((EnhancedAnvilBlock) state.getBlock()).damageAnvil(state);
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
}
