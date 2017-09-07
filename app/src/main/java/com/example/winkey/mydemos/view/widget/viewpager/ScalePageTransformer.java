package com.example.winkey.mydemos.view.widget.viewpager;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @author : xupg
 * @date:2017/4/10
 * @description: viewpagerScale动画
 */

public class ScalePageTransformer implements ViewPager.PageTransformer {

    private static final float DEFAULT_SCALE_MIN=0.9f;
    private float mMinScale = DEFAULT_SCALE_MIN;
    public static final float DEFAULT_CENTER = 0.5f;

    public ScalePageTransformer(){

    }

    public ScalePageTransformer(float scale){
        mMinScale=scale;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void transformPage(View page, float position) {
        int pageWidth=page.getWidth();
        int pageHeight=page.getHeight();

        page.setPivotX(pageWidth/2);
        page.setPivotY(pageHeight/2);

        if(position<-1){
            page.setScaleX(mMinScale);
            page.setScaleY(mMinScale);
            page.setPivotX(pageWidth);
        }else if(position<=1) {
            if(position<0){
                float scaleFactor = (1 + position) * (1 - mMinScale) + mMinScale;
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);

                page.setPivotX(pageWidth * (DEFAULT_CENTER + (DEFAULT_CENTER * -position)));
            }else {
                float scaleFactor = (1 - position) * (1 - mMinScale) + mMinScale;
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
                page.setPivotX(pageWidth * ((1 - position) * DEFAULT_CENTER));
            }
        }else {
            page.setPivotX(0);
            page.setScaleX(mMinScale);
            page.setScaleY(mMinScale);
        }
    }
}
