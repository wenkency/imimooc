package com.lven.immooc.http;

import static com.lven.immooc.http.AppConfig.getDomain;

public class URLS {
    /**
     * 获取验证码
     */
    public static String getMsgCode() {
        return getDomain() + "/f34e28da5816433d/getMsgCode";
    }
}
