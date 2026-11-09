package com.phantomclient.config.modules;

import com.phantomclient.config.ConfigManager;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.MathHelper;

import java.util.HashMap;
import java.util.Map;

public class SoundESPConfig extends ModuleConfig {
    private int range = 64;
    private int indicatorSize = 20;
    private float indicatorOpacity = 0.7f;
    private final Map<SoundCategory, Boolean> enabledCategories = new HashMap<>();
    private final Map<SoundCategory, Integer> categoryColors = new HashMap<>();

    public SoundESPConfig() {
        super("sound_esp");
        // Enable all categories by default
        for (SoundCategory category : SoundCategory.values()) {
            enabledCategories.put(category, true);
            categoryColors.put(category, getDefaultColor(category));
        }
    }

    public int getRange() { return range; }
    public void setRange(int range) {
        this.range = MathHelper.clamp(range, 8, 256);
        ConfigManager.saveConfig();
    }

    public int getIndicatorSize() { return indicatorSize; }
    public void setIndicatorSize(int size) {
        this.indicatorSize = MathHelper.clamp(size, 5, 50);
        ConfigManager.saveConfig();
    }

    public float getIndicatorOpacity() { return indicatorOpacity; }
    public void setIndicatorOpacity(float opacity) {
        this.indicatorOpacity = MathHelper.clamp(opacity, 0.1f, 1.0f);
        ConfigManager.saveConfig();
    }

    public boolean isCategoryEnabled(SoundCategory category) {
        return enabledCategories.getOrDefault(category, false);
    }

    public void setCategoryEnabled(SoundCategory category, boolean enabled) {
        enabledCategories.put(category, enabled);
        ConfigManager.saveConfig();
    }

    public int getCategoryColor(SoundCategory category) {
        return categoryColors.getOrDefault(category, 0xFFFFFFFF);
    }

    public void setCategoryColor(SoundCategory category, int color) {
        categoryColors.put(category, color);
        ConfigManager.saveConfig();
    }

    private int getDefaultColor(SoundCategory category) {
        return switch (category) {
            case MASTER -> 0xFFFFFFFF;
            case MUSIC -> 0xFF00FF00;
            case RECORDS -> 0xFFFF00FF;
            case WEATHER -> 0xFF00FFFF;
            case BLOCKS -> 0xFFFFFF00;
            case HOSTILE -> 0xFFFF0000;
            case NEUTRAL -> 0xFF0000FF;
            case PLAYERS -> 0xFFAA00AA;
            case AMBIENT -> 0xFF00AAAA;
            case VOICE -> 0xFFAAAA00;
        };
    }

    @Override
    public void loadFromData(Object data) {
        SoundESPData espData = (SoundESPData) data;
        this.enabled = espData.enabled;
        this.range = espData.range;
        this.indicatorSize = espData.indicatorSize;
        this.indicatorOpacity = espData.indicatorOpacity;
        this.enabledCategories.clear();
        this.enabledCategories.putAll(espData.enabledCategories);
        this.categoryColors.clear();
        this.categoryColors.putAll(espData.categoryColors);
    }

    @Override
    public Object toData() {
        SoundESPData data = new SoundESPData();
        data.enabled = this.enabled;
        data.range = this.range;
        data.indicatorSize = this.indicatorSize;
        data.indicatorOpacity = this.indicatorOpacity;
        data.enabledCategories = new HashMap<>(this.enabledCategories);
        data.categoryColors = new HashMap<>(this.categoryColors);
        return data;
    }

    // Data class for serialization
    public static class SoundESPData {
        public boolean enabled;
        public int range;
        public int indicatorSize;
        public float indicatorOpacity;
        public Map<SoundCategory, Boolean> enabledCategories = new HashMap<>();
        public Map<SoundCategory, Integer> categoryColors = new HashMap<>();
    }
}
