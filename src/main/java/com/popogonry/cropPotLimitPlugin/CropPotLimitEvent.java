package com.popogonry.cropPotLimitPlugin;

import dev.lone.itemsadder.api.CustomBlock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class CropPotLimitEvent implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        Chunk chunk = block.getChunk();

        CustomBlock customBlock = CustomBlock.byItemStack(event.getPlayer().getItemInHand());
        if (customBlock == null) return;

        String blockId = customBlock.getNamespacedID();
        if (!PluginRepository.pluginConfig.getCropPotBlockIds().contains(blockId)) return;

        CooldownManager cooldownManager = new CooldownManager();
        if (cooldownManager.isOnCooldown(event.getPlayer(), 25L)) {
            return;
        }

        int currentTotal = CropPotLimitRepository.chunkTotalCountCache.getOrDefault(chunk, 0);

        if (currentTotal >= PluginRepository.pluginConfig.getCropPotLimit()) {

            // 수정
            player.sendMessage(ChatColor.RED + "이 청크에서는 경작지를 최대 " + PluginRepository.pluginConfig.getCropPotLimit() + "개까지만 설치할 수 있습니다.");
            event.setCancelled(true);
            return;
        }

        CropPotLimitRepository.chunkTotalCountCache.put(chunk, currentTotal + 1);

        Bukkit.getScheduler().runTaskLater(CropPotLimitPlugin.getServerInstance(), () -> {
            if(CustomBlock.byAlreadyPlaced(event.getBlock()) == null) {
                CropPotLimitRepository.chunkTotalCountCache.put(chunk, currentTotal);
            }
        }, 1L); // 1틱 = 0.05초

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Chunk chunk = block.getChunk();

        CustomBlock customBlock = CustomBlock.byAlreadyPlaced(block);
        if (customBlock == null) return;

        String blockId = customBlock.getNamespacedID();
        if (!PluginRepository.pluginConfig.getCropPotBlockIds().contains(blockId)) return;

        int currentTotal = CropPotLimitRepository.chunkTotalCountCache.getOrDefault(chunk, 0);
        if (currentTotal <= 1) {
            CropPotLimitRepository.chunkTotalCountCache.remove(chunk);
        } else {
            CropPotLimitRepository.chunkTotalCountCache.put(chunk, currentTotal - 1);
        }
    }
}
