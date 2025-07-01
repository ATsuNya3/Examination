package moe.gensoukyo.fabledinvokechannelmod.event;

import moe.gensoukyo.fabledinvokechannelmod.network.InvokeData;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.network.PacketDistributor;


import static moe.gensoukyo.fabledinvokechannelmod.FabledInvokeChannelMod.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.GAME)
public class ForgeEventSubscriber {
    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("exammsg")
                .requires(source -> source.hasPermission(2))
                .then(Commands.argument("message", StringArgumentType.greedyString())
                        .executes(context -> {
                            String msg = StringArgumentType.getString(context, "message");
                            ServerPlayer sender = context.getSource().getPlayer();
                            if (sender != null) {
                                // 只发送给命令执行者自己
                                sendToSelf(sender, msg);
                            } else {
                                context.getSource().sendFailure(Component.literal("只有玩家才能使用此命令"));
                            }
                            return 1;
                        })
                )
        );
    }
    public static void sendToSelf(ServerPlayer sender, String message) {
        InvokeData data = new InvokeData(message);
        PacketDistributor.sendToPlayer(sender, data);
    }
}
