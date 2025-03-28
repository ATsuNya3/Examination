package com.ATsuNya3.examination.command;

import com.ATsuNya3.examination.Item.ChangableMessageItem;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class SetMessageCommand {//设置命令 在创造模式修改 通过/itemdata setmessage “字符串”  来设置
    public static void registerCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("itemdata")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.literal("setmessage")
                                .then(Commands.argument("message", StringArgumentType.greedyString())
                                            .executes(context -> setMessage(context))
                                )
                        )
        );
    }
    private static int setMessage(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String message = StringArgumentType.getString(context, "message");
        ServerPlayer player = context.getSource().getPlayerOrException();
        ItemStack stack = player.getMainHandItem();


        if (stack.getItem() instanceof ChangableMessageItem) {
            ChangableMessageItem.setMessage(stack, message);
            player.containerMenu.broadcastChanges();
            context.getSource().sendSuccess(() -> Component.literal("已经设置消息为: " + message), true);
        } else {
            context.getSource().sendFailure(Component.literal("请手持自定义物品"));
        }
        return 0;
    }
}

