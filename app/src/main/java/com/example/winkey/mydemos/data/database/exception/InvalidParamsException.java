package com.example.winkey.mydemos.data.database.exception;

/**
 * @author : xupg
 * @date:2017/3/30
 * @description: TODO
 */

public class InvalidParamsException extends Exception {

    private String exceptionMsg;

    public InvalidParamsException(String exceptionMsg) {
        super(exceptionMsg);
        this.exceptionMsg = exceptionMsg;
    }
}
