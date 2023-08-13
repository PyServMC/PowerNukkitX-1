package cn.nukkit.block;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.blockproperty.BlockProperties;
import cn.nukkit.blockproperty.CommonBlockProperties;
import cn.nukkit.blockproperty.IntBlockProperty;
import cn.nukkit.entity.item.EntityFallingBlock;
import cn.nukkit.item.Item;
import cn.nukkit.nbt.tag.CompoundTag;

//todo complete
@PowerNukkitXOnly
@Since("1.20.10-r2")
public class BlockSuspiciousGravel extends BlockFallableMeta {
    public static final IntBlockProperty BRUSHED_PROGRESS = new IntBlockProperty("brushed_progress", false, 3);
    public static final BlockProperties PROPERTIES = new BlockProperties(CommonBlockProperties.HANGING, BRUSHED_PROGRESS);

    @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    public BlockSuspiciousGravel() {
    }

    public BlockSuspiciousGravel(int meta) {
        super(meta);
    }

    public int getId() {
        return SUSPICIOUS_GRAVEL;
    }

    public String getName() {
        return "Suspicious Gravel";
    }

    @Override
    public double getHardness() {
        return 0.25;
    }

    @Override
    public double getResistance() {
        return 1.25;
    }

    @PowerNukkitOnly
    @Override
    protected EntityFallingBlock createFallingEntity(CompoundTag customNbt) {
        customNbt.putBoolean("BreakOnGround", true);
        return super.createFallingEntity(customNbt);
    }

    @Override
    public Item[] getDrops(Item item) {
        return new Item[]{Item.AIR_ITEM};
    }
}