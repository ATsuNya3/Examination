package com.ATsuNya3.examination.Item;

import net.minecraft.world.item.Item;

public class MessageItem extends Item {
    public final String message;

    public MessageItem (Properties pProperties,String message){
        super(pProperties);
        this.message=message;
    }
    public String getmessage() {
        return message;
    }//获取MessageItem中的message
}
