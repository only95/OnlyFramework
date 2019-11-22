package com.only.framework;

import android.app.Application;
import android.support.multidex.MultiDex;

/**
 * ${tags}
 *
 * @Title: ${enclosing_method}
 * @author:wujun
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }
}
