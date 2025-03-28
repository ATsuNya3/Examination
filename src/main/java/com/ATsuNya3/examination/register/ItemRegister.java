package com.ATsuNya3.examination.register;

import com.ATsuNya3.examination.Item.ChangableMessageItem;
import com.ATsuNya3.examination.Item.MessageItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.ATsuNya3.examination.Examination.MODID;

public class ItemRegister {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(MODID);

    public static final Supplier<Item> Message1 = ITEMS.register("message1", () -> new MessageItem(new Item.Properties(),"信息1"));//注册五个物品带有字符串常量
    public static final Supplier<Item> Message2 = ITEMS.register("message2", () -> new MessageItem(new Item.Properties(),"这里是信息2"));
    public static final Supplier<Item> Message3 = ITEMS.register("message3", () -> new MessageItem(new Item.Properties(),"第三个信息"));
    public static final Supplier<Item> Message4 = ITEMS.register("message4", () -> new MessageItem(new Item.Properties(),"四信息"));
    public static final Supplier<Item> Message5 = ITEMS.register("message5", () -> new MessageItem(new Item.Properties(),"ausdhiuashudasuidsahuidashuida"));

    public static final Supplier<Item> INFO = ITEMS.register("info", () -> new ChangableMessageItem(new Item.Properties()));// 题 3

    public static final Supplier<Item> MessageRecorder_ITEM = ITEMS.register("messagerecorder",() -> new BlockItem(BlockRegister.MessageRecorder.get(), new Item.Properties()));
}
