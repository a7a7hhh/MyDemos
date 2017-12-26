package com.example.winkey.mydemos.data.di.module;

import android.app.Application;

import com.example.winkey.mydemos.data.di.Person;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * author: Winkey
 * date: 2017/12/16.
 * describe: TODO
 */

@Module   //提供依赖对象的实例
public class MainModule {

    Application mApplication;
    public MainModule(Application application) {
        mApplication = application;
    }
    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
        // 关键字，标明该方法提供依赖对象
    Person providerPerson(){
        //提供Person对象
        return new Person();
    }


}
