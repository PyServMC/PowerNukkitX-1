package cn.nukkit.level.format.updater;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.blockstate.BlockState;
import cn.nukkit.level.Location;
import cn.nukkit.level.format.ChunkSection;

public class LogUpdater_1_20_40 implements Updater {
    private final ChunkSection section;

    public LogUpdater_1_20_40(ChunkSection section) {
        this.section = section;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Override
    public boolean update(int offsetX, int offsetY, int offsetZ, int x, int y, int z, BlockState state) {
        int blockId = state.getBlockId();
        if (blockId != BlockID.LOG) {
            return false;
        }

        int newLogID = getNewID(state.getDataStorage().intValue());
        return section.setBlockState(x, y, z, BlockState.of(BlockID.LOG, newLogID));
    }

    private int getNewID(int fromData) {
        /*
            OLD STATES: 
            
            "4": "minecraft:oak_log;pillar_axis=x",
            "5": "minecraft:spruce_log;pillar_axis=x",
            "6": "minecraft:birch_log;pillar_axis=x",
            "7": "minecraft:jungle_log;pillar_axis=x",
            "8": "minecraft:oak_log;pillar_axis=z",
            "9": "minecraft:spruce_log;pillar_axis=z",
            "10": "minecraft:birch_log;pillar_axis=z",
            "11": "minecraft:jungle_log;pillar_axis=z"

            NEW STATES:
            "8": "minecraft:oak_log;pillar_axis=x",
            "9": "minecraft:spruce_log;pillar_axis=x",
            "10": "minecraft:birch_log;pillar_axis=x",
            "11": "minecraft:jungle_log;pillar_axis=x",
            "16": "minecraft:oak_log;pillar_axis=z",
            "17": "minecraft:spruce_log;pillar_axis=z",
            "18": "minecraft:birch_log;pillar_axis=z",
            "19": "minecraft:jungle_log;pillar_axis=z"

            convert old to new
        */

        int newID = switch (fromData) {
            case 4 -> 8;
            case 5 -> 9;
            case 6 -> 10;
            case 7 -> 11;
            case 8 -> 16;
            case 9 -> 17;
            case 10 -> 18;
            case 11 -> 19;
            default -> fromData;
        };

        return newID;
    }
}
