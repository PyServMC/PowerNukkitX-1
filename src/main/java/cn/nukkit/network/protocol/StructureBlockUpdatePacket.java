package cn.nukkit.network.protocol;

import cn.nukkit.math.BlockVector3;
import cn.nukkit.math.Vector3;
import cn.nukkit.network.protocol.types.structure.StructureEditorData;
import cn.nukkit.network.protocol.types.structure.StructureSettings;
import lombok.ToString;

@ToString
public class StructureBlockUpdatePacket extends DataPacket {
    public BlockVector3 blockPosition;
    public StructureEditorData editorData;
    public boolean powered;

    @Override
    public byte pid() {
        return ProtocolInfo.STRUCTURE_BLOCK_UPDATE_PACKET;
    }

    public BlockVector3 getBlockPosition() {
        return blockPosition;
    }

    public StructureEditorData getEditorData() {
        return editorData;
    }

    public boolean isPowered() {
        return powered;
    }

    public void setBlockPosition(BlockVector3 blockPosition) {
        this.blockPosition = blockPosition;
    }

    public void setEditorData(StructureEditorData editorData) {
        this.editorData = editorData;
    }

    public void setPowered(boolean powered) {
        this.powered = powered;
    }

    @Override
    public void decode() {

    }

    @Override
    public void encode() {
        this.reset();
        this.putBlockVector3(this.getBlockPosition());
    }

    private void writeEditorData() {
        this.putString(this.editorData.name());
        this.putString(this.editorData.dataField());
        this.putBoolean(this.editorData.includingPlayers());
        this.putBoolean(this.editorData.boundingBoxVisible());
        this.putVarInt(this.editorData.type().ordinal());
        this.writeStructureSettings();
    }

    private void writeStructureSettings() {
        StructureSettings settings = this.editorData.settings();
        this.putString(settings.paletteName());
        this.putBoolean(settings.ignoringBlocks());
        this.putBoolean(settings.ignoringEntities());
        this.putBlockVector3(settings.size());
        this.putBlockVector3(settings.offset());
        this.putUnsignedVarInt(settings.lastEditedByEntityId());
        this.putInt(settings.rotation().ordinal());
        this.putInt(settings.mirror().ordinal());
        this.putLFloat(settings.integrityValue());
        this.putLInt(settings.integritySeed());
        this.putVector3f(settings.pivot());
    }

    public enum SaveMode {
        SAVE_TO_MEMORY,
        SAVE_TO_DISK;

        private static final SaveMode[] VALUES = values();

        public static SaveMode get(int id) {
            return VALUES[id];
        }
    }

    public enum Rotation {
        NONE,
        ROTATE_90,
        ROTATE_180,
        ROTATE_270;

        private static final Rotation[] VALUES = values();

        public static Rotation get(int id) {
            return VALUES[id];
        }
    }

    public enum Mirror {
        NONE,
        X,
        Y,
        Z;

        private static final Mirror[] VALUES = values();

        public static Mirror get(int id) {
            return VALUES[id];
        }
    }

    public enum AnimationMode {
        NONE,
        LAYER,
        BLOCKS;

        private static final AnimationMode[] VALUES = values();

        public static AnimationMode get(int id) {
            return VALUES[id];
        }
    }
}
