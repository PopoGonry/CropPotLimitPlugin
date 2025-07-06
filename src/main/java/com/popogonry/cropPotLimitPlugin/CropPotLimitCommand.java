package com.popogonry.cropPotLimitPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CropPotLimitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!sender.isOp()) {
            return true;
        }
        CropPotLimitService cropPotLimitService = new CropPotLimitService();
        cropPotLimitService.initializeChunkBlockCache();
        sender.sendMessage("캐시 초기화 완료");

        return false;
    }
}
