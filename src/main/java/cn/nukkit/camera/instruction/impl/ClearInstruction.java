package cn.nukkit.camera.instruction.impl;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.camera.instruction.CameraInstruction;

/**
 * @author daoge_cmd
 * @date 2023/6/11
 * PowerNukkitX Project
 */
@PowerNukkitXOnly
@Since("1.20.0-r2")
public class ClearInstruction implements CameraInstruction {
    private static final ClearInstruction INSTANCE = new ClearInstruction();

    private ClearInstruction() {}

    public static ClearInstruction get() {
        return INSTANCE;
    }
}
