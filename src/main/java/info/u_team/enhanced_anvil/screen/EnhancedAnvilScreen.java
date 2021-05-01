package info.u_team.enhanced_anvil.screen;

import net.minecraft.client.gui.screen.inventory.AnvilScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.RepairContainer;
import net.minecraft.util.text.ITextComponent;

public class EnhancedAnvilScreen extends AnvilScreen {
	
	public EnhancedAnvilScreen(RepairContainer container, PlayerInventory plazerInventory, ITextComponent title) {
		super(container, plazerInventory, title);
	}
}
