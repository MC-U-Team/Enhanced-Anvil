package info.u_team.enhanced_anvil.container;

import info.u_team.enhanced_anvil.init.EnhancedAnvilContainerTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.RepairContainer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;

public class EnhancedAnvilContainer extends RepairContainer {
	
	// Client
	public EnhancedAnvilContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
		this(id, playerInventory, IWorldPosCallable.DUMMY);
	}
	
	// Server
	public EnhancedAnvilContainer(int id, PlayerInventory playerInventory, IWorldPosCallable pos) {
		super(id, playerInventory, pos);
		containerType = EnhancedAnvilContainerTypes.ENHANCED_ANVIL; // Force set this container type
	}
}
