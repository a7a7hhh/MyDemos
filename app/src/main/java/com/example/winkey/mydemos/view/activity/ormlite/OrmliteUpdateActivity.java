package com.example.winkey.mydemos.view.activity.ormlite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.activity.base.ToolbarActivity;
import com.example.winkey.mydemos.view.fragment.ormlite.OrmliteUpdateFragment;

/**
 * author: Winkey
 * date: 2017/10/9
 * describe: TODO
 */

public class OrmliteUpdateActivity extends ToolbarActivity {

    private OrmliteUpdateFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(R.id.fl_root, mFragment);
        //setContentView(R.layout.activity_main);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, OrmliteUpdateActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getToolbarId() {
        return R.id.toolBar;
    }

    @Override
    protected int getTbTitleId() {
        return R.id.tvTitle;
    }

    @Override
    protected String getTitleStr() {
        return "修改";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_toolbar;
    }



    @Override
    protected void parsentData() {
        mFragment = OrmliteUpdateFragment.newInstance();
    }


}
