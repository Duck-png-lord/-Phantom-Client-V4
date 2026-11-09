package com.phantomclient.modules;

import com.phantomclient.PhantomClient;
import com.phantomclient.config.modules.BlockESPConfig;
import com.phantomclient.util.RenderUtils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.Map;

public class BlockESPModule extends Module {
    private BlockESPConfig config;
    private final Map<Block, Integer> blockColors = new HashMap<>();

    @Override
    public void onInitialize() {
        config = new BlockESPConfig();
        PhantomClient.BLOCK_ESP.setEnabled(config.isEnabledByDefault());

        // Default block colors
        blockColors.put(Blocks.DIAMOND_ORE, 0xFF00FFFF);
        blockColors.put(Blocks.EMERALD_ORE, 0xFF00FF00);
        blockColors.put(Blocks.GOLD_ORE, 0xFFFFFF00);
        blockColors.put(Blocks.IRON_ORE, 0xFF808080);
        blockColors.put(Blocks.LAPIS_ORE, 0xFF0000FF);
        blockColors.put(Blocks.REDSTONE_ORE, 0xFFFF0000);
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

        int range = config.getRange();
        BlockPos playerPos = client.player.getBlockPos();

        for (int x = -range; x <= range; x++) {
            for (int y = -range; y <= range; y++) {
                for (int z = -range; z <= range
