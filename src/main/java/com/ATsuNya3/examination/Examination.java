package com.ATsuNya3.examination;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

import static com.ATsuNya3.examination.NetworkHandler.MODID;

@Mod(MODID)
public class Examination {

    public Examination(IEventBus modEventBus) {
        modEventBus.register(NetworkHandler.class);
        NeoForge.EVENT_BUS.register(NetworkHandler.class);
    }
}

