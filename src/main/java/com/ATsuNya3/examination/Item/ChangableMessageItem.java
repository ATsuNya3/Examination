package com.ATsuNya3.examination.Item;

import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;

import java.util.List;

public class ChangableMessageItem extends Item {
    public ChangableMessageItem (Properties pProperties){
        super(pProperties);
    }

    public static void setMessage(ItemStack stack, String message) {
        CompoundTag tag = new CompoundTag();
        tag.putString("message", message);

        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }//设置字符串标签

    public static String getMessage(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            CompoundTag tag = customData.copyTag();
            return tag.getString("message");
        }
        return "默认消息";
    }//获取ChangableMessageItem的信息

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.examination.info.desc"));
    }//用于添加文本描述
}

