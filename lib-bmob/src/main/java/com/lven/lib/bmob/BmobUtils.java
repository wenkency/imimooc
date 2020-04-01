package com.lven.lib.bmob;

import android.content.Context;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * Bmob工具类
 */
public class BmobUtils {
    private static String appKey = "ade511435cd8be19e15944412f9b30ff";

    public static void init(Context context) {
        //第一：默认初始化
        Bmob.initialize(context, appKey);
    }

    /**
     * 发送验证码
     */
    public static void requestSMSCode(String phone, QueryListener<Integer> listener) {
        BmobSMS.requestSMSCode(phone, "", listener);
    }

    /**
     * 注册或者登录
     */
    public static void signOrLoginByMobilePhone(String phone, String smsCode, LogInListener<IMUser> listener) {
        BmobUser.signOrLoginByMobilePhone(phone, smsCode, listener);
    }

    /**
     * 获取当前登录用户
     */
    public static IMUser getIMUser() {
        return BmobUser.getCurrentUser(IMUser.class);
    }

    /**
     * 判断用户是否登录
     */
    public static boolean isLogin() {
        return BmobUser.isLogin();
    }
}
