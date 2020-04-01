package com.lven.lib.bmob;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

public class BmobBean extends BmobObject implements Serializable {
    private String name;
    // 性别：0女 1男
    private int sex;

    public BmobBean() {
    }

    public BmobBean(String name, int sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "BmobBean{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }
}
