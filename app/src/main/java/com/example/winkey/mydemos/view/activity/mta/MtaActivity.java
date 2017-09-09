package com.example.winkey.mydemos.view.activity.mta;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.activity.base.BaseActivity;
import com.example.winkey.mydemos.view.fragment.MainFragment;
import com.example.winkey.mydemos.view.fragment.mta.MtaFragment;

/**
 * author: Winkey
 * date: 2017/9/9
 * describe: TODO
 */

public class MtaActivity extends BaseActivity {

    private MtaFragment mFragment;

    public static void start(Context context){
        Intent intent=new Intent(context,MtaActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(R.id.fl_root,mFragment);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base;
    }

    @Override
    protected void parsentData() {
        mFragment = MtaFragment.newInstance();
    }

    @Override
    protected void initViews() {

    }
}
