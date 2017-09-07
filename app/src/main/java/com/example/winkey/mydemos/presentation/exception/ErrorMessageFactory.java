package com.example.winkey.mydemos.presentation.exception;

import android.content.Context;


import com.example.winkey.mydemos.R;

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

    private ErrorMessageFactory(){
    }

    public static String create(Context context, Exception exception){
        String message=context.getString(R.string.exception_message_generic);
        if (exception instanceof ConnectException) {
            message = context.getString(R.string.exception_message_no_connection);
        } else if (exception instanceof UnknownHostException){
            message = context.getString(R.string.exception_message_host_error);
        } else if (exception instanceof UnknownServiceException){
            message = context.getString(R.string.exception_message_host_error);
        } else if(exception instanceof HttpException){
            message = context.getString(R.string.exception_message_host_error);
        }
        return message;
    }

}
