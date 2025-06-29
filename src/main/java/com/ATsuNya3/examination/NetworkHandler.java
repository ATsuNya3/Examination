package com.ATsuNya3.examination;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.ClientPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetworkHandler {
    public static final String MODID = "examination";

    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1.0");

        registrar.playToServer(
                FabledAnimationPayload.TYPE,
                FabledAnimationPayload.STREAM_CODEC,
                createAnimationPayloadHandler()
        );
    }

    private static void handleAnimationPayload(FabledAnimationPayload payload, ClientPayloadContext context) {
        context.enqueueWork(() -> {
            // 在主游戏线程处理数据
            Minecraft mc = Minecraft.getInstance();
            mc.execute(() -> {
                processAnimationData(payload.data());
            });
        });
    }

    private static void processAnimationData(String data) {
        // 在这里处理接收到的动画数据
        System.out.println("Received animation data: " + data);
        try {
            JsonObject json = JsonParser.parseString(data).getAsJsonObject();
            String animName = json.get("animation").getAsString();
            float intensity = json.get("intensity").getAsFloat();
            ClientAnimationHandler.playAnimation(animName,intensity);
        } catch (Exception e) {
            LogUtils.getLogger().error("Invalid animation data: {}", data, e);
        }
    }
    private static IPayloadHandler<FabledAnimationPayload> createAnimationPayloadHandler() {
        return (payload, context) -> {
            // 确保是客户端上下文
            if (context instanceof ClientPayloadContext clientContext) {
                handleAnimationPayload(payload, clientContext);
            } else {
                LOGGER.error("Received non-client context for animation payload: {}", context.getClass());
            }
        };
    }
}
