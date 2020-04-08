package com.lven.immooc.http;

public class AppConfig {
    private static final String TEST_DOMAIN = "http://cloud.bmob.cn";
    private static final String RElEASE_DOMAIN = "http://cloud.bmob.cn";
    private static final String TEST_APP_ID = "e90928398db0130b0d6d21da7bde357e";
    private static final String RELEASE_APP_ID = "e90928398db0130b0d6d21da7bde357e";
    private static final String TEST_APP_KEY = "514d8f8a2371bdf1566033f6664a24d2";
    private static final String RELEASE_APP_KEY = "514d8f8a2371bdf1566033f6664a24d2";
    private static String appId = TEST_APP_ID;
    private static String appKey = TEST_APP_KEY;
    private static String domain = TEST_DOMAIN;

    public static void setDebug(boolean debug) {
        domain = debug ? TEST_DOMAIN : RElEASE_DOMAIN;
        appId = debug ? TEST_APP_ID : RELEASE_APP_ID;
        appKey = debug ? TEST_APP_KEY : RELEASE_APP_KEY;
    }

    public static String getDomain() {
        return domain;
    }

    public static String getAppId() {
        return appId;
    }

    public static String getAppKey() {
        return appKey;
    }


}
