package com.lven.immooc.presenter;

import android.app.Activity;

import com.lven.immooc.http.CarCallback;
import com.lven.immooc.http.URLS;

import cn.carhouse.http.OkHttpPresenter;

public class CarPresenter {
    /**
     * 获取验证码
     */
    public static void getMsgCode(Activity activity, String phone, final CarCallback<Object> callback) {
        OkHttpPresenter.with(activity).get(URLS.getMsgCode(), "phone", phone, callback);
    }
}
