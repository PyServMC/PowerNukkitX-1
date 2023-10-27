package cn.nukkit.pycmc;

import cn.nukkit.Server;

public enum CustomProperties {
    NO_SCULC_SENSOR_SOUND("NO_SCULC_SOUNDS");

    private String name;

    CustomProperties(String name) {
        this.name = name;
    }

    public void enable() {
        if(!Server.customProperties.contains(name)) Server.customProperties.add(name);
    }

    public void disable() {
        if(Server.customProperties.contains(name)) Server.customProperties.remove(name);
    }

    public boolean isEnabled() {
        return Server.customProperties.contains(name);
    }
}
