package cn.nukkit.network.protocol.types.structure;

import cn.nukkit.blockproperty.value.StructureBlockType;
import cn.nukkit.network.protocol.StructureBlockUpdatePacket;

public record StructureEditorData(String name, String dataField, boolean includingPlayers, boolean boundingBoxVisible,
                                  StructureBlockType type, StructureSettings settings,
                                  StructureBlockUpdatePacket.SaveMode mode) {
}
