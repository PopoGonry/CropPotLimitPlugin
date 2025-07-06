package com.popogonry.cropPotLimitPlugin;

import org.bukkit.Chunk;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CropPotLimitRepository {
    public static final Map<Chunk, Integer> chunkTotalCountCache = new HashMap<>();
}
