package cn.nukkit.block;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;

@PowerNukkitXOnly
@Since("1.20.0-r2")
public class BlockSpruceHangingSign extends BlockHangingSign {
    public BlockSpruceHangingSign() {
    }

    public int getId() {
        return SPRUCE_HANGING_SIGN;
    }

    public String getName() {
        return "Spruce Hanging Sign";
    }
}