package cn.nukkit.network.protocol.types.structure;

import cn.nukkit.math.BlockVector3;
import cn.nukkit.math.Vector3f;
import cn.nukkit.network.protocol.StructureBlockUpdatePacket;

public record StructureSettings(String paletteName, boolean ignoringEntities, boolean ignoringBlocks,
                                boolean nonTickingPlayers, BlockVector3 size, BlockVector3 offset,
                                long lastEditedByEntityId, StructureBlockUpdatePacket.Rotation rotation, StructureBlockUpdatePacket.Mirror mirror,
                                StructureBlockUpdatePacket.AnimationMode animationMode, float animationSeconds, float integrityValue,
                                int integritySeed, Vector3f pivot) {
}
