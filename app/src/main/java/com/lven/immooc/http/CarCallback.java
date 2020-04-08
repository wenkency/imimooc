package com.lven.immooc.http;

import com.lven.immooc.http.bean.RespondBean;
import com.lven.lib.frame.http.BeanCallback;

import java.lang.reflect.Type;
import java.util.Map;

import cn.carhouse.http.core.RequestParams;
import cn.carhouse.http.core.RequestType;
import cn.carhouse.http.parse.ParameterTypeUtils;
import cn.carhouse.http.parse.ParameterizedTypeImpl;
import cn.carhouse.http.util.GsonUtil;
import cn.carhouse.utils.HandlerUtils;

public abstract class CarCallback<T> extends BeanCallback<T> {
    @Override
    public void onBefore(RequestParams params, RequestType type) {
        Map<String, String> header = params.getHeaderParams();
        header.put("X-Bmob-Application-Id", AppConfig.getAppId());
        header.put("X-Bmob-REST-API-Key", AppConfig.getAppKey());
    }

    @Override
    public void onSucceed(RequestParams params, String result, boolean isSuccessful, int code) {
        try {
            Type actualType = ParameterTypeUtils.parameterType(this);
            // BaseRspBean 泛型的解析类型
            ParameterizedTypeImpl parseType = new ParameterizedTypeImpl(RespondBean.class, new Type[]{actualType});
            // 1. BaseRspBean: 新接口数据解析 、旧包含Head解析
            RespondBean<T> bean = GsonUtil.getGson().fromJson(result, parseType);
            if (bean.isSucceed()) {
                HandlerUtils.runInMainThread(new Runnable() {
                    @Override
                    public void run() {
                        onSucceed(bean.getData());
                        onAfter();
                    }
                });
            } else {
                throw new RuntimeException(bean.getMessage());
            }
        } catch (Exception e) {
            onError(e);
        }

    }
}
