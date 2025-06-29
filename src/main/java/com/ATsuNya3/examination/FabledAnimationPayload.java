package com.ATsuNya3.examination;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record FabledAnimationPayload(String data) implements CustomPacketPayload {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath("fabled", "animation_channel");
    public static final Type<FabledAnimationPayload> TYPE = new Type<>(ID);
    // 解码器（从字节流读取）
    public static final StreamCodec<RegistryFriendlyByteBuf, FabledAnimationPayload> STREAM_CODEC = StreamCodec.of(
            (buf, payload) -> buf.writeUtf(payload.data, 32767),
            buf -> new FabledAnimationPayload(buf.readUtf(32767))
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
