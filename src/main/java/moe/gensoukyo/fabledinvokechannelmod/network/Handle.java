package moe.gensoukyo.fabledinvokechannelmod.network;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;



public class Handle {
    public static class ClientPayloadHandler {
        public static void handleDataOnNetwork(final InvokeData data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                Player player = context.player();
                if (player != null) {
                    // 在客户端聊天栏显示消息
                    player.sendSystemMessage(Component.literal("[Examination] " + data.message()));
                }
            });
        }
    }

    public static class ServerPayloadHandler{
        public static void handleDataOnNetwork(final InvokeData data, final IPayloadContext context) {
        }
    }
}
