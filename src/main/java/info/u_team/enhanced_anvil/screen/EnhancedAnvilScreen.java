package info.u_team.enhanced_anvil.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class EnhancedAnvilScreen extends AnvilScreen {
	
	public EnhancedAnvilScreen(AnvilMenu container, Inventory plazerInventory, Component title) {
		super(container, plazerInventory, title);
	}
	
	@Override
	protected void renderLabels(PoseStack matrixStack, int x, int y) {
		RenderSystem.disableBlend();
		font.draw(matrixStack, title, titleLabelX, titleLabelY, 0x404040);
		font.draw(matrixStack, inventory.getDisplayName(), inventoryLabelX, inventoryLabelY, 0x404040);
		
		final int maximumCost = this.menu.getCost();
		
		if (maximumCost > 0) {
			int textColor = 0x80FF20;
			
			final Component text;
			if (!menu.getSlot(2).hasItem()) {
				text = null;
			} else {
				text = new TranslatableComponent("container.repair.cost", maximumCost);
				if (!menu.getSlot(2).mayPickup(inventory.player)) {
					textColor = 0xFF6060;
				}
			}
			
			if (text != null) {
				final int textX = imageWidth - 8 - font.width(text) - 2;
				final int textY = 69;
				fill(matrixStack, textX - 2, 67, imageWidth - 8, 79, 0x4F000000);
				font.drawShadow(matrixStack, text, textX, textY, textColor);
			}
		}
	}
}
