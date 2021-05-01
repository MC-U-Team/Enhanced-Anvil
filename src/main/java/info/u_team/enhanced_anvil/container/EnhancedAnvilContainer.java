package info.u_team.enhanced_anvil.container;

import info.u_team.enhanced_anvil.block.EnhancedAnvilBlock;
import info.u_team.enhanced_anvil.init.EnhancedAnvilContainerTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.RepairContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tags.BlockTags;
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
		containerType = EnhancedAnvilContainerTypes.ENHANCED_ANVIL.get();
	}
	
	@Override
	protected ItemStack func_230301_a_(PlayerEntity player, ItemStack output) {
		if (!player.abilities.isCreativeMode) {
			player.addExperienceLevel(-maximumCost.get());
		}
		
		final float breakChance = ForgeHooks.onAnvilRepair(player, output, field_234643_d_.getStackInSlot(0), field_234643_d_.getStackInSlot(1));
		
		field_234643_d_.setInventorySlotContents(0, ItemStack.EMPTY);
		if (materialCost > 0) {
			final ItemStack itemstack = this.field_234643_d_.getStackInSlot(1);
			if (!itemstack.isEmpty() && itemstack.getCount() > this.materialCost) {
				itemstack.shrink(this.materialCost);
				field_234643_d_.setInventorySlotContents(1, itemstack);
			} else {
				field_234643_d_.setInventorySlotContents(1, ItemStack.EMPTY);
			}
		} else {
			field_234643_d_.setInventorySlotContents(1, ItemStack.EMPTY);
		}
		
		maximumCost.set(0);
		field_234644_e_.consume((world, pos) -> {
			final BlockState oldState = world.getBlockState(pos);
			if (!player.abilities.isCreativeMode && oldState.isIn(BlockTags.ANVIL) && player.getRNG().nextFloat() < breakChance) {
				if (oldState.getBlock() instanceof EnhancedAnvilBlock) {
					final BlockState newState = (((EnhancedAnvilBlock) oldState.getBlock()).damageAnvil(oldState));
					if (newState == null) {
						world.removeBlock(pos, false);
						world.playEvent(1029, pos, 0);
					} else {
						world.setBlockState(pos, newState, 2);
						world.playEvent(1030, pos, 0);
					}
				}
			} else {
				world.playEvent(1030, pos, 0);
			}
			
		});
		return output;
	}
}
