package com.lven.lib.imrc;

import android.content.Context;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.message.TextMessage;

public class IMCloudUtils {
    /**
     * 初始化 SDK，在整个应用程序全局只需要调用一次, 建议在Application继承类中调用。
     */
    public static void init(Context context) {
        RongIMClient.init(context, IMConfig.APP_KEY);
    }

    /**
     * 连接融云
     */
    public static void connect(String token, RongIMClient.ConnectCallback callback) {
        RongIMClient.connect(token, callback);
    }

    /**
     * 在断开和融云的连接后，有新消息时，仍然能够收到推送通知，调用 disconnect() 方法。
     */
    public static void disconnect() {
        RongIMClient.getInstance().disconnect();
    }

    /**
     * 断开连接后，不想收到任何推送通知，调用 logout() 方法。
     */
    public static void logout() {
        RongIMClient.getInstance().logout();
    }

    /**
     * 接收消息的监听器
     *
     * @param listener
     */
    public static void setOnReceiveMessageListener(RongIMClient.OnReceiveDestructionMessageListener listener) {
        RongIMClient.getInstance().setOnReceiveDestructionMessageListener(listener);
    }

    public static void sendTextMassage(String msg, String targetId) {
        TextMessage textMessage = TextMessage.obtain(msg);
        RongIMClient.getInstance().sendMessage(Conversation.ConversationType.PRIVATE,
                targetId,
                textMessage,
                null,
                null,
                new SendMessageCallback());
    }
}
