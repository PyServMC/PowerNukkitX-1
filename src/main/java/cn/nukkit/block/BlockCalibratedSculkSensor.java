package cn.nukkit.block;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.blockproperty.BlockProperties;
import cn.nukkit.blockproperty.CommonBlockProperties;
import cn.nukkit.blockproperty.IntBlockProperty;

//todo complete
@PowerNukkitXOnly
@Since("1.20.10-r2")
public class BlockCalibratedSculkSensor extends BlockTransparentMeta {
    public static final IntBlockProperty SCULK_SENSOR_PHASE = new IntBlockProperty("sculk_sensor_phase", false, 2);

    public static final BlockProperties PROPERTIES = new BlockProperties(CommonBlockProperties.DIRECTION, SCULK_SENSOR_PHASE);

    @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    public BlockCalibratedSculkSensor() {
    }

    public BlockCalibratedSculkSensor(int meta) {
        super(meta);
    }

    public int getId() {
        return CALIBRATED_SCULK_SENSOR;
    }

    public String getName() {
        return "Calibrated Sculk Sensor";
    }
}