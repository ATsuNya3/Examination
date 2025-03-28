package com.ATsuNya3.examination.method;

public class MessageRecorde {
    private static String lastMessage = "?";//“L”初始值

    public static void recordMessage(String message) {//记录和获取最后一次发送的消息
        lastMessage = message;
    }

    public static String getLastMessage() {
        return lastMessage;
    }
}
