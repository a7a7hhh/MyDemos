package com.example.winkey.mydemos.data.api;



import com.example.winkey.mydemos.data.model.vo.account.TokenVO;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by xupg on 2017/3/29.
 */

public interface ApiService {

    /**
     * 获取token
     *
     * @param userName
     * @param password
     * @return
     */
    @FormUrlEncoded
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @POST("account/connect/token")
    Observable<TokenVO> getToken(@Field("username") String userName, @Field("password") String password, @Field("client_id") String clientId, @Field("grant_type") String grantType);

}
