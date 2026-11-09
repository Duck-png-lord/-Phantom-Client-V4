package com.phantomclient.modules;

import com.phantomclient.PhantomClient;
import com.phantomclient.config.modules.PlayerESPConfig;
import com.phantomclient.util.RenderUtils;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class PlayerESPModule extends Module {
    private PlayerESPConfig config;

    @Override
    public void onInitialize() {
        config = new PlayerESPConfig();
        PhantomClient.PLAYER_ESP.setEnabled(config.isEnabledByDefault());
    }

    @Override
    public void onEnable() {
        config.load();
    }

    @Override
    public void onDisable() {}

    @Override
    public void onRender() {
        if (client.world == null || client.player == null) return;

        for (AbstractClientPlayerEntity player : client.world.getPlayers()) {
            if (player == client.player && !config.isShowSelf()) continue;

            Vec3d pos = player.getPos();
            Box box = new Box(
                    pos.x - 0.4,
                    pos.y,
                    pos.z - 0.4,
                    pos.x + 0.4,
                    pos.y + 1.9,
                    pos.z + 0.4
            );

            int color = config.getPlayerColors().getOrDefault(player.getName().getString(), 0xFF00FF00);
            RenderUtils.drawOutlinedBox(box, color, config.getBoxLineWidth());

            if (config.isShowNames()) {
                RenderUtils.drawTextInWorld(
                        player.getName().getString(),
                        pos.x,
                        pos.y + 2.0,
                        pos.z,
                        config.getNameColor(),
                        config.getNameScale()
                );
            }

            if (config.isShowDistance()) {
                double distance = client.player.distanceTo(player);
                RenderUtils.drawTextInWorld(
                        String.format("%.1f", distance) + "m",
                        pos.x,
                        pos.y + 2.5,
                        pos.z,
                        config.getDistanceColor(),
                        config.getDistanceScale()
                );
            }
        }
    }

    @Override
    public void onClientTick() {}
}
