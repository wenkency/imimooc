package com.lven.immooc.app;

import android.app.Application;
import android.os.Looper;

import com.lven.immooc.http.AppConfig;
import com.lven.lib.bmob.BmobUtils;
import com.lven.lib.frame.BuildConfig;
import com.lven.lib.imrc.IMCloudUtils;

import cn.carhouse.http.HttpLog;
import cn.carhouse.utils.ActivityUtils;
import cn.carhouse.utils.ContextUtils;
import cn.carhouse.utils.LogUtils;
import cn.carhouse.utils.ThreadUtils;

public class AppInit {
    /**
     * 初始化应用
     */
    public static void init(final Application application) {
        if (application == null) {
            new RuntimeException("application in null");
            return;
        }
        if (!ContextUtils.isMainProcess()) {
            return;
        }
        // 1. 子线程初始化
        ThreadUtils.getNormalPool().execute(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                doBackgroundInit(application);
                Looper.loop();
            }
        });
        // 2. 主线程初始化
        mainThreadInit(application);


    }


    /**
     * 子线程初始化
     */
    private static void doBackgroundInit(Application application) {
        // Bmob初始化
        BmobUtils.init(application);
        // 融云初始化
        IMCloudUtils.init(application);
    }

    /**
     * 主线程初始化
     */
    private static void mainThreadInit(Application application) {
        // Activity管理类
        ActivityUtils.register(application);
        // 设置Log打印
        LogUtils.setDebug(BuildConfig.IS_DEBUG);
        HttpLog.setIsDebug(BuildConfig.IS_DEBUG);

        AppConfig.setDebug(false);
    }
}
