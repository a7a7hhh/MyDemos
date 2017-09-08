package com.example.winkey.mydemos.view.activity.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.activity.base.BaseActivity;
import com.example.winkey.mydemos.view.fragment.account.LoginFragment;

/**
 * Created by Winkey on 2017/9/8.
 */

public class LoginActivity extends BaseActivity {

    private LoginFragment mFragment;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(R.id.fl_root, mFragment);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base;
    }

    @Override
    protected void parsentData() {
        mFragment=LoginFragment.newInstance();
    }

    @Override
    protected void initViews() {

    }
}
