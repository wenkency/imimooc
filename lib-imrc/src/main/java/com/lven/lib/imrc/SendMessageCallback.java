package com.lven.lib.imrc;

import android.util.Log;

import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

public class SendMessageCallback implements IRongCallback.ISendMessageCallback {
    @Override
    public void onAttached(Message message) {
        // 消息成功存到本地数据库
        Log.e("SendMessageCallback","onAttached:"+message.getContent());
    }

    @Override
    public void onSuccess(Message message) {
        // 发送消息成功
        Log.e("SendMessageCallback","onSuccess:"+message.getContent());
    }

    @Override
    public void onError(Message message, RongIMClient.ErrorCode errorCode) {
        // 发送消息失败
        Log.e("SendMessageCallback","onError:"+errorCode);
    }
}
