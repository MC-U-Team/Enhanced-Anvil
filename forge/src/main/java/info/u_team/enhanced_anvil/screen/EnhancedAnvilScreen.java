package info.u_team.enhanced_anvil.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AnvilMenu;

public class EnhancedAnvilScreen extends AnvilScreen {
	
	public EnhancedAnvilScreen(AnvilMenu menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title);
	}
	
	@Override
	protected void renderLabels(PoseStack poseStack, int x, int y) {
		RenderSystem.disableBlend();
		
		font.draw(poseStack, title, titleLabelX, titleLabelY, 0x404040);
		font.draw(poseStack, playerInventoryTitle, inventoryLabelX, inventoryLabelY, 0x404040);
		
		final int cost = menu.getCost();
		if (cost > 0) {
			int color = 0x80FF20;
			final Component text;
			if (!menu.getSlot(2).hasItem()) {
				text = null;
			} else {
				text = Component.translatable("container.repair.cost", cost);
				if (!menu.getSlot(2).mayPickup(player)) {
					color = 0xFF6060;
				}
			}
			
			if (text != null) {
				final int k = imageWidth - 8 - font.width(text) - 2;
				fill(poseStack, k - 2, 67, imageWidth - 8, 79, 0x4F000000);
				font.drawShadow(poseStack, text, k, 69, color);
			}
		}
		
	}
}
