package info.u_team.enhanced_anvil.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.*;

public class EnhancedAnvilBlock extends UAnvilBlock {
	
	public EnhancedAnvilBlock(String name) {
		super(name, Properties.create(Material.ANVIL, MaterialColor.IRON).hardnessAndResistance(5.0F, 1200.0F).sound(SoundType.ANVIL));
	}
	
}
