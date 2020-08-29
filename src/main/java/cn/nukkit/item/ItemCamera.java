package cn.nukkit.item;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.entity.CreatureSpawnEvent;
import cn.nukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import cn.nukkit.item.ItemID;
import cn.nukkit.level.Level;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.math.BlockFace;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;

/**
 * @author good777LUCKY
 */
public class ItemCamera extends Item {

    public ItemCamera() {
        this(0, 1);
    }
    
    public ItemCamera(Integer meta) {
        this(meta, 1);
    }
    
    public ItemCamera(Integer meta, int count) {
        super(ItemID.CAMERA, meta, count, "Camera");
    }
    
    @Override
    public boolean canBeActivated() {
        return true;
    }
    
    @Override
    public boolean onActivate(Level level, Player player, Block block, Block target, BlockFace face, double fx, double fy, double fz) {
        FullChunk chunk = level.getChunk((int) block.getX() >> 4, (int) block.getZ() >> 4);
        
        if (chunk == null) {
            return false;
        }
        
        CompoundTag nbt = new CompoundTag()
                .putList(new ListTag<DoubleTag>("Pos")
                        .add(new DoubleTag("", block.getX() + 0.5))
                        .add(new DoubleTag("", target.getBoundingBox() == null ? block.getY() : target.getBoundingBox().getMaxY() + 0.0001f))
                        .add(new DoubleTag("", block.getZ() + 0.5)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", (float)player.getYaw() + 180f))
                        .add(new FloatTag("", 0)));
        
        if (this.hasCustomName()) {
            nbt.putString("CustomName", this.getCustomName());
        }
        
        CreatureSpawnEvent ev = new CreatureSpawnEvent(62, block, nbt, SpawnReason.CAMERA);
        level.getServer().getPluginManager().callEvent(ev);
        
        if (ev.isCancelled()) {
            return false;
        }
        
        Entity entity = Entity.createEntity("Camera", chunk, nbt);
        
        if (entity != null) {
            if (!player.isCreative()) {
                player.getInventory().decreaseCount(player.getInventory().getHeldItemIndex());
            }
            entity.spawnToAll();
            return true;
        }
        
        return false;
    }
}
