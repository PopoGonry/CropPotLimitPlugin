
package com.popogonry.cropPotLimitPlugin;

import org.bukkit.block.Block;

import java.util.List;
import java.util.Set;

public class PluginConfig {
    private final int cropPotLimit;
    private final Set<String> cropPotBlockIds;


    public PluginConfig(int cropPotLimit, Set<String> cropPotBlockIds) {
        this.cropPotLimit = cropPotLimit;
        this.cropPotBlockIds = cropPotBlockIds;
    }

    public int getCropPotLimit() {
        return cropPotLimit;
    }

    public Set<String> getCropPotBlockIds() {
        return cropPotBlockIds;
    }

    @Override
    public String toString() {
        return "PluginConfig{" +
                "cropPotLimit=" + cropPotLimit +
                ", cropPotBlockIds=" + cropPotBlockIds +
                '}';
    }
}
