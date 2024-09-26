package cn.nukkit.command.data;

import cn.nukkit.api.PowerNukkitXDifference;
import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;

import java.util.*;

@PowerNukkitXDifference(since = "1.19.50-r1", info = "Use List<Flag> instead")
public class CommandData implements Cloneable {

    public String description = "description";
    public CommandEnum aliases = null;
    public Map<String, CommandOverload> overloads = new HashMap<>();
    //默认带一个NOT_CHEAT标签
    public EnumSet<Flag> flags = EnumSet.of(Flag.NOT_CHEAT);
    public int permission;

    @Since("1.20.10-r1")
    public List<ChainedSubCommandData> subcommands = new ArrayList<>();

    @Override
    public CommandData clone() {
        try {
            return (CommandData) super.clone();
        } catch (Exception e) {
            return new CommandData();
        }
    }

    // Bit flags
    @PowerNukkitXOnly
    @Since("1.19.50-r1")
    public enum Flag {
        NONE(0x00),
        TEST_USAGE(0x01),
        HIDDEN_FROM_COMMAND_BLOCK(0x02),
        HIDDEN_FROM_PLAYER(0x04),
        HIDDEN(0x06),
        HIDDEN_FROM_AUTOMATION(0x08),
        REMOVED(0xe),
        LOCAL_SYNC(0x10),
        EXECUTE_DISALLOWED(0x20),
        MESSAGE_TYPE(0x40),
        NOT_CHEAT(0x80),
        ASYNC(0x100),
        EDITOR(0x200);

        public final int bit;

        Flag(int bit) {
            this.bit = bit;
        }
    }
}
