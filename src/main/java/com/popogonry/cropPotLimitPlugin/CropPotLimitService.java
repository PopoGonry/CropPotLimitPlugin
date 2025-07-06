package com.popogonry.cropPotLimitPlugin;

import dev.lone.itemsadder.api.CustomBlock;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CropPotLimitService {
    public void initializeChunkBlockCache() {
        CropPotLimitRepository.chunkTotalCountCache.clear();

        Set<String> targetBlockIds = PluginRepository.pluginConfig.getCropPotBlockIds();
        for (World world : Bukkit.getWorlds()) {
            for (Chunk chunk : world.getLoadedChunks()) {

                int count = 0;

                for (int x = 0; x < 16; x++) {
                    for (int y = 0; y < world.getMaxHeight(); y++) {
                        for (int z = 0; z < 16; z++) {
                            Block block = chunk.getBlock(x, y, z);
                            CustomBlock cb = CustomBlock.byAlreadyPlaced(block);
                            if (cb == null) continue;

                            String id = cb.getNamespacedID();
                            if (targetBlockIds.contains(id)) {
                                count++;
                            }
                        }
                    }
                }

                if (count > 0) {
                    CropPotLimitRepository.chunkTotalCountCache.put(chunk, count);
                }
            }
        }

        Bukkit.getLogger().info("[CropPotLimitPlugin] 청크 블럭 캐시 초기화 완료 (총 " +
                CropPotLimitRepository.chunkTotalCountCache.size() + " 청크)");
    }
}
