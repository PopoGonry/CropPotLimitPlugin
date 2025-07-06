
package com.popogonry.cropPotLimitPlugin;

import java.util.HashSet;
import java.util.Set;

public class PluginDataConfig extends Config {
    public PluginDataConfig(String basePath, String fileName) {
        super(basePath, fileName);
    }

    public PluginConfig loadPluginDataConfig() {
        return new PluginConfig(
                this.getConfig().getInt("Crop-Pot-Limit"),
                new HashSet<>(this.getConfig().getStringList("Crop-Pot-Block-Ids"))
        );
    }

    public void loadDefaults() {
    }

    public void applySettings() {
        this.getConfig().options().copyDefaults(true);
    }
}