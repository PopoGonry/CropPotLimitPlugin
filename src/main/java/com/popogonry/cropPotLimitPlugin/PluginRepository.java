
package com.popogonry.cropPotLimitPlugin;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PluginRepository {
    private static final String CONFIG_FILE_NAME = "config.yml";
    private final String configBasePath = CropPotLimitPlugin.getServerInstance().getDataFolder().getAbsolutePath();
    private final PluginDataConfig pluginDataConfig;
    public static PluginConfig pluginConfig;

    public PluginRepository() {
        this.pluginDataConfig = new PluginDataConfig(this.configBasePath, "config.yml");
    }

    public void reloadConfig() {
        this.pluginDataConfig.reload();
    }

    public void saveConfig() {
        this.pluginDataConfig.store();
    }

    public void loadPluginDataConfig() {
        pluginConfig = this.pluginDataConfig.loadPluginDataConfig();
    }

}
