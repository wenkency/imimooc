package com.lven.lib.frame;

import android.app.Application;
import android.os.Looper;

import com.lven.lib.bmob.BmobUtils;

import cn.carhouse.utils.ActivityUtils;
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
        // Activity管理类
        ActivityUtils.register(application);
        // Bmob初始化
        BmobUtils.init(application);
    }

    /**
     * 主线程初始化
     */
    private static void mainThreadInit(Application application) {
        // 设置Log打印
        LogUtils.setDebug(BuildConfig.IS_DEBUG);
    }
}
