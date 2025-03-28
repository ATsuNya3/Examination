package com.ATsuNya3.examination.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record MyData(String message) implements CustomPacketPayload {//注册负载
    public static final Type<MyData> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("examination", "my_data"));

    public static final StreamCodec<ByteBuf, MyData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            MyData::message,
            MyData::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
