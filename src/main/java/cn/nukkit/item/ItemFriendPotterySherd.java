package cn.nukkit.item;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;

@PowerNukkitXOnly
@Since("1.20.50-r1")
public class ItemFriendPotterySherd extends ItemPotterySherd {
    public ItemFriendPotterySherd() {
        this(0, 1);
    }

    public ItemFriendPotterySherd(Integer meta) {
        this(meta, 1);
    }
    public ItemFriendPotterySherd(Integer meta, int count) {
        super(FRIEND_POTTERY_SHERD, meta, count, "Friend Pottery Sherd");
    }
}
