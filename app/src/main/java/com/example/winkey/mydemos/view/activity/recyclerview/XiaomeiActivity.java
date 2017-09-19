package com.example.winkey.mydemos.view.activity.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.activity.base.ToolbarActivity;
import com.example.winkey.mydemos.view.fragment.recyclerview.XiaomeiFragment;

/**
 * author: Winkey
 * date: 2017/9/13
 * describe: TODO
 */

public class XiaomeiActivity extends ToolbarActivity {

    private XiaomeiFragment mFragment;

    public static void start(Context context) {
        Intent intent = new Intent(context, XiaomeiActivity.class);
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
        return getString(R.string.recycler_view_xiaomei);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_toolbar;
    }

    @Override
    protected void parsentData() {
        mFragment = XiaomeiFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(R.id.fl_root, mFragment);
    }
}
