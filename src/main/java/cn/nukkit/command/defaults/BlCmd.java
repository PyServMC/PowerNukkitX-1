package cn.nukkit.command.defaults;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.command.tree.ParamList;
import cn.nukkit.command.utils.CommandLogger;
import cn.nukkit.event.player.PlayerKickEvent;

import cn.nukkit.block.Block;
import cn.nukkit.blockstate.BlockState;

import java.util.Map;

/**
 * @author KeksDev (PyCMC)
 */
public class BlCmd extends VanillaCommand {

    public BlCmd(String name) {
        super(name, "Get blockstate informations", "-");
        this.setPermission("pycmc.admin.blockstate");
        this.commandParameters.clear();
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        
        Block b = ((Player) sender).getTargetBlock(10);
        if (b == null) {
            sender.sendMessage("§cYou must look at a block!");
            return true;
        }

        BlockState bs = BlockState.of(b.getId(), b.getDataStorage());
        sender.sendMessage("§aBlock: " + bs.toString());
        sender.sendMessage("§aBlockState: " + bs.toString());
        sender.sendMessage("§aBlockState: " + bs.getExactIntStorage());


        return true;
    }
}
