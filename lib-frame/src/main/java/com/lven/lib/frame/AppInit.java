package com.lven.lib.frame;

import android.app.Application;

import com.lven.lib.bmob.BmobUtils;

import cn.carhouse.utils.ActivityUtils;
import cn.carhouse.utils.LogUtils;

public class AppInit {
    /**
     * 初始化应用
     */
    public static void init(Application application) {
        if (application == null) {
            new RuntimeException("application in null");
            return;
        }
        // Bmob初始化
        BmobUtils.init(application);
        // Activity管理类
        ActivityUtils.register(application);
        // 设置Log打印
        LogUtils.setDebug(BuildConfig.IS_DEBUG);

    }
}
