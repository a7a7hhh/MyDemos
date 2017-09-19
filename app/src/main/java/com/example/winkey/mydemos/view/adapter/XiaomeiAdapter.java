package com.example.winkey.mydemos.view.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.utils.DeviceUtils;
import com.example.winkey.mydemos.view.utils.Logger;
import com.example.winkey.mydemos.view.widget.recyclerview.adapter.CommonAdapter;
import com.example.winkey.mydemos.view.widget.recyclerview.adapter.ViewHolder;

import java.util.List;

/**
 * author: Winkey
 * date: 2017/9/13
 * describe: TODO
 */

public class XiaomeiAdapter extends CommonAdapter {

    private Context mContext;

    public XiaomeiAdapter(Context context, List datas) {
        super(context, R.layout.item_xiaomei, datas);
        mContext = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void convert(ViewHolder holder, Object o, int position) {
        GridLayout functionGrid = holder.getView(R.id.gl_picture);
        Logger.debug("xwb");
        for (int i = 0; i < 8; i++) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_xiaomei_picture, null);
            //使用Spec定义子控件的位置和比重
            GridLayout.Spec rowSpec = GridLayout.spec(i / 3, 1f);
            GridLayout.Spec columnSpec = GridLayout.spec(i % 3, 1f);
            //将Spec传入GridLayout.LayoutParams并设置宽高为0，必须设置宽高，否则视图异常
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);
            layoutParams.height = 0;
            layoutParams.width = 0;
            //还可以根据位置动态定义子控件直接的边距，下面的意思是
            //第一行的子控件都有2dp的bottomMargin，中间位置的子控件都有2dp的leftMargin和rightMargin
            layoutParams.leftMargin = DeviceUtils.dpToPx(mContext, 5);
            layoutParams.topMargin = DeviceUtils.dpToPx(mContext, 5);
            functionGrid.addView(view, layoutParams);
        }
    }

}
