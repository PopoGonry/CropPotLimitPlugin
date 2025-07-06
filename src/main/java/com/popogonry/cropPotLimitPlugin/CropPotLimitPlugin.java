package com.popogonry.cropPotLimitPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class CropPotLimitPlugin extends JavaPlugin {

    private static CropPotLimitPlugin serverInstance;


    @Override
    public void onEnable() {
        // Plugin startup logic
        serverInstance = this;
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new CropPotLimitEvent(), this);
        getServer().getPluginCommand("화분초기화").setExecutor(new CropPotLimitCommand());


        PluginRepository pluginRepository = new PluginRepository();
        pluginRepository.loadPluginDataConfig();

        CropPotLimitService cropPotLimitService = new CropPotLimitService();
        cropPotLimitService.initializeChunkBlockCache();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static CropPotLimitPlugin getServerInstance() {
        return serverInstance;
    }

}
