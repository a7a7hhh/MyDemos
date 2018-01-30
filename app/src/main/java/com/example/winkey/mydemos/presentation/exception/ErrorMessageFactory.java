package com.example.winkey.mydemos.presentation.exception;

import android.content.Context;


import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.data.api.base.ApiConfig;
import com.example.winkey.mydemos.view.utils.Logger;

import org.json.JSONObject;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * @author : xupg
 * @date:2017/3/30
 * @description: TODO
 */

public class ErrorMessageFactory {

    private ErrorMessageFactory() {
    }

    public static ErrorMessage create(Context context, Exception e) {
        ErrorMessage errorMessage = new ErrorMessage();
        String message = context.getString(R.string.exception_message_generic);
        if (e instanceof ConnectException) {
            message = context.getString(R.string.exception_message_no_connection);
            errorMessage.code = 500;
        } else if (e instanceof UnknownHostException) {
            message = context.getString(R.string.exception_message_host_error);
            errorMessage.code = 500;
        } else if (e instanceof UnknownServiceException) {
            message = context.getString(R.string.exception_message_host_error);
            errorMessage.code = 500;
        } else if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;
            errorMessage.code = exception.code();
            Logger.debug(exception.response().raw().request().url().toString());
            if (exception.code() == 400) {
                JSONObject jsonObject = null;
                if (exception.response().raw().request().url().toString().equals((ApiConfig.TEST_HOST) + "/account/connect/token")) {
                    try {
                        jsonObject = new JSONObject(exception.response().errorBody().string());
                        Logger.debug(jsonObject.toString());
                        message = TokenError.Unknow.getErrorMsg(jsonObject.getString("error")).toString();
                        Logger.debug(message);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        jsonObject = new JSONObject(exception.response().errorBody().string());
                        message = jsonObject.getString("Message");
                        Logger.debug(message);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            } else if (exception.code() == 401) {
                message = exception.code() + "";
            } else if (exception.code() == 403) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(exception.response().errorBody().string());
                    message = jsonObject.getString("Message");
                    Logger.debug(message);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else if (exception.code() == 500) {
                message = context.getString(R.string.exception_message_host_error);
            } else {
                message = context.getString(R.string.exception_message_host_error);
                Logger.debug(exception.code() + "");
            }
        } else {
            Logger.debug(e.getMessage());
            message = context.getString(R.string.exception_message_host_error);
            errorMessage.code = 0;
        }
        errorMessage.message = message;
        return errorMessage;
    }

    public static class ErrorMessage {
        public String message;
        public int code;
    }

    public enum TokenError {
        Unknow("error"),
        无效的请求("invalid_request"),
        无效的客户端("invalid_client"),
        无效的用户名或密码("invalid_grant"),
        未授权的客户端("unauthorized_client"),
        不支持的授权类型("unsupported_grant_type"),
        无效的权限("invalid_scope");

        TokenError(String error) {
        }

        public TokenError getErrorMsg(String s) {
            TokenError error = null;
            switch (s) {
                case "invalid_request":
                    error = 无效的请求;
                    break;
                case "invalid_client":
                    error = 无效的客户端;
                    break;
                case "invalid_grant":
                    error = 无效的用户名或密码;
                    break;
                case "unauthorized_client":
                    error = 未授权的客户端;
                    break;
                case "unsupported_grant_type":
                    error = 不支持的授权类型;
                    break;
                case "invalid_scope":
                    error = 无效的权限;
                    break;
            }
            return error;
        }
    }
}
