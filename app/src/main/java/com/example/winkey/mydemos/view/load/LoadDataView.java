package com.example.winkey.mydemos.view.load;

import android.content.Context;

/**
 * @author : xupg
 * @date:2017/3/30
 * @description: TODO
 */

public interface LoadDataView {

    /**
     * 显示加载进度
     */
    void showLoading();

    /**
     * 隐藏加载进度
     */
    void hideLoading();

    /**
     * 显示错误视图
     */
    void showErrorView(String message);

    /**
     * 显示空视图
     */
    void showEmptyView();

    /**
     * 显示网络错误视图
     */
    void showNetWorkView();


    /**
     * @return
     */
    Context context();

}
