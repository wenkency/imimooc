package com.lven.lib.bmob;

import cn.bmob.v3.BmobUser;

/**
 * 聊天用户类
 */
public class IMUser extends BmobUser {
    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * IMToken
     */
    private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "IMUser{" +
                "name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
