package com.phantomclient.config.modules;

import com.phantomclient.config.ConfigManager;
import net.minecraft.util.math.MathHelper;

import java.util.HashMap;
import java.util.Map;

public class BlockESPConfig extends ModuleConfig {
    private int range = 64;
    private float lineWidth = 2.0f;
    private final Map<String, Integer> blockColors = new HashMap<>();
    private final Map<String, Boolean> enabledBlocks = new HashMap<>();

    public BlockESPConfig() {
        super("block_esp");
        // Default colors for common blocks
        blockColors.put("minecraft:diamond_ore", 0xFF00FFFF);
        blockColors.put("minecraft:emerald_ore", 0xFF00FF00);
        blockColors.put("minecraft:gold_ore", 0xFFFFFF00);
        blockColors.put("minecraft:iron_ore", 0xFF808080);
        blockColors.put("minecraft:coal_ore", 0xFF000000);
        blockColors.put("minecraft:redstone_ore", 0xFFFF0000);
        blockColors.put("minecraft:lapis_ore", 0xFF0000FF);
        blockColors.put("minecraft:nether_quartz_ore", 0xFFFFFFFF);

        // Enable all blocks by default
        enabledBlocks.putAll(blockColors.keySet().stream()
            .collect(HashMap::new, (m, k) -> m.put(k, true), HashMap::putAll));
    }

    public int getRange() { return range; }
    public void setRange(int range) {
        this.range = MathHelper.clamp(range, 8, 256);
        ConfigManager.saveConfig();
    }

    public float getLineWidth() { return lineWidth; }
    public void setLineWidth(float lineWidth) {
        this.lineWidth = MathHelper.clamp(lineWidth, 0.5f, 5.0f);
        ConfigManager.saveConfig();
    }

    public Map<String, Integer> getBlockColors() { return new HashMap<>(blockColors); }
    public void setBlockColor(String blockId, int color) {
        blockColors.put(blockId, color);
        ConfigManager.saveConfig();
    }

    public Map<String, Boolean> getEnabledBlocks() { return new HashMap<>(enabledBlocks); }
    public void setBlockEnabled(String blockId, boolean enabled) {
        enabledBlocks.put(blockId, enabled);
        ConfigManager.saveConfig();
    }

    public boolean isBlockEnabled(String blockId) {
        return enabledBlocks.getOrDefault(blockId, false);
    }

    @Override
    public void loadFromData(Object data) {
        BlockESPData espData = (BlockESPData) data;
        this.enabled = espData.enabled;
        this.range = espData.range;
        this.lineWidth = espData.lineWidth;
        this.blockColors.clear();
        this.blockColors.putAll(espData.blockColors);
        this.enabledBlocks.clear();
        this.enabledBlocks.putAll(espData.enabledBlocks);
    }

    @Override
    public Object toData() {
        BlockESPData data = new BlockESPData();
        data.enabled = this.enabled;
        data.range = this.range;
        data.lineWidth = this.lineWidth;
        data.blockColors = new HashMap<>(this.blockColors);
        data.enabledBlocks = new HashMap<>(this.enabledBlocks);
        return data;
    }

    // Data class for serialization
    public static class BlockESPData {
        public boolean enabled;
        public int range;
        public float lineWidth;
        public Map<String, Integer> blockColors = new HashMap<>();
        public Map<String, Boolean> enabledBlocks = new HashMap<>();
    }
}
