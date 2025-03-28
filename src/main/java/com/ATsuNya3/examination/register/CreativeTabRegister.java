package com.ATsuNya3.examination.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.ATsuNya3.examination.Examination.MODID;

public class CreativeTabRegister {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab,CreativeModeTab> MODE = CREATIVE_TABS.register("creativetab"
            , () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemgroup.examination.exmainationtab"))
                    .icon(() -> new ItemStack(ItemRegister.INFO.get()))
                    .displayItems((params, output) -> {
                        output.accept(ItemRegister.Message1.get());
                        output.accept(ItemRegister.Message2.get());
                        output.accept(ItemRegister.Message3.get());
                        output.accept(ItemRegister.Message4.get());
                        output.accept(ItemRegister.Message5.get());
                        output.accept(ItemRegister.INFO.get());
                        output.accept(ItemRegister.MessageRecorder_ITEM.get());
                    }).build());
}
