package com.example.winkey.mydemos.view.activity.shadow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.activity.base.BaseActivity;
import com.example.winkey.mydemos.view.fragment.shadow.ShadowFragment;

/**
 * author: Winkey
 * date: 2017/9/9
 * describe: TODO
 */

public class ShadowActivity extends BaseActivity {
    private ShadowFragment mFragment;

    public static void start(Context context){
        Intent intent=new Intent(context,ShadowActivity.class);
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
        mFragment = ShadowFragment.newInstance();
    }

    @Override
    protected void initViews() {

    }
}
