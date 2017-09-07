package com.example.winkey.mydemos.presentation.base;


import com.example.winkey.mydemos.view.load.LoadDataView;

/**
 * @author : xupg
 * @date:2017/3/30
 * @description: TODO
 */

public interface Presenter {
    /**
     * 注册
     * @param loadDataView
     */
    void register(LoadDataView loadDataView);

    /**
     * 注销
     * @param loadDataView
     */
    void unregister(LoadDataView loadDataView);

    /**
     * 在Activity/Fragment的onResume()方法调用
     */
    void resume();

    /**
     * 在Activity/Fragment的onPause()方法调用
     */
    void pause();

    /**
     * 在Activity/Fragment的onDestory()方法调用
     */
    void destory();
}
