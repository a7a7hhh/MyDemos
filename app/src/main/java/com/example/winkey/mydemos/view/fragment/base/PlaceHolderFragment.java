package com.example.winkey.mydemos.view.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.winkey.mydemos.view.load.LoadDataView;
import com.example.winkey.mydemos.view.widget.PlaceHolderLayout;


/**
 * @author : xupg
 * @date:2017/3/30
 * @description: TODO
 */

public abstract class PlaceHolderFragment extends BaseFragment implements LoadDataView,PlaceHolderLayout.RetryListener{

    /**
     * 占位视图
     */
    private PlaceHolderLayout placeholder;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initPaleceHolder(view);
        if (getPlaceHolderId() != 0) {
            placeholder.setRetryListener(this);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 初始化占位视图
     *
     * @param view
     */
    protected void initPaleceHolder(View view) {
        if (getPlaceHolderId() != 0) {
            placeholder = (PlaceHolderLayout) view.findViewById(getPlaceHolderId());
        }

    }

    protected abstract int getPlaceHolderId();

    /**
     * 显示加载中视图
     */
    @Override
    public void showLoading() {
        if (placeholder != null)
            placeholder.showLoadingView();
    }

    /**
     * 显示错误视图
     *
     * @param message
     */
    @Override
    public void showErrorView(String message, int messagecode) {
        if (placeholder != null)
            placeholder.showErrorView();
    }

    /**
     * 显示空视图
     */
    @Override
    public void showEmptyView() {
        if (placeholder != null)
            placeholder.showEmptyView();
    }

    /**
     * 显示网络错误视图
     */
    @Override
    public void showNetWorkView() {
        if (placeholder != null)
            placeholder.showNetWorkView();
    }

    /**
     * 隐藏加载中视图
     */
    @Override
    public void hideLoading() {
        if (placeholder != null)
            placeholder.hideLoadingView();
    }

    @Override
    public Context context() {
        return getActivity();
    }


}
