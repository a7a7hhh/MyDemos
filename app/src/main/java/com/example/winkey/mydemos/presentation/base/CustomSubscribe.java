package com.example.winkey.mydemos.presentation.base;

import android.content.Context;
import android.net.ParseException;
import android.widget.Toast;

import com.example.winkey.mydemos.data.model.base.JsonObjectResponse;
import com.example.winkey.mydemos.data.model.base.JsonPageResponse;
import com.example.winkey.mydemos.data.model.base.JsonResponse;
import com.example.winkey.mydemos.presentation.exception.DefaultErrorBundle;
import com.example.winkey.mydemos.presentation.exception.ErrorBundle;
import com.example.winkey.mydemos.presentation.exception.ErrorMessageFactory;
import com.example.winkey.mydemos.view.utils.Logger;
import com.example.winkey.mydemos.view.utils.SystemTool;
import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * @author : xupg
 * @date:2017/3/30
 * @description: TODO
 */

public class CustomSubscribe<T> extends Subscriber<T> {

    private BasePresenter presenter;
    private CallBackListener<T> callBackListener;

    /**
     * 加载框
     */
    @Override
    public void onStart() {
        super.onStart();
        getPresenter().showLoading();
    }

    @Override
    public void onCompleted() {
        getPresenter().hideLoading();
    }

    @Override
    public void onError(Throwable e) {
        onCompleted();
        Logger.debug("********失败回调********");
        if (SystemTool.checkNet(context())) {
            if (e instanceof HttpException) {
                ErrorBundle errorBundle = new DefaultErrorBundle((Exception) e);
                ErrorMessageFactory.ErrorMessage errorMessage = ErrorMessageFactory.create(context(),
                        errorBundle.getException());
                showErrorView(errorMessage.message, errorMessage.code);
            } else {
                showNetErrorView();
            }
            Logger.debug("********失败回调********");
            e.printStackTrace();
        }
    }

    /**
     * 绑定数据
     *
     * @param t
     */

    @Override
    public void onNext (T t){
        if (t instanceof JsonResponse) {
            JsonResponse response = (JsonResponse) t;
            if (response.isDone()) {
                if (t instanceof JsonObjectResponse) {
                    onRender(t);
                } else if (t instanceof JsonPageResponse) {
                    onRender(t);
                }
            }
        } else {
            onRender(t);
        }
    }



    protected void onRender(T t) {
        getCallBackListener().callBack(t);
    }

    protected void showErrorView(String msg, int errorCode) {
        getPresenter().showErrorView(msg, errorCode);
    }


    /**
     * 网络错误
     */
    protected void showNetErrorView() {
        getPresenter().showNetWorkView();
    }


    /**
     * 显示空视图
     */
    protected void showEmptyView() {
        getPresenter().showEmptyView();
    }

    /**
     * @return
     */
    protected Context context() {
        return getPresenter().getContext();
    }

    /**
     * 设置getPresenter()
     *
     * @param presenter
     * @return
     */
    protected void setPresenter(BasePresenter presenter) {
        this.presenter = presenter;
    }


    public void setCallBackListener(CallBackListener<T> callBackListener) {
        this.callBackListener = callBackListener;
    }

    private BasePresenter getPresenter() {
        try {
            if (presenter == null) {
                throw new Exception("未设置presenter");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return presenter;
    }

    private CallBackListener<T> getCallBackListener() {
        try {
            if (callBackListener == null) {
                throw new Exception("回调函数为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return callBackListener;
    }


}
