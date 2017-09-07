package com.example.winkey.mydemos.presentation.base;

import android.content.Context;
import android.net.ParseException;
import android.widget.Toast;

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
                Logger.debug("httpexception");
                HttpException exception = (HttpException) e;
                Logger.debug("错误码"+exception.code());
                Logger.debug(exception.response().raw().request().url().toString());
                if (exception.code() == 400) {
                    JSONObject jsonObject = null;
                    if(exception.response().raw().request().url().toString().equals("http://192.168.1.132:5000/connect/token")){
                        try {
                            jsonObject = new JSONObject(exception.response().errorBody().string());
                            Logger.debug(jsonObject.toString());
                            String errorMsg = null;
                            errorMsg = jsonObject.getString("error_description");
                            Logger.debug(errorMsg);
                            Toast.makeText(context(),errorMsg, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }else {
                        try {
                            jsonObject = new JSONObject(exception.response().errorBody().string());
                            String errorMsg = null;
                            errorMsg = jsonObject.getString("Message");
                            Logger.debug(errorMsg);
                            Toast.makeText(context(),errorMsg, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                } else if (exception.code() == 401) {
                    Toast.makeText(context(),exception.code()+"", Toast.LENGTH_SHORT).show();
//                    LoginActivity.start(context());
                } else if (exception.code() == 403) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(exception.response().errorBody().string());
                        String errorMsg = null;
                        errorMsg = jsonObject.getString("Message");
                        Logger.debug(errorMsg);
                        Toast.makeText(context(),errorMsg, Toast.LENGTH_SHORT).show();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if (exception.code() == 500) {
                    Toast.makeText(context(),"服务器错误！", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context(),exception.code()+"", Toast.LENGTH_SHORT).show();
                }
            }else if(e instanceof JsonParseException
                    || e instanceof JSONException
                    || e instanceof ParseException){
                Toast.makeText(context(),"解析错误", Toast.LENGTH_SHORT).show();
            }
//            ErrorBundle errorBundle = new DefaultErrorBundle((Exception) e);
//            String errorMessage = ErrorMessageFactory.create(context(),
//                    errorBundle.getException());
//            showErrorView(errorMessage);
        } else {
            showNetErrorView();
        }
        Logger.debug("********失败回调********");
        e.printStackTrace();
    }

    @Override
    public void onNext(T t) {
        onRender(t);
//        if (t instanceof JsonResponse) {
//            JsonResponse response = (JsonResponse) t;
//            onRender(t);
//        } else {
//            onRender(t);
//        }

    }

    /**
     * 绑定数据
     *
     * @param t
     */
    protected void onRender(T t) {
        getCallBackListener().callBack(t);
    }

    /**
     * 错误信息传递
     *
     * @param msg
     */
    protected void showErrorView(String msg) {
        getPresenter().showErrorView(msg);
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
