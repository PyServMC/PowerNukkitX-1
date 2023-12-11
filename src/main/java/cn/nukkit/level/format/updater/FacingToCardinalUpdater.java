package cn.nukkit.level.format.updater;

import cn.nukkit.block.BlockID;
import cn.nukkit.blockstate.BlockState;
import cn.nukkit.math.BlockFace;
import cn.nukkit.blockproperty.CommonBlockProperties;
import cn.nukkit.level.format.ChunkSection;

class FacingToCardinalUpdater implements Updater {
    private final ChunkSection section;
    private boolean fix2 = false;

    public FacingToCardinalUpdater(ChunkSection section) {
        this.section = section;
    }

    public FacingToCardinalUpdater(ChunkSection section, boolean fix2) {
        this.section = section;
        this.fix2 = fix2;
    }

    @Override
    public boolean update(int offsetX, int offsetY, int offsetZ, int x, int y, int z, BlockState state) {
        int blockId = state.getBlockId();
        if (blockId != BlockID.CHEST
                && blockId != BlockID.ENDER_CHEST
                && blockId != BlockID.TRAPPED_CHEST
                && blockId != BlockID.STONECUTTER_BLOCK) {
            return false;
        }

        int stateOld = state.getExactIntStorage();
        if(stateOld != 0 && stateOld != 1 && stateOld != 2 && stateOld != 3 && stateOld != 4 && stateOld != 5) return false;

        int stateNew = getNewData(stateOld);

        return section.setBlockState(x, y, z, BlockState.of(blockId, stateNew));
    }

    private int getNewData(int fromData) {
        /*
            The PNX ids provided here are wrong, we use our own IDs
        
        return switch (fromData) {
            case 0, 1, 2 -> CommonBlockProperties.CARDINAL_DIRECTION.getMetaForValue(BlockFace.NORTH); //2
            case 3 -> CommonBlockProperties.CARDINAL_DIRECTION.getMetaForValue(BlockFace.SOUTH); //0
            case 4 -> CommonBlockProperties.CARDINAL_DIRECTION.getMetaForValue(BlockFace.WEST); //1
            case 5 -> CommonBlockProperties.CARDINAL_DIRECTION.getMetaForValue(BlockFace.EAST); //3
            default -> CommonBlockProperties.CARDINAL_DIRECTION.getMetaForValue(BlockFace.NORTH); //2
        };*/

        return switch (fromData) {
            case 2 -> CommonBlockProperties.CARDINAL_DIRECTION.getMetaForValue(BlockFace.NORTH); //2
            case 3 -> CommonBlockProperties.CARDINAL_DIRECTION.getMetaForValue(BlockFace.SOUTH); //0
            case 0 -> CommonBlockProperties.CARDINAL_DIRECTION.getMetaForValue(BlockFace.WEST); //1
            case 1 -> CommonBlockProperties.CARDINAL_DIRECTION.getMetaForValue(BlockFace.EAST); //3
            default -> CommonBlockProperties.CARDINAL_DIRECTION.getMetaForValue(BlockFace.NORTH); //2
        };
    }
}