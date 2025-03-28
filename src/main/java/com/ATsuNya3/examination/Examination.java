package com.ATsuNya3.examination;

import com.ATsuNya3.examination.command.SetMessageCommand;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

import static com.ATsuNya3.examination.register.BlockRegister.BLOCKS;
import static com.ATsuNya3.examination.register.CreativeTabRegister.CREATIVE_TABS;
import static com.ATsuNya3.examination.register.ItemRegister.ITEMS;

@Mod(Examination.MODID)
public class Examination {
    public static final String MODID = "examination";

    public Examination(IEventBus modEventBus)
    {
        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);
        CREATIVE_TABS.register(modEventBus);

        NeoForge.EVENT_BUS.addListener(SetMessageCommand::registerCommands);
    }
}
