package com.example.leak_canary_demo;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * @author pikachu
 * @time 2016/7/14 9:39
 * @desc
 */
public class AppData extends Application {

    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        mRefWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        AppData application = (AppData) context.getApplicationContext();
        return application.mRefWatcher;
    }

}
