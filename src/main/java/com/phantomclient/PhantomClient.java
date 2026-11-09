package com.phantomclient;

import com.phantomclient.config.ConfigManager;
import com.phantomclient.modules.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class PhantomClient implements ClientModInitializer {
    public static final String MOD_ID = "phantomclient";

    // Modules
    public static final PlayerESPModule PLAYER_ESP = new PlayerESPModule();
    public static final BlockESPModule BLOCK_ESP = new BlockESPModule();
    public static final SoundESPModule SOUND_ESP = new SoundESPModule();
    public static final LightESPModule LIGHT_ESP = new LightESPModule();
    public static final AutoTotemModule AUTO_TOTEM = new AutoTotemModule();
    public static final FreecamModule FREECAM = new FreecamModule();
    public static final ReachModule REACH = new ReachModule();

    // Keybinds
    public static KeyBinding openMenuKey;
    public static KeyBinding togglePlayerESPKey;
    public static KeyBinding toggleBlockESPKey;
    public static KeyBinding toggleSoundESPKey;
    public static KeyBinding toggleLightESPKey;
    public static KeyBinding toggleAutoTotemKey;
    public static KeyBinding toggleFreecamKey;
    public static KeyBinding toggleReachKey;

    @Override
    public void onInitializeClient() {
        // Register modules
        registerModule(PLAYER_ESP);
        registerModule(BLOCK_ESP);
        registerModule(SOUND_ESP);
        registerModule(LIGHT_ESP);
        registerModule(AUTO_TOTEM);
        registerModule(FREECAM);
        registerModule(REACH);

        // Register keybinds
        openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.phantomclient.menu",
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.phantomclient.general"
        ));

        togglePlayerESPKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.phantomclient.playeresp",
                GLFW.GLFW_KEY_UNKNOWN,
                "category.phantomclient.modules"
        ));

        toggleBlockESPKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.phantomclient.blockesp",
                GLFW.GLFW_KEY_UNKNOWN,
                "category.phantomclient.modules"
        ));

        toggleSoundESPKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.phantomclient.soundesp",
                GLFW.GLFW_KEY_UNKNOWN,
                "category.phantomclient.modules"
        ));

        toggleLightESPKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.phantomclient.lightesp",
                GLFW.GLFW_KEY_UNKNOWN,
                "category.phantomclient.modules"
        ));

        toggleAutoTotemKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.phantomclient.autototem",
                GLFW.GLFW_KEY_UNKNOWN,
                "category.phantomclient.modules"
        ));

        toggleFreecamKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.phantomclient.freecam",
                GLFW.GLFW_KEY_UNKNOWN,
                "category.phantomclient.modules"
        ));

        toggleReachKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.phantomclient.reach",
                GLFW.GLFW_KEY_UNKNOWN,
                "category.phantomclient.modules"
        ));

        // Load config
        ConfigManager.loadConfig();

        // Register tick events
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMenuKey.wasPressed()) {
                client.setScreen(new ModMenuScreen(null));
            }

            while (togglePlayerESPKey.wasPressed()) PLAYER_ESP.toggle();
            while (toggleBlockESPKey.wasPressed()) BLOCK_ESP.toggle();
            while (toggleSoundESPKey.wasPressed()) SOUND_ESP.toggle();
            while (toggleLightESPKey.wasPressed()) LIGHT_ESP.toggle();
            while (toggleAutoTotemKey.wasPressed()) AUTO_TOTEM.toggle();
            while (toggleFreecamKey.wasPressed()) FREECAM.toggle();
            while (toggleReachKey.wasPressed()) REACH.toggle();
        });
    }

    private void registerModule(Module module) {
        module.onInitialize();
    }
}
