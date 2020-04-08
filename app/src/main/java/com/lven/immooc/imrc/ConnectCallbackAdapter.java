package com.lven.immooc.imrc;

import cn.carhouse.utils.LogUtils;
import io.rong.imlib.RongIMClient;

public class ConnectCallbackAdapter extends RongIMClient.ConnectCallback {
    @Override
    public void onTokenIncorrect() {
        LogUtils.e("连接失败: token error");
    }

    @Override
    public void onSuccess(String s) {
        LogUtils.e("连接成功: " + s);
    }

    @Override
    public void onError(RongIMClient.ErrorCode errorCode) {
        LogUtils.e("连接失败: " + errorCode);
    }
}
