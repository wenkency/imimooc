package com.lven.immooc.http.bean;

import android.text.TextUtils;

/**
 * 打车 响应的Bean
 */
public class RespondBean<T> {
    private String msg;
    private String message;
    private String code;
    private T data;
    private boolean sucess;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSucceed() {
        return "200".equals(code) || isSucess();
    }

    public String getMessage() {
        if (!TextUtils.isEmpty(msg)) {
            return msg;
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public boolean isSucess() {
        return sucess;
    }
}
