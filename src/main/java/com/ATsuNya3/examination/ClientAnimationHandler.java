package com.ATsuNya3.examination;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ClientAnimationHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Map<String, BiConsumer<Player, Float>> ANIMATIONS = new HashMap<>();

    static {
        // 注册动画处理器
        ANIMATIONS.put("sword_slash", ClientAnimationHandler::playSwordSlash);
        ANIMATIONS.put("magic_blast", ClientAnimationHandler::playMagicBlast);
        ANIMATIONS.put("healing_aura", ClientAnimationHandler::playHealingAura);
    }

    public static void playAnimation(String animName, float intensity) {
        Minecraft.getInstance().execute(() -> { Player player = Minecraft.getInstance().player;
        if (player == null) return;

        BiConsumer<Player, Float> animation = ANIMATIONS.get(animName);
        if (animation != null) {
            animation.accept(player, intensity);
        } else {
            LOGGER.warn("Unknown animation type: {}", animName);
        }});
    }

    private static void playSwordSlash(Player player, float intensity) {
        // 播放音效
        player.playSound(SoundEvents.PLAYER_ATTACK_SWEEP, 1.0f, 0.8f + intensity * 0.4f);

        // 创建粒子效果
        ClientLevel level = (ClientLevel) player.level();
        for (int i = 0; i < (int)(10 * intensity); i++) {
            double angle = Math.toRadians(player.getYRot() + i * 36);
            double distance = 1.0 + i * 0.1;

            double x = player.getX() + Math.sin(angle) * distance;
            double z = player.getZ() + Math.cos(angle) * distance;
            double y = player.getY() + 1.0;

            level.addParticle(ParticleTypes.SWEEP_ATTACK,
                    x, y, z,
                    0, 0, 0);
        }
    }

    private static void playMagicBlast(Player player, float intensity) {
        // 播放音效
        player.playSound(SoundEvents.ILLUSIONER_CAST_SPELL, 1.0f, 1.0f / intensity);

        // 创建魔法粒子
        ClientLevel level = (ClientLevel) player.level();
        for (int i = 0; i < (int)(20 * intensity); i++) {
            double theta = level.random.nextDouble() * Math.PI * 2;
            double phi = level.random.nextDouble() * Math.PI;
            double r = intensity * (0.5 + level.random.nextDouble());

            double x = player.getX() + r * Math.sin(phi) * Math.cos(theta);
            double y = player.getY() + 1.5 + r * Math.cos(phi);
            double z = player.getZ() + r * Math.sin(phi) * Math.sin(theta);

            level.addParticle(ParticleTypes.ENCHANT,
                    x, y, z,
                    0, 0.1, 0);
        }
    }

    private static void playHealingAura(Player player, float intensity) {
        // 播放音效
        player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.7f, 1.2f);

        // 创建治疗粒子
        ClientLevel level = (ClientLevel) player.level();
        for (int i = 0; i < (int)(15 * intensity); i++) {
            double angle = level.random.nextDouble() * Math.PI * 2;
            double radius = 1.0 + level.random.nextDouble();
            double height = 0.5 + level.random.nextDouble() * 1.5;

            double x = player.getX() + Math.cos(angle) * radius;
            double z = player.getZ() + Math.sin(angle) * radius;
            double y = player.getY() + height;

            level.addParticle(ParticleTypes.HEART,
                    x, y, z,
                    0, 0.1, 0);
        }
    }
}
