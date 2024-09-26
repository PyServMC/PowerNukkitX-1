package cn.nukkit.camera.data;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;

/**
 * @author daoge_cmd
 * @date 2023/6/11
 * PowerNukkitX Project
 */
@PowerNukkitXOnly
@Since("1.20.0-r2")
public record Pos(float x, float y, float z) implements SerializableData {
    public CompoundTag serialize() {
        return new CompoundTag("pos")
                .putList("pos", new ListTag<>("pos")
                        .add(new FloatTag(x))
                        .add(new FloatTag(y))
                        .add(new FloatTag(z))
                );
    }
}
