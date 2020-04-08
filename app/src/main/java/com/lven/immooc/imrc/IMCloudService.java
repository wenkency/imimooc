package com.lven.immooc.imrc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.lven.lib.bmob.BmobUtils;
import com.lven.lib.imrc.IMCloudUtils;

import cn.carhouse.utils.LogUtils;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

public class IMCloudService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();


        // 1. 去连接融云
        IMCloudUtils.connect(BmobUtils.getToken(), new ConnectCallbackAdapter());

        // 2. 设置消息监听
        IMCloudUtils.setOnReceiveMessageListener(new RongIMClient.OnReceiveDestructionMessageListener() {
            @Override
            public void onReceive(Message message) {
                LogUtils.e("message:" + message.getContent());
            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
