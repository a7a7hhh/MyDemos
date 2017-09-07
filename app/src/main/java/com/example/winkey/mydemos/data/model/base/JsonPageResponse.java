package com.example.winkey.mydemos.data.model.base;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xupg on 2017/3/29.
 */

public class JsonPageResponse<T> extends JsonResponse {

    @SerializedName("data")
    public Data<T> datas;


    public static class Data<T>{
        /**总条数*/
        @SerializedName("total")
        public int total;
        @SerializedName("rows")
        public List<T> rows;

    }

    /**获取List<T>数据*/
    public List<T> getListData() {
        if (datas != null) {
            return datas.rows;
        }
        return null;
    }
}
