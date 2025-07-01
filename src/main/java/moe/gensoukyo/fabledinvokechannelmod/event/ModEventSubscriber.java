package moe.gensoukyo.fabledinvokechannelmod.event;

import moe.gensoukyo.fabledinvokechannelmod.network.Handle;
import moe.gensoukyo.fabledinvokechannelmod.network.InvokeData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import static moe.gensoukyo.fabledinvokechannelmod.FabledInvokeChannelMod.MODID;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MODID)
public class ModEventSubscriber {
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playBidirectional(
                InvokeData.TYPE,
                InvokeData.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        Handle.ClientPayloadHandler::handleDataOnNetwork,
                        Handle.ServerPayloadHandler::handleDataOnNetwork
                )
        );
    }
}
