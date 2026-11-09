package com.phantomclient.modules;

import com.phantomclient.config.modules.ModuleConfig;
import net.minecraft.client.MinecraftClient;

public abstract class Module {
    protected final MinecraftClient client = MinecraftClient.getInstance();
    protected boolean enabled = false;
    protected ModuleConfig config;

    public abstract void onInitialize();
    public abstract void onEnable();
    public abstract void onDisable();
    public abstract void onRender();
    public abstract void onClientTick();

    public void toggle() {
        setEnabled(!enabled);
    }

    public void setEnabled(boolean enabled) {
        if (this.enabled == enabled) return;

        this.enabled = enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public ModuleConfig getConfig() {
        return config;
    }
}
