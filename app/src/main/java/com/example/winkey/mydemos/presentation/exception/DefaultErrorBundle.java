package com.example.winkey.mydemos.presentation.exception;

/**
 * @author : xupg
 * @date:2017/3/30
 * @description: TODO
 */

public class DefaultErrorBundle implements ErrorBundle {
    private static final String DEFAULT_ERROR_MSG = "未知错误";

    private final Exception exception;

    public DefaultErrorBundle(Exception exception) {
        this.exception = exception;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public String getErrorMsg() {
        return (exception != null) ? this.exception.getMessage() : DEFAULT_ERROR_MSG;
    }

}
