package com.example.winkey.mydemos.data.model.base;

/**
 * Created by xupg on 2017/3/29.
 */

public class JsonResponse {
    /**
     * 系统返回信息
     */
    public String Message;
    /**
     * 系统返回码
     */
    public String StatusTag;

    public String ActionRequestID;


    /**
     * 判断借口是否可用
     * @return
     */
    public boolean isDone(){
        if(StatusTag!=null){
            return StatusTag.equals("Success");
        }
        return  true;
    }


}
