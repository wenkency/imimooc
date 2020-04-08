package com.lven.immooc.http;

import com.lven.lib.frame.http.BeanCallback;
import com.lven.lib.frame.utils.SHA1;
import com.lven.lib.imrc.IMConfig;

import java.util.Map;

import cn.carhouse.http.core.RequestParams;
import cn.carhouse.http.core.RequestType;

public abstract class BmobCallback<T> extends BeanCallback<T> {
    @Override
    public void onBefore(RequestParams params, RequestType type) {
        // 时间戳
        String Timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        // 随机数
        String Nonce = String.valueOf((int) Math.floor(Math.random() * 100000 + Math.round(1000000)));
        // 数据签名:App Secret、Nonce (随机数)、signTimestamp (时间戳)
        String Signature = SHA1.sha1(IMConfig.APP_SECRET + Nonce + Timestamp);
        Map<String, String> headerParams = params.getHeaderParams();
        headerParams.put("Timestamp", Timestamp);
        headerParams.put("App-Key", IMConfig.APP_KEY);
        headerParams.put("Nonce", Nonce);
        headerParams.put("Signature", Signature);
    }

}
