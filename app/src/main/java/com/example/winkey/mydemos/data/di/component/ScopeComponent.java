package com.example.winkey.mydemos.data.di.component;

import com.example.winkey.mydemos.data.di.scope.PerActivity;
import com.example.winkey.mydemos.data.di.module.ScopeModule;
import com.example.winkey.mydemos.view.fragment.ormlite.OrmliteFragment;

import dagger.Component;

/**
 * author: Winkey
 * date: 2017/12/16.
 * describe: TODO
 */

@PerActivity
@Component(modules = {ScopeModule.class})  // 作为桥梁，沟通调用者和依赖对象库
public interface ScopeComponent {

    void inject(OrmliteFragment fragment);
}