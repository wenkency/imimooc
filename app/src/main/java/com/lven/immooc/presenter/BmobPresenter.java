package com.lven.immooc.presenter;

import android.app.Activity;

import com.lven.immooc.http.BmobCallback;
import com.lven.lib.bmob.IMUser;
import com.lven.lib.frame.bmob.IMTokenUser;
import com.lven.lib.frame.bmob.TokenBean;
import com.lven.lib.frame.http.BeanCallback;

import cn.carhouse.http.HttpLog;
import cn.carhouse.http.OkHttpPresenter;
import cn.carhouse.utils.TSUtils;

public class BmobPresenter {
    /**
     * 关联Bmob用户和融云IM的Token
     *
     * @param activity
     * @param imUser
     */
    public static void getToken(Activity activity, final IMUser imUser, final BeanCallback<IMTokenUser> callback) {
        HttpLog.setIsDebug(true);
        // 请求
        final String url = "https://api-cn.ronghub.com/user/getToken.json";
        TokenBean bean = new TokenBean(imUser.getObjectId(), imUser.getName(), imUser.getAvatar());
        OkHttpPresenter.with(activity).form(url, bean, new BmobCallback<IMTokenUser>() {
            @Override
            public void onSucceed(IMTokenUser user) {
                if (user.isSucceed()) {
                    callback.onSucceed(user);
                } else {
                    TSUtils.show(user.errorMessage);
                }
            }
        });
    }
}
