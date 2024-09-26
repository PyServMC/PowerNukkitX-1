package cn.nukkit.block;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;

@PowerNukkitXOnly
@Since("1.20.0-r2")
public class BlockFenceGateCherry extends BlockFenceGate {
    public BlockFenceGateCherry() {
        this(0);
    }

    public BlockFenceGateCherry(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return CHERRY_FENCE_GATE;
    }

    @Override
    public String getName() {
        return "Cherry Fence Gate";
    }

    @Override
    public int getBurnChance() {
        return 5;
    }

    @Override
    public int getBurnAbility() {
        return 20;
    }
}
