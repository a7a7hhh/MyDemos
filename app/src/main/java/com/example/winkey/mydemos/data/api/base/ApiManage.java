package com.example.winkey.mydemos.data.api.base;


import com.example.winkey.mydemos.data.api.ApiService;

/**
 * @author : xupg
 * @date:2017/3/29
 * @description: TODO
 */

public class ApiManage {

    private static ApiManage sInstance=null;

    public ApiService mApiService;

    public ApiService mDownloadService;

    public ApiService mApiWithToken;

    private ApiManage(){
        mApiService=ApiServiceFactory.getInstance().provideApiService(ApiConfig.TEST_HOST);
    }

    public static ApiManage getInstance(){
        if(sInstance==null){
            synchronized (ApiManage.class){
                if(sInstance==null){
                    sInstance=new ApiManage();
                }
            }
        }
        return sInstance;
    }

    public ApiService getApiService(){
        return mApiService;
    }

    public ApiService getDownloadService(String downloadUrl){
        if(mDownloadService==null){
            mDownloadService=ApiServiceFactory.getInstance().provideApiService(downloadUrl);
        }
        return mDownloadService;
    }

}
