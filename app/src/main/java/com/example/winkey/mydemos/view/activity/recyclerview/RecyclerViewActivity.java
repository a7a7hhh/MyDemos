package com.example.winkey.mydemos.view.activity.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.activity.base.BaseActivity;
import com.example.winkey.mydemos.view.activity.mta.MtaAccountAcitiviy;
import com.example.winkey.mydemos.view.fragment.mta.MtaAccountFragment;
import com.example.winkey.mydemos.view.fragment.recyclerview.RecyclerViewFragment;

/**
 * author: Winkey
 * date: 2017/9/13
 * describe: TODO
 */

public class RecyclerViewActivity extends BaseActivity {
    private RecyclerViewFragment mFragment;

    public static void start(Context context){
        Intent intent=new Intent(context,RecyclerViewActivity.class);
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
        mFragment = RecyclerViewFragment.newInstance();
    }

    @Override
    protected void initViews() {

    }
}
