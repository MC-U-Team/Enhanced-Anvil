package info.u_team.enhanced_anvil.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.AnvilScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.RepairContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.play.client.CRenameItemPacket;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class EnhancedAnvilScreen extends AnvilScreen {
	
	public EnhancedAnvilScreen(RepairContainer container, PlayerInventory plazerInventory, ITextComponent title) {
		super(container, plazerInventory, title);
	}
	
	@Override
	protected void initFields() {
		super.initFields();
		nameField.setResponder(this::rename);
	}
	
	private void rename(String name) {
		if (!name.isEmpty()) {
			String updatedName = name;
			final Slot slot = this.container.getSlot(0);
			
			if (slot != null && slot.getHasStack() && !slot.getStack().hasDisplayName() && name.equals(slot.getStack().getDisplayName().getString())) {
				updatedName = "";
			}
			
			updatedName.replace("&", "§");
			
			container.updateItemName(updatedName);
			minecraft.player.connection.sendPacket(new CRenameItemPacket(updatedName));
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
		RenderSystem.disableBlend();
		font.drawText(matrixStack, title, titleX, titleY, 0x404040);
		font.drawText(matrixStack, playerInventory.getDisplayName(), playerInventoryTitleX, playerInventoryTitleY, 0x404040);
		
		final int maximumCost = this.container.getMaximumCost();
		
		if (maximumCost > 0) {
			int textColor = 0x80FF20;
			
			final ITextComponent text;
			if (!container.getSlot(2).getHasStack()) {
				text = null;
			} else {
				text = new TranslationTextComponent("container.repair.cost", maximumCost);
				if (!container.getSlot(2).canTakeStack(playerInventory.player)) {
					textColor = 0xFF6060;
				}
			}
			
			if (text != null) {
				final int textX = xSize - 8 - font.getStringPropertyWidth(text) - 2;
				final int textY = 69;
				fill(matrixStack, textX - 2, 67, xSize - 8, 79, 0x4F000000);
				font.drawTextWithShadow(matrixStack, text, textX, textY, textColor);
			}
		}
	}
}
