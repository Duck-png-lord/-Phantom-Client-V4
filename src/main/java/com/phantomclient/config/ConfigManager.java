package com.phantomclient.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import com.phantomclient.PhantomClient;
import com.phantomclient.config.modules.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("phantomclient.json");

    public static void loadConfig() {
        if (!Files.exists(CONFIG_PATH)) {
            saveConfig();
            return;
        }

        try {
            String json = Files.readString(CONFIG_PATH);
            ConfigData data = GSON.fromJson(json, ConfigData.class);

            PhantomClient.PLAYER_ESP.setEnabled(data.playerESP.enabled);
            PhantomClient.PLAYER_ESP.getConfig().loadFromData(data.playerESP);

            PhantomClient.BLOCK_ESP.setEnabled(data.blockESP.enabled);
            PhantomClient.BLOCK_ESP.getConfig().loadFromData(data.blockESP);

            PhantomClient.SOUND_ESP.setEnabled(data.soundESP.enabled);
            PhantomClient.SOUND_ESP.getConfig().loadFromData(data.soundESP);

            PhantomClient.LIGHT_ESP.setEnabled(data.lightESP.enabled);
            PhantomClient.LIGHT_ESP.getConfig().loadFromData(data.lightESP);

            PhantomClient.AUTO_TOTEM.setEnabled(data.autoTotem.enabled);
            PhantomClient.AUTO_TOTEM.getConfig().loadFromData(data.autoTotem);

            PhantomClient.FREECAM.setEnabled(data.freecam.enabled);
            PhantomClient.FREECAM.getConfig().loadFromData(data.freecam);

            PhantomClient.REACH.setEnabled(data.reach.enabled);
            PhantomClient.REACH.getConfig().loadFromData(data.reach);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveConfig() {
        ConfigData data = new ConfigData();

        data.playerESP = PhantomClient.PLAYER_ESP.getConfig().toData();
        data.blockESP = PhantomClient.BLOCK_ESP.getConfig().toData();
        data.soundESP = PhantomClient.SOUND_ESP.getConfig().toData();
        data.lightESP = PhantomClient.LIGHT_ESP.getConfig().toData();
        data.autoTotem = PhantomClient.AUTO_TOTEM.getConfig().toData();
        data.freecam = PhantomClient.FREECAM.getConfig().toData();
        data.reach = PhantomClient.REACH.getConfig().toData();

        try {
            Files.writeString(CONFIG_PATH, GSON.toJson(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ConfigData {
        public PlayerESPConfig.PlayerESPData playerESP = new PlayerESPConfig.PlayerESPData();
        public BlockESPConfig.BlockESPData blockESP = new BlockESPConfig.BlockESPData();
        public SoundESPConfig.SoundESPData soundESP = new SoundESPConfig.SoundESPData();
        public LightESPConfig.LightESPData lightESP = new LightESPConfig.LightESPData();
        public AutoTotemConfig.AutoTotemData autoTotem = new AutoTotemConfig.AutoTotemData();
        public FreecamConfig.FreecamData freecam = new FreecamConfig.FreecamData();
        public ReachConfig.ReachData reach = new ReachConfig.ReachData();
    }
}
