package cn.nukkit.utils.collection.nb;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;

import java.util.Map;

@Since("1.20.10-r1")
@PowerNukkitXOnly
public interface LongObjectEntry<V> extends Map.Entry<Long, V> {
    @Deprecated
    default Long getKey() {
        return getLongKey();
    }

    long getLongKey();
}
