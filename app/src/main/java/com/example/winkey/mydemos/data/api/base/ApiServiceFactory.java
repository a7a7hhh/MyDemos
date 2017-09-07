package com.example.winkey.mydemos.data.api.base;



import com.example.winkey.mydemos.data.api.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author : xupg
 * @date:2017/3/29
 * @description: TODO
 */

public class ApiServiceFactory {
    private static ApiServiceFactory sInstance;

    private ApiServiceFactory() {

    }

    public static ApiServiceFactory getInstance() {
        if (sInstance == null) {
            synchronized (ApiServiceFactory.class) {
                if (sInstance == null) {
                    sInstance = new ApiServiceFactory();
                }
            }
        }
        return sInstance;
    }
    /**
     *
     * @param baseUrl
     * @return
     */
    public ApiService provideApiService(String baseUrl){
        OkHttpClient.Builder client=provideOkhttpClient();
        Retrofit retrofit=provideRestAdapter(client,baseUrl);
        return retrofit.create(ApiService.class);
    }

    /**
     *
     * @return
     */
    private OkHttpClient.Builder provideOkhttpClient(){
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        client.readTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        //client.addInterceptor(new TokenInterceptor());
        return client;
    }

    /**
     * 提供Retrofit
     *
     * @param client
     * @param baseUrl
     * @return
     */
    private Retrofit provideRestAdapter(OkHttpClient.Builder client, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(client.build())
                .build();
        return retrofit;
    }

}
