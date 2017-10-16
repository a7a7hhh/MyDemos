package com.example.winkey.mydemos.view.activity.ormlite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.activity.base.ToolbarActivity;
import com.example.winkey.mydemos.view.fragment.ormlite.OrmliteDeleteFragment;

/**
 * author: Winkey
 * date: 2017/10/9
 * describe: TODO
 */

public class OrmliteDeleteActivity extends ToolbarActivity {
    private OrmliteDeleteFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(R.id.fl_root, mFragment);
        //setContentView(R.layout.activity_main);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, OrmliteDeleteActivity.class);
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
        return "删除";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_toolbar;
    }



    @Override
    protected void parsentData() {
        mFragment = OrmliteDeleteFragment.newInstance();
    }


}
