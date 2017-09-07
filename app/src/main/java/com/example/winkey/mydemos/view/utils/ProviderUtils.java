package com.example.winkey.mydemos.view.utils;

import android.content.Context;

/**
 * @author : xupg
 * @date:2017/7/27
 * @description: TODO
 */

public class ProviderUtils {

    public static String getFileProviderName(Context context) {
        return context.getPackageName()+".provider";
    }

}
