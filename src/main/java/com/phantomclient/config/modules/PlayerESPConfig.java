package com.phantomclient.config.modules;

import com.phantomclient.config.ConfigManager;
import net.minecraft.util.math.MathHelper;

import java.util.HashMap;
import java.util.Map;

public class PlayerESPConfig extends ModuleConfig {
    private boolean showSelf = false;
    private boolean showNames = true;
    private boolean showDistance = true;
    private float nameScale = 1.0f;
    private float distanceScale = 1.0f;
    private int nameColor = 0xFFFFFFFF;
    private int distanceColor = 0xFFFFFFFF;
    private float boxLineWidth = 2.0f;
    private final Map<String, Integer> playerColors = new HashMap<>();

    public PlayerESPConfig() {
        super("player_esp");
        // Default colors
        playerColors.put("Notch", 0xFF0000FF);
        playerColors.put("Grumm", 0xFFFF0000);
    }

    // Getters and setters with config saving
    public boolean isShowSelf() { return showSelf; }
    public void setShowSelf(boolean showSelf) {
        this.showSelf = showSelf;
        ConfigManager.saveConfig();
    }

    public boolean isShowNames() { return showNames; }
    public void setShowNames(boolean showNames) {
        this.showNames = showNames;
        ConfigManager.saveConfig();
    }

    public boolean isShowDistance() { return showDistance; }
    public void setShowDistance(boolean showDistance) {
        this.showDistance = showDistance;
        ConfigManager.saveConfig();
    }

    public float getNameScale() { return nameScale; }
    public void setNameScale(float nameScale) {
        this.nameScale = MathHelper.clamp(nameScale, 0.1f, 3.0f);
        ConfigManager.saveConfig();
    }

    public float getDistanceScale() { return distanceScale; }
    public void setDistanceScale(float distanceScale) {
        this.distanceScale = MathHelper.clamp(distanceScale, 0.1f, 3.0f);
        ConfigManager.saveConfig();
    }

    public int getNameColor() { return nameColor; }
    public void setNameColor(int nameColor) {
        this.nameColor = nameColor;
        ConfigManager.saveConfig();
    }

    public int getDistanceColor() { return distanceColor; }
    public void setDistanceColor(int distanceColor) {
        this.distanceColor = distanceColor;
        ConfigManager.saveConfig();
    }

    public float getBoxLineWidth() { return boxLineWidth; }
    public void setBoxLineWidth(float boxLineWidth) {
        this.boxLineWidth = MathHelper.clamp(boxLineWidth, 0.5f, 5.0f);
        ConfigManager.saveConfig();
    }

    public Map<String, Integer> getPlayerColors() { return new HashMap<>(playerColors); }
    public void setPlayerColor(String playerName, int color) {
        playerColors.put(playerName, color);
        ConfigManager.saveConfig();
    }

    public void removePlayerColor(String playerName) {
        playerColors.remove(playerName);
        ConfigManager.saveConfig();
    }

    @Override
    public void loadFromData(Object data) {
        PlayerESPData espData = (PlayerESPData) data;
        this.enabled = espData.enabled;
        this.showSelf = espData.showSelf;
        this.showNames = espData.showNames;
        this.showDistance = espData.showDistance;
        this.nameScale = espData.nameScale;
        this.distanceScale = espData.distanceScale;
        this.nameColor = espData.nameColor;
        this.distanceColor = espData.distanceColor;
        this.boxLineWidth = espData.boxLineWidth;
        this.playerColors.clear();
        this.playerColors.putAll(espData.playerColors);
    }

    @Override
    public Object toData() {
        PlayerESPData data = new PlayerESPData();
        data.enabled = this.enabled;
        data.showSelf = this.showSelf;
        data.showNames = this.showNames;
        data.showDistance = this.showDistance;
        data.nameScale = this.nameScale;
        data.distanceScale = this.distanceScale;
        data.nameColor = this.nameColor;
        data.distanceColor = this.distanceColor;
        data.boxLineWidth = this.boxLineWidth;
        data.playerColors = new HashMap<>(this.playerColors);
        return data;
    }

    // Data class for serialization
    public static class PlayerESPData {
        public boolean enabled;
        public boolean showSelf;
        public boolean showNames;
        public boolean showDistance;
        public float nameScale;
        public float distanceScale;
        public int nameColor;
        public int distanceColor;
        public float boxLineWidth;
        public Map<String, Integer> playerColors = new HashMap<>();
    }
}
