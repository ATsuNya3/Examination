package com.ATsuNya3.examination.network;

import com.ATsuNya3.examination.method.MessageRecorde;
import net.neoforged.neoforge.network.handling.IPayloadContext;



public class Handle {
    public static class ClientPayloadHandler {
        public static void handleDataOnNetwork(final MyData data, final IPayloadContext context) {
            MessageRecorde.recordMessage(data.message());//记录最后一次被发送的消息
        }
    }

    public static class ServerPayloadHandler{
        public static void handleDataOnNetwork(final MyData data, final IPayloadContext context) {
        }
    }
}
