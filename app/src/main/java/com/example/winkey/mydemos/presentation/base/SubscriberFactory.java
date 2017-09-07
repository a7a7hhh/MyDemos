package com.example.winkey.mydemos.presentation.base;

/**
 * @author : xupg
 * @date:2017/3/30
 * @description: TODO
 */

public class SubscriberFactory<T> {

    private static volatile SubscriberFactory sInstance;

    private SubscriberFactory(){

    }

    public static SubscriberFactory getInstance(){
        if(sInstance==null){
            synchronized (SubscriberFactory.class){
                if(sInstance==null){
                    sInstance=new SubscriberFactory();
                }
            }
        }
        return sInstance;
    }

    public BaseSubscriber createSubscribe(CallBackListener<T> listener, BasePresenter presenter){
        BaseSubscriber<T> subscriber = BaseSubscriber.builder()
                .callback(listener)
                .presenter(presenter)
                .build();
        return subscriber;
    }

}
