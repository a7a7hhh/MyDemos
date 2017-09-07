package com.example.winkey.mydemos.view.widget.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * @author : xupg
 * @date:2017/4/11
 * @description: TODO
 */

public class RecyclerViewPager extends LinearLayout {

    private PagerAdapter adapter;

    public RecyclerViewPager(Context context) {
        super(context);
    }

    public RecyclerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPagerAdapter(PagerAdapter adapter){
        this.adapter=adapter;
    }

    private void init(){

    }
}
