package com.example.winkey.mydemos.presentation.manage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.winkey.mydemos.data.model.vo.account.TokenVO;
import com.example.winkey.mydemos.view.utils.DateUtils;

/**
 * author: Winkey
 * date: 2017/9/12
 * describe: TODO
 */

public class TokenSharedPreference {
    private static SharedPreferences sp;

    private static String filename = "TokenVO";
    /**
     * 保存token
     *
     * @param ctx
     * @param tokenVO
     */
    public static void saveToken(Context ctx, TokenVO tokenVO) {
        if (sp == null) {
            sp = ctx.getSharedPreferences(TokenSharedPreference.filename,
                    Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("token", tokenVO.access_token);
        edit.putString("tokenType", tokenVO.token_type);
        edit.putString("refreshToken", tokenVO.refresh_token);
        edit.putLong("saveTime", DateUtils.getCurrentTimeMillis());
        edit.commit();
    }

    public static String getToken(Context ctx) {
        if (sp == null) {
            sp = ctx.getSharedPreferences(TokenSharedPreference.filename,
                    Context.MODE_PRIVATE);
        }
        return sp.getString("token", "");
    }
    public static String getTokenType(Context ctx) {
        if (sp == null) {
            sp = ctx.getSharedPreferences(TokenSharedPreference.filename,
                    Context.MODE_PRIVATE);
        }
        return sp.getString("tokenType", "");
    }
}
