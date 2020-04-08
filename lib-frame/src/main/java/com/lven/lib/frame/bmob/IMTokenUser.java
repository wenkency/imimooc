package com.lven.lib.frame.bmob;

public class IMTokenUser {
    public String token;
    public String name;
    public String code;
    public String errorMessage;

    public boolean isSucceed() {
        return "200".equals(code);
    }
}
