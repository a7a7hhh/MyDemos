package com.example.winkey.mydemos.view.widget.viewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.winkey.mydemos.R;


/**
 * @author : xupg
 * @date:2017/4/11
 * @description: 广告banner指示器
 */

public class DotView extends View {

    private Context mContext;
    private Paint mPaintSelected;
    private Paint mPaintUnSelected;
    private static int DEFAULT_SELECTED_COLOR = Color.RED;
    private static int DEFAULT_UNSELECTED_COLOR = Color.GRAY;
    private int mCount =3;
    private int mWidth = 30;
    private int mHeight = 6;
    private int dotMargin=20;
    private int selectedPosition=1;
    private int radius=8;

    public DotView(Context context) {
        this(context,null);
    }

    public DotView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttr(context,attrs);
        init(context);
    }

    private void parseAttr(Context context, AttributeSet attrs){
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.DotView);
        array.recycle();
    }

    private void init(Context context){
        mContext=context;
        mPaintSelected=new Paint();
        mPaintUnSelected=new Paint();
        mPaintSelected.setAntiAlias(true);
        mPaintUnSelected.setAntiAlias(true);
        mPaintSelected.setColor(DEFAULT_SELECTED_COLOR);
        mPaintUnSelected.setColor(DEFAULT_UNSELECTED_COLOR);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode= MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode= MeasureSpec.getMode(heightMeasureSpec);

        int widthSpecSize= MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize= MeasureSpec.getSize(heightMeasureSpec);

        if(widthMeasureSpec== MeasureSpec.EXACTLY){

        }else if(widthMeasureSpec== MeasureSpec.AT_MOST){

        }
        setMeasuredDimension(mWidth*mCount+(dotMargin*(mCount-1)),mHeight);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        drawRect(canvas,selectedPosition);
    }

    /**
     * 风格位长型
     * @param canvas
     * @param position
     */
    private void drawRect(Canvas canvas, int position){
        for(int i=1;i<=mCount;i++){
            RectF rect=new RectF((i-1)*(mWidth+dotMargin),0,i*mWidth+(i-1)*dotMargin,mHeight);
            if(i!=position){
                canvas.drawRoundRect(rect,3,3,mPaintUnSelected);
            }else {
                canvas.drawRoundRect(rect,3,3,mPaintSelected);
            }
        }
    }

    /**
     * 风格为点型
     * @param canvas
     */
    private void drawCicle(Canvas canvas, int position){
        for(int i=0;i<mCount;i++){
            if(i!=position){
                canvas.drawCircle(dotMargin*(i),0,radius,mPaintUnSelected);
            }else {
                canvas.drawCircle(dotMargin*(i),0,radius,mPaintSelected);
            }
        }
    }

    public void setCount(int count){
        mCount=count;
        invalidate();
    }

    public void selected(int position){
        selectedPosition=position;
        invalidate();
    }

}
