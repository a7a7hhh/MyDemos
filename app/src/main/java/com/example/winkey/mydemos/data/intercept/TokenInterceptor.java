package com.example.winkey.mydemos.data.intercept;

import android.text.TextUtils;

import com.example.winkey.mydemos.App;
import com.example.winkey.mydemos.presentation.manage.TokenSharedPreference;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author: Winkey
 * date: 2017/9/12
 * describe: TODO
 */

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if ( TextUtils.isEmpty(TokenSharedPreference.getToken(App.getInstance())) ) {//表示第一次登陆还没拉取过token
            return chain.proceed(request);//执行登陆的操作
        }
        Request authorised = request.newBuilder()
                .addHeader("Authorization",TokenSharedPreference.getTokenType(App.getInstance())+" "+TokenSharedPreference.getToken(App.getInstance()))
                .method(request.method(), request.body())
                .build();
        return chain.proceed(authorised);
    }
}
