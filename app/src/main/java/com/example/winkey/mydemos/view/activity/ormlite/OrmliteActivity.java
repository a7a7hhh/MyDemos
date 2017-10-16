package com.example.winkey.mydemos.view.activity.ormlite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.activity.base.BaseActivity;
import com.example.winkey.mydemos.view.fragment.ormlite.OrmliteFragment;

/**
 * author: Winkey
 * date: 2017/10/9
 * describe: TODO
 */

public class OrmliteActivity extends BaseActivity {

    private OrmliteFragment mFragment;


    public static void start(Context context){
        Intent intent=new Intent(context,OrmliteActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(R.id.fl_root,mFragment);
        //setContentView(R.layout.activity_main);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base;
    }

    @Override
    protected void parsentData() {
        mFragment = OrmliteFragment.newInstance();
    }

    @Override
    protected void initViews() {

    }
}
