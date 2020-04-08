package com.lven.lib.frame.http;


import cn.carhouse.http.core.IObjectCallback;
import cn.carhouse.http.util.GsonUtil;
import cn.carhouse.utils.HandlerUtils;

/**
 * 通用接口解析方式案例，实际如有不用，可以自己继承IObjectCallback解析
 */
public class ObjectCallback implements IObjectCallback {
    private IObjectCallback callback;

    public ObjectCallback(IObjectCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onSuccess(final String json, final Object object, final Class clazz) {
        HandlerUtils.runInMainThread(new Runnable() {
            @Override
            public void run() {
                // 如果是字符串类型
                if (clazz == String.class) {
                    callback.onSuccess(json, json, clazz);
                } else {
                    Object data = GsonUtil.getGson().fromJson(json, clazz);
                    callback.onSuccess(json, data, clazz);
                }

            }
        });

    }

    @Override
    public void onError(final Exception e) {
        HandlerUtils.runInMainThread(new Runnable() {
            @Override
            public void run() {
                callback.onError(e);
            }
        });
    }
}
