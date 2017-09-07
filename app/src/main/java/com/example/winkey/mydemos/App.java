package com.example.winkey.mydemos;

import android.app.Application;

import com.example.winkey.mydemos.view.utils.ToastUtils;

/**
 * Created by Winkey on 2017/9/6.
 */

public class App extends Application {
    private static App instance;

    public ToastUtils toastUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        toastUtils = new ToastUtils(this);
        //setupImageLoader();
    }

    public static App getInstance() {
        return instance;
    }
}
