package com.ATsuNya3.examination.event;

import com.ATsuNya3.examination.Item.ChangableMessageItem;
import com.ATsuNya3.examination.Item.MessageItem;
import com.ATsuNya3.examination.block.MessageBlock;
import com.ATsuNya3.examination.method.MessageRecorde;
import com.ATsuNya3.examination.network.MyData;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.network.PacketDistributor;


import static com.ATsuNya3.examination.Examination.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.GAME)
public class ForgeEventSubscriber {
    @SubscribeEvent
    public static void ProcessInfo(PlayerInteractEvent.RightClickBlock event){
        Player player = event.getEntity();
        ItemStack stack = player.getMainHandItem();
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);

        if (state.getBlock() instanceof MessageBlock && stack.getItem() instanceof MessageItem){//手持MessageItem类物品的处理
            String message = ((MessageItem) stack.getItem()).getmessage();//获取字符串
            PacketDistributor.sendToAllPlayers(new MyData(message));//发送给所有玩家

        }else if (state.getBlock() instanceof MessageBlock && stack.getItem() instanceof ChangableMessageItem){//手持ChangableMessageItem
            PacketDistributor.sendToAllPlayers(new MyData(ChangableMessageItem.getMessage(stack)));

        }else if (state.getBlock() instanceof MessageBlock && stack.isEmpty()){//空手显示
            event.getEntity().displayClientMessage(
                 Component.literal(MessageRecorde.getLastMessage())
                            .withStyle(ChatFormatting.AQUA),
                     true
             );//显示消息在屏幕上
         }
    }
}
