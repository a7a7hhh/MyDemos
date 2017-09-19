package com.example.winkey.mydemos.view.widget.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

/**
 * @author : xupg
 * @date:2017/4/11
 * @description: TODO
 */

public class FlowLayout extends ViewGroup {

    private List<SqureLayout> view;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode= MeasureSpec.getMode(widthMeasureSpec);
        int heightMode= MeasureSpec.getMode(heightMeasureSpec);

        int widthSize= MeasureSpec.getSize(widthMeasureSpec);
        int heightSize= MeasureSpec.getSize(heightMeasureSpec);

        int width=0;
        int height=0;
        int cCount=getChildCount();

        for(int i=0;i<cCount;i++){
            View child=getChildAt(i);
            if(child.getVisibility()==GONE)
                continue;
            MarginLayoutParams lp = (MarginLayoutParams) child
                    .getLayoutParams();

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    public void addView(View view){

    }
}
