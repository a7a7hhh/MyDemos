package com.example.winkey.mydemos.view.activity.mta;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.activity.base.BaseActivity;
import com.example.winkey.mydemos.view.fragment.mta.MtaAccountFragment;
import com.example.winkey.mydemos.view.fragment.mta.MtaEventFragment;

/**
 * author: Winkey
 * date: 2017/9/9
 * describe: TODO
 */

public class MtaEventActivity extends BaseActivity {
    private MtaEventFragment mFragment;

    public static void start(Context context){
        Intent intent=new Intent(context,MtaEventActivity.class);
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
        mFragment = MtaEventFragment.newInstance();
    }

    @Override
    protected void initViews() {

    }
}
