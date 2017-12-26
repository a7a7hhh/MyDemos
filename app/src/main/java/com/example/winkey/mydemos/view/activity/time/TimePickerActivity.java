package com.example.winkey.mydemos.view.activity.time;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.activity.base.BaseActivity;
import com.example.winkey.mydemos.view.activity.ormlite.OrmliteActivity;
import com.example.winkey.mydemos.view.fragment.ormlite.OrmliteFragment;
import com.example.winkey.mydemos.view.fragment.time.TimePickerFragment;

/**
 * author: Winkey
 * date: 2017/12/22.
 * describe: TODO
 */

public class TimePickerActivity extends BaseActivity{

    private TimePickerFragment mFragment;


    public static void start(Context context){
        Intent intent=new Intent(context,TimePickerActivity.class);
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
        mFragment = TimePickerFragment.newInstance();
    }

    @Override
    protected void initViews() {

    }
}
