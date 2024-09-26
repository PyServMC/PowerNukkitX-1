package cn.nukkit.network.process.processor;

import cn.nukkit.Player;
import cn.nukkit.PlayerHandle;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.network.process.DataPacketProcessor;
import cn.nukkit.network.protocol.CommandRequestPacket;
import cn.nukkit.network.protocol.ProtocolInfo;
import org.jetbrains.annotations.NotNull;

public class CommandRequestProcessor extends DataPacketProcessor<CommandRequestPacket> {
    @Override
    public void handle(@NotNull PlayerHandle playerHandle, @NotNull CommandRequestPacket pk) {
        if (!playerHandle.player.spawned || !playerHandle.player.isAlive()) {
            return;
        }
        //?? why set craftType
        playerHandle.player.craftingType = Player.CRAFTING_SMALL;
        PlayerCommandPreprocessEvent playerCommandPreprocessEvent = new PlayerCommandPreprocessEvent(playerHandle.player, pk.command);
        playerHandle.player.getServer().getPluginManager().callEvent(playerCommandPreprocessEvent);
        if (playerCommandPreprocessEvent.isCancelled()) {
            return;
        }
        playerHandle.player.getServer().executeCommand(playerCommandPreprocessEvent.getPlayer(), playerCommandPreprocessEvent.getMessage());
    }

    @Override
    public int getPacketId() {
        return ProtocolInfo.toNewProtocolID(ProtocolInfo.COMMAND_REQUEST_PACKET);
    }
}
