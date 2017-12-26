package com.example.winkey.mydemos.data.di.component;

import com.example.winkey.mydemos.data.di.module.MainModule;
import com.example.winkey.mydemos.data.di.module.NetModule;
import com.example.winkey.mydemos.view.activity.MainActivity;
import com.example.winkey.mydemos.view.fragment.MainFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * author: Winkey
 * date: 2017/12/16.
 * describe: TODO
 */
@Singleton
@Component(modules = {MainModule.class,NetModule.class})  // 作为桥梁，沟通调用者和依赖对象库
public interface MainComponent {

    //定义注入的方法
    void inject(MainActivity activity);
    void inject(MainFragment fragment);
}