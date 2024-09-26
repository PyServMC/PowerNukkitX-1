package cn.nukkit.item;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.BlockHangingSign;

@PowerNukkitXOnly
@Since("1.20.0-r2")
public class ItemBlockHangingSign extends ItemBlock {
    public ItemBlockHangingSign(BlockHangingSign block) {
        super(block);
    }

    public ItemBlockHangingSign(BlockHangingSign block, Integer meta) {
        super(block, meta, 1);
    }

    public ItemBlockHangingSign(BlockHangingSign block, Integer meta, int count) {
        super(block, meta, count);
    }

    @Override
    public int getMaxStackSize() {
        return 16;
    }
}
