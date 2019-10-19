package info.u_team.enhanced_anvil.screen;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.screen.inventory.AnvilScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.RepairContainer;
import net.minecraft.util.text.ITextComponent;

public class EnhancedAnvilScreen extends AnvilScreen {
	
	public EnhancedAnvilScreen(RepairContainer container, PlayerInventory plazerInventory, ITextComponent title) {
		super(container, plazerInventory, title);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		GlStateManager.disableLighting();
		GlStateManager.disableBlend();
		font.drawString(title.getFormattedText(), 60, 6, 0x404040);
		final int cost = container.func_216976_f();
		if (cost > 0 && container.getSlot(2).getHasStack()) {
			final String costString = I18n.format("container.repair.cost", cost);
			int posX = xSize - 8 - font.getStringWidth(costString) - 2;
			fill(posX - 2, 67, xSize - 8, 79, 1325400064);
			font.drawStringWithShadow(costString, posX, 69, container.getSlot(2).canTakeStack(playerInventory.player) ? 0x80FF20 : 0xFF6060);
		}
		GlStateManager.enableLighting();
	}
}
