package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.api.PowerNukkitDifference;
import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.blockproperty.BlockProperties;
import cn.nukkit.blockproperty.CommonBlockProperties;
import cn.nukkit.event.block.SignColorChangeEvent;
import cn.nukkit.event.block.SignGlowEvent;
import cn.nukkit.event.block.SignWaxedEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemGlowInkSac;
import cn.nukkit.item.ItemHoneycomb;
import cn.nukkit.item.ItemTool;
import cn.nukkit.level.particle.WaxOnParticle;
import cn.nukkit.math.BlockFace;
import cn.nukkit.math.CompassRoseDirection;
import cn.nukkit.math.Vector3;
import cn.nukkit.network.protocol.LevelEventPacket;
import cn.nukkit.network.protocol.LevelSoundEventPacket;
import cn.nukkit.utils.BlockColor;
import cn.nukkit.utils.DyeColor;
import cn.nukkit.utils.Faceable;
import org.jetbrains.annotations.NotNull;

@PowerNukkitXOnly
@Since("1.20.0-r2")
public abstract class BlockSignBase extends BlockTransparentMeta implements Faceable {
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public static final BlockProperties PROPERTIES = new BlockProperties(CommonBlockProperties.GROUND_SIGN_DIRECTION);

    public BlockSignBase() {
        this(0);
    }

    public BlockSignBase(int meta) {
        super(meta);
    }

    @Override
    public double getHardness() {
        return 1;
    }

    @Override
    public double getResistance() {
        return 5;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Since("1.3.0.0-PN")
    @PowerNukkitOnly
    @Override
    public boolean isSolid(BlockFace side) {
        return false;
    }

    @PowerNukkitOnly
    @Override
    public int getWaterloggingLevel() {
        return 1;
    }

    @Since("1.20.0-r2")
    @Override
    public void onPlayerRightClick(@NotNull Player player, Item item, BlockFace face, Vector3 clickPoint) {
        var blockEntity = this.getLevel().getBlockEntity(this);
        if (!(blockEntity instanceof BlockEntitySign sign)) {
            return;
        }
        // If a sign is waxed, it cannot be modified.
        if (sign.isWaxed() || (player.isSneaking() && item.getId() != 0)) {
            level.addLevelSoundEvent(this.add(0.5, 0.5, 0.5), LevelSoundEventPacket.SOUND_WAXED_SIGN_INTERACT_FAIL);
            return;
        }
        boolean front = switch (getSignDirection()) {
            case EAST -> face == BlockFace.EAST;
            case SOUTH -> face == BlockFace.SOUTH;
            case WEST -> face == BlockFace.WEST;
            case NORTH -> face == BlockFace.NORTH;
            case NORTH_EAST, NORTH_NORTH_EAST, EAST_NORTH_EAST -> face == BlockFace.EAST || face == BlockFace.NORTH;
            case NORTH_WEST, NORTH_NORTH_WEST, WEST_NORTH_WEST -> face == BlockFace.WEST || face == BlockFace.NORTH;
            case SOUTH_EAST, SOUTH_SOUTH_EAST, EAST_SOUTH_EAST -> face == BlockFace.EAST || face == BlockFace.SOUTH;
            case SOUTH_WEST, SOUTH_SOUTH_WEST, WEST_SOUTH_WEST -> face == BlockFace.WEST || face == BlockFace.SOUTH;
        };
        if (item.getId() == Item.DYE) {
            BlockColor color = DyeColor.getByDyeData(item.getDamage()).getSignColor();
            if (color.equals(sign.getColor(front)) || sign.isEmpty(front)) {
                player.openSignEditor(this, front);
                return;
            }
            SignColorChangeEvent event = new SignColorChangeEvent(this, player, color);
            this.level.getServer().getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                sign.spawnTo(player);
                return;
            }
            sign.setColor(front, color);
            sign.spawnToAll();
            this.level.addLevelEvent(this, LevelEventPacket.EVENT_SOUND_DYE_USED);
            if ((player.getGamemode() & 0x01) == 0) {
                item.count--;
            }
            return;
        } else if (item instanceof ItemGlowInkSac) {
            if (sign.isGlowing(front) || sign.isEmpty(front)) {
                player.openSignEditor(this, front);
                return;
            }
            SignGlowEvent event = new SignGlowEvent(this, player, true);
            this.level.getServer().getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                sign.spawnTo(player);
                return;
            }
            sign.setGlowing(front, true);
            sign.spawnToAll();
            this.level.addLevelEvent(this, LevelEventPacket.EVENT_SOUND_INK_SACE_USED);
            if ((player.getGamemode() & 0x01) == 0) {
                item.count--;
            }
            return;
        } else if (item instanceof ItemHoneycomb) {
            SignWaxedEvent event = new SignWaxedEvent(this, player, true);
            this.level.getServer().getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                sign.spawnTo(player);
                return;
            }
            sign.setWaxed(true);
            sign.spawnToAll();
            this.getLevel().addParticle(new WaxOnParticle(this));
            if ((player.getGamemode() & 0x01) == 0) {
                item.count--;
            }
            return;
        }
        player.openSignEditor(this, front);
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_AXE;
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public CompassRoseDirection getSignDirection() {
        return getPropertyValue(CommonBlockProperties.GROUND_SIGN_DIRECTION);
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public void setSignDirection(CompassRoseDirection direction) {
        setPropertyValue(CommonBlockProperties.GROUND_SIGN_DIRECTION, direction);
    }

    @PowerNukkitDifference(info = "Was returning the wrong face, it now return the closest face, or the left face if even", since = "1.4.0.0-PN")
    @Override
    public BlockFace getBlockFace() {
        return getSignDirection().getClosestBlockFace();
    }

    @PowerNukkitOnly
    @Since("1.3.0.0-PN")
    @Override
    public void setBlockFace(BlockFace face) {
        setSignDirection(face.getCompassRoseDirection());
    }

    @Override
    @PowerNukkitOnly
    public boolean breaksWhenMoved() {
        return true;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }
}
