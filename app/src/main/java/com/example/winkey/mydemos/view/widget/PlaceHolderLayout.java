package com.example.winkey.mydemos.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;


import com.example.winkey.mydemos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author : xupg
 * @date:2017/3/30
 * @description: 占位布局
 */

public class PlaceHolderLayout extends FrameLayout {

    @BindView(R.id.iLoading)
    View iLoading;
    @BindView(R.id.cLoading)
    View cLoading;
    @BindView(R.id.iEmpty)
    View iEmpty;
    @BindView(R.id.iNetwork)
    View iNetwork;
    @BindView(R.id.iError)
    View iError;

    RetryListener retryListener;


    public PlaceHolderLayout(Context context) {
        super(context);
        initViews(context);
    }

    public PlaceHolderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public PlaceHolderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.univ_place_holder, null);
        ButterKnife.bind(this,view);
        hideAll();
        setVisibility(VISIBLE);
        addView(view);
    }


    /**
     * 隐藏全部视图
     */
    public void hideAll() {
        this.setVisibility(GONE);
        iLoading.setVisibility(GONE);
        iEmpty.setVisibility(GONE);
        iNetwork.setVisibility(GONE);
        iError.setVisibility(GONE);
//        cLoading.stopRefresh();
    }

    /**
     * 显示加载视图
     */
    public void showLoadingView() {
        this.setVisibility(VISIBLE);
        iLoading.setVisibility(VISIBLE);
        iEmpty.setVisibility(GONE);
        iNetwork.setVisibility(GONE);
        iError.setVisibility(GONE);
//        cLoading.onRefresh(0);
    }

    /**
     * 隐藏加载视图
     */
    public void hideLoadingView() {
        if(!iEmpty.isShown()&&!iNetwork.isShown()&&!iError.isShown()){
            this.setVisibility(GONE);
        }
        iLoading.setVisibility(GONE);
//        cLoading.stopRefresh();
    }

    /**
     * 显示空视图
     */
    public void showEmptyView() {
        this.setVisibility(VISIBLE);
        iLoading.setVisibility(GONE);
        iEmpty.setVisibility(VISIBLE);
        iNetwork.setVisibility(GONE);
        iError.setVisibility(GONE);
    }

    /**
     * 显示网络错误视图
     */
    public void showNetWorkView() {
        this.setVisibility(VISIBLE);
        iLoading.setVisibility(GONE);
        iEmpty.setVisibility(GONE);
        iNetwork.setVisibility(VISIBLE);
        iError.setVisibility(GONE);
    }

    /**
     * 显示错误视图
     */
    public void showErrorView() {
        this.setVisibility(VISIBLE);
        iLoading.setVisibility(GONE);
        iEmpty.setVisibility(GONE);
        iNetwork.setVisibility(GONE);
        iError.setVisibility(VISIBLE);
    }

    public void setRetryListener(RetryListener listener) {
        this.retryListener = listener;
    }

    @OnClick({R.id.iNetwork,R.id.iError})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iError:
            case R.id.iNetwork:
                retryListener.retry();
                break;
        }
    }

//    private class onClick implements OnClickListener{
//
//        @Override
//        public void onClick(View v) {
//            int i = v.getId();
//            if (i == R.id.iNetwork || i == R.id.iError) {
//
//
//            }
//        }
//    }

    public interface RetryListener {
        void retry();
    }
}
