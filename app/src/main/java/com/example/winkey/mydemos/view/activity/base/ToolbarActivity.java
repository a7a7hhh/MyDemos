package com.example.winkey.mydemos.view.activity.base;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;


/**
 * @author : xupg
 * @date:2017/3/30
 * @description: TODO
 */

public abstract class ToolbarActivity extends BaseActivity {
    protected Toolbar toolbar;
    protected TextView tbTitle;

    @Override
    protected void initViews() {
        setupToolbar();
    }

    protected void setupToolbar(){
        if(getToolbarId()!=0){
            toolbar = (Toolbar)findViewById(getToolbarId());
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        if(getTbTitleId()!=0){
            tbTitle = (TextView)findViewById(getTbTitleId());
            if(!TextUtils.isEmpty(getTitleStr()))
                tbTitle.setText(getTitleStr());
        }


    }

    protected abstract int getToolbarId();
    protected abstract int getTbTitleId();
    protected abstract String getTitleStr();

}
