package cn.nukkit.block;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;

@PowerNukkitOnly
@Since("1.4.0.0-PN")
public class BlockNyliumWarped extends BlockNylium {

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockNyliumWarped() {
        // Does nothing
    }

    @Override
    public String getName() {
        return "Warped Nylium";
    }

    @Override
    public int getId() {
        return WARPED_NYLIUM;
    }

}
