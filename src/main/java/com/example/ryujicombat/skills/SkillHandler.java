package com.example.ryujicombat.skills;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class SkillHandler {
    private static boolean hasDoubleJumped = false;
    private static int dashCooldown = 0;
    private static int slideCooldown = 0;

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                handleDash(client.player);
                handleDoubleJump(client.player);
                handleSlide(client.player);
                handleParry(client.player);
            }
        });
    }

    private static void handleDash(PlayerEntity player) {
        if (dashCooldown > 0) dashCooldown--;
        var mc = MinecraftClient.getInstance();
        if (mc.options.sprintKey.isPressed() &&
            mc.options.sneakKey.isPressed() &&
            dashCooldown == 0) {
            Vec3d vec = player.getRotationVec(1.0F).multiply(2.0D);
            player.addVelocity(vec.x, 0, vec.z);
            dashCooldown = 40;
        }
    }

    private static void handleDoubleJump(PlayerEntity player) {
        var mc = MinecraftClient.getInstance();
        if (player.isOnGround()) hasDoubleJumped = false;
        else if (!hasDoubleJumped && mc.options.jumpKey.wasPressed()) {
            player.addVelocity(0, 0.6, 0);
            hasDoubleJumped = true;
        }
    }

    private static void handleSlide(PlayerEntity player) {
        if (slideCooldown > 0) slideCooldown--;
        var mc = MinecraftClient.getInstance();
        if (mc.options.sprintKey.isPressed() &&
            mc.options.sneakKey.wasPressed() &&
            slideCooldown == 0 &&
            player.isOnGround()) {
            Vec3d vec = player.getRotationVec(1.0F).multiply(1.5);
            player.addVelocity(vec.x, -0.1, vec.z);
            slideCooldown = 40;
        }
    }

    private static void handleParry(PlayerEntity player) {
        var mc = MinecraftClient.getInstance();
        if (mc.options.useKey.isPressed() && mc.options.sneakKey.isPressed()) {
            player.world.getOtherEntities(player, player.getBoundingBox().expand(2.0D), e -> e != player)
                .forEach(entity -> {
                    Vec3d knock = entity.getPos().subtract(player.getPos()).normalize().multiply(1.5);
                    entity.addVelocity(knock.x, 0.3, knock.z);
                });
        }
    }
}