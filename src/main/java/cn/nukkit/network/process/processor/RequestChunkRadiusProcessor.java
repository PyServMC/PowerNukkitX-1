package cn.nukkit.network.process.processor;

import cn.nukkit.PlayerHandle;
import cn.nukkit.network.process.DataPacketProcessor;
import cn.nukkit.network.protocol.ChunkRadiusUpdatedPacket;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.network.protocol.RequestChunkRadiusPacket;
import org.jetbrains.annotations.NotNull;

public class RequestChunkRadiusProcessor extends DataPacketProcessor<RequestChunkRadiusPacket> {
    @Override
    public void handle(@NotNull PlayerHandle playerHandle, @NotNull RequestChunkRadiusPacket pk) {
        ChunkRadiusUpdatedPacket chunkRadiusUpdatePacket = new ChunkRadiusUpdatedPacket();
        playerHandle.setChunkRadius(Math.max(3, Math.min(pk.radius, playerHandle.player.getViewDistance())));
        chunkRadiusUpdatePacket.radius = playerHandle.getChunkRadius();
        playerHandle.player.dataPacket(chunkRadiusUpdatePacket);
    }

    @Override
    public int getPacketId() {
        return ProtocolInfo.toNewProtocolID(ProtocolInfo.REQUEST_CHUNK_RADIUS_PACKET);
    }
}
