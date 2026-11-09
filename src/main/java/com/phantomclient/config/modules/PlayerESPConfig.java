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

    // ... (add all other getters/setters with ConfigManager.saveConfig() calls)

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
