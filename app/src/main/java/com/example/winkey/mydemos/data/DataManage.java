package com.example.winkey.mydemos.data;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xupg on 2017/3/29.
 */

public class DataManage {

    private static DataManage sInstance=null;

    private DataManage(){

    }

    public static DataManage getInstance(){
        if(sInstance==null){
            synchronized (DataManage.class){
                if(sInstance==null){
                    sInstance=new DataManage();
                }
            }
        }
        return sInstance;
    }

    public void execute(Subscriber subscriber, Observable<?> observable){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
