package com.lven.lib.frame;

import android.app.Application;

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppInit.init(this);
    }
}
