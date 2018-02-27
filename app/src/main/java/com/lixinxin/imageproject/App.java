package com.lixinxin.imageproject;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by android on 2018/2/27.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
