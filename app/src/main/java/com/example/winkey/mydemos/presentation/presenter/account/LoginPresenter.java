package com.example.winkey.mydemos.presentation.presenter.account;

import android.content.Context;

import com.example.winkey.mydemos.data.api.base.ApiConfig;
import com.example.winkey.mydemos.data.model.vo.account.TokenVO;
import com.example.winkey.mydemos.presentation.base.BasePresenter;
import com.example.winkey.mydemos.presentation.base.CallBackListener;
import com.example.winkey.mydemos.view.utils.Logger;

import okhttp3.ResponseBody;

/**
 * author: Winkey
 * date: 2017/9/8
 * describe: TODO
 */

public class LoginPresenter extends BasePresenter {

    private Context mContext;
    public LoginPresenter(Context context) {
        mContext = context;
    }

    public void login(String userName, String password, CallBackListener<TokenVO> callBackListener) {
        Logger.debug("userName"+userName+";password"+password);
        execute(callBackListener, api().getToken(userName, password, ApiConfig.CLIENT_ID, "password"));
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destory() {

    }
}
