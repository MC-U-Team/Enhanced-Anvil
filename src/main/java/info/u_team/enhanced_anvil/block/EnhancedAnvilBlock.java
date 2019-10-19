package info.u_team.enhanced_anvil.block;

import javax.annotation.Nullable;

import info.u_team.enhanced_anvil.init.EnhancedAnvilItemGroups;
import net.minecraft.block.*;
import net.minecraft.block.material.*;

public class EnhancedAnvilBlock extends UAnvilBlock {
	
	public EnhancedAnvilBlock(String name) {
		super(name, EnhancedAnvilItemGroups.GROUP, Properties.create(Material.ANVIL, MaterialColor.IRON).hardnessAndResistance(5.0F, 1200.0F).sound(SoundType.ANVIL));
	}
	
	@Nullable
	public BlockState damageAnvil(BlockState state) {
		return state;
	}
	
}
