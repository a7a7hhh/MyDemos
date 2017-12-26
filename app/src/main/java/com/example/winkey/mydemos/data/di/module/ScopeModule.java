package com.example.winkey.mydemos.data.di.module;

import com.example.winkey.mydemos.data.di.scope.PerActivity;
import com.example.winkey.mydemos.view.utils.Logger;

import dagger.Module;
import dagger.Provides;

/**
 * author: Winkey
 * date: 2017/12/16.
 * describe: TODO
 */
@Module
public class ScopeModule {

    @Provides
    @PerActivity
    String providerScope(){
        Logger.debug("providerScope");
        return "";
    }
}
