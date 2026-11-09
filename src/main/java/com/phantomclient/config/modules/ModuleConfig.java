package com.phantomclient.config.modules;

public abstract class ModuleConfig {
    protected boolean enabled = false;
    protected final String moduleId;

    public ModuleConfig(String moduleId) {
        this.moduleId = moduleId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabledByDefault() {
        return false;
    }

    public abstract void loadFromData(Object data);
    public abstract Object toData();
}
