package com.ATsuNya3.examination.event;

import com.ATsuNya3.examination.network.Handle;
import com.ATsuNya3.examination.network.MyData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import static com.ATsuNya3.examination.Examination.MODID;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MODID)
public class ModEventSubscriber {
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playBidirectional(
                MyData.TYPE,
                MyData.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        Handle.ClientPayloadHandler::handleDataOnNetwork,
                        Handle.ServerPayloadHandler::handleDataOnNetwork
                )
        );
    }
}
