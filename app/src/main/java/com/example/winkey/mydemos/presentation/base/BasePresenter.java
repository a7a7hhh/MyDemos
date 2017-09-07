package com.example.winkey.mydemos.presentation.base;

import android.content.Context;


import com.example.winkey.mydemos.App;
import com.example.winkey.mydemos.data.DataManage;
import com.example.winkey.mydemos.data.api.ApiService;
import com.example.winkey.mydemos.data.api.base.ApiManage;
import com.example.winkey.mydemos.view.load.LoadDataView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * @author : xupg
 * @date:2017/3/30
 * @description: TODO
 */

public abstract class BasePresenter implements Presenter {

    private List<LoadDataView> loadDataViewList;

    /**
     * 获取Subscriber
     *
     * @param callbackListener
     * @return
     */
    private BaseSubscriber<?> getSubscriber(CallBackListener<?> callbackListener) {
        return SubscriberFactory.getInstance().createSubscribe(callbackListener, this);
    }

    /**
     * 显示加载视图
     */
    protected void showLoading() {
        if(loadDataViewList == null){
            return;
        }
        for (LoadDataView loadDataView : loadDataViewList) {
            loadDataView.showLoading();
        }
    }

    /**
     * 隐藏加载视图
     */
    protected void hideLoading() {
        if(loadDataViewList == null){
            return;
        }
        for (LoadDataView loadDataView : loadDataViewList) {
            loadDataView.hideLoading();
        }
    }

    /**
     * 显示错误视图
     *
     * @param msg
     */
    protected void showErrorView(String msg) {
        if(loadDataViewList == null){
            return;
        }
        for (LoadDataView loadDataView : loadDataViewList) {
            loadDataView.showErrorView(msg);
        }
    }

    /**
     * 显示空视图
     */
    protected void showEmptyView() {
        if(loadDataViewList == null){
            return;
        }
        for (LoadDataView loadDataView : loadDataViewList) {
            loadDataView.showEmptyView();
        }
    }

    /**
     * 显示网络错误视图
     */
    protected void showNetWorkView() {
        if(loadDataViewList == null){
            return;
        }
        for (LoadDataView loadDataView : loadDataViewList) {
            loadDataView.showNetWorkView();
        }
    }

    protected Context getContext() {
        return App.getInstance();
    }

    /**
     * @param callbackListener
     * @param observable
     */
    protected void execute(CallBackListener<?> callbackListener, Observable<?> observable) {
        getDataManage().execute(getSubscriber(callbackListener), observable);
    }

    /**
     * 获取ApiService
     *
     * @return
     */
    protected ApiService api() {
        return ApiManage.getInstance().getApiService();
    }

    /**
     * 获取DownloadApiService
     *
     * @return
     */
    protected ApiService downloadFile(String url) {
        return ApiManage.getInstance().getDownloadService(url);
    }

    /**
     * 获取DataManage
     *
     * @return
     */
    private DataManage getDataManage() {
        return DataManage.getInstance();
    }

    /**
     * 注册
     *
     * @param loadDataView
     */
    @Override
    public void register(LoadDataView loadDataView) {
        if (loadDataViewList == null) {
            loadDataViewList = new ArrayList<>();
        }

        loadDataViewList.add(loadDataView);
    }

    /**
     * 注销
     *
     * @param loadDataView
     */
    @Override
    public void unregister(LoadDataView loadDataView) {
        if (loadDataViewList != null) {
            loadDataViewList.remove(loadDataView);
        }
    }


}
