package cn.nukkit.block;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.item.ItemTool;

@PowerNukkitOnly
public class BlockFletchingTable extends BlockSolid {

    @PowerNukkitOnly
    public BlockFletchingTable() {
    }

    @Override
    public int getId() {
        return FLETCHING_TABLE;
    }

    @Override
    public String getName() {
        return "Fletching Table";
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_AXE;
    }

    @Override
    public double getResistance() {
        return 12.5;
    }

    @Override
    public double getHardness() {
        return 2.5;
    }

    @Override
    public int getBurnChance() {
        return 5;
    }

    @Override
    public boolean canHarvestWithHand() {
        return true;
    }
}
