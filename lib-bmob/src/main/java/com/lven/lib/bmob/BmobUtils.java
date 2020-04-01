package com.lven.lib.bmob;

import android.content.Context;

import cn.bmob.v3.Bmob;

/**
 * Bmob工具类
 */
public class BmobUtils {
    private static String appKey = "ade511435cd8be19e15944412f9b30ff";

    public static void init(Context context) {
        //第一：默认初始化
        Bmob.initialize(context, appKey);
    }
}
