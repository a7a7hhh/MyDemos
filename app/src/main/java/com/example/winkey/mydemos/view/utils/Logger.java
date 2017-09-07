package com.example.winkey.mydemos.view.utils;

import android.util.Log;

import com.example.winkey.mydemos.BuildConfig;


/**
 * /**
 *
 * @author : xupg
 * @date:2017/3/30
 * @description: TODO
 */
public class Logger {
    public static boolean IS_DEBUG = BuildConfig.IS_DEBUG;
    public static boolean SHOW_ACTIVITY_STATE = true;

    public Logger() {
    }


    public static final void debug(String msg) {
        if (IS_DEBUG) {
            Log.i("debug", msg);
        }

    }

    public static final void state(String packName, String state) {
        if (SHOW_ACTIVITY_STATE) {
            Log.d("activity_state", packName + state);
        }

    }


    public static final void debug(String msg, Object... format) {
        debug(String.format(msg, format));
    }
}
