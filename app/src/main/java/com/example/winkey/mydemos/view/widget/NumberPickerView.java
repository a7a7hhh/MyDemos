package com.example.winkey.mydemos.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.DecelerateInterpolator;
import android.widget.OverScroller;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.utils.Logger;

import java.util.Arrays;

/**
 * author: Winkey
 * date: 2017/12/22.
 * describe: TODO
 */

public class NumberPickerView extends View {

    private Paint mTextPaint;//用于显示文字的画笔


    private int mMinValue;//最小值

    private int mMaxValue;//最大值

    private int mPageSize;//显示的个数

    private int mItemHeight;//组件的高度

    @DimenRes
    private int mMajorTextSize;//主要字体大小
    @DimenRes
    private int mMinorTextSize;//次要字体大小
    @DimenRes
    private int mOptionTextSize;//备选字体大小

    private int mLastTouchY;//最后一次触摸的Y值


    private int mActivePointerId;//如果是多点触控，选择控制的点

    private Object[] mSelector;//显示内容的数组


    private int mTouchSlop;//最小的滑动距离


    @ColorInt
    private int mMajorTextColor;//主要文字的颜色

    @ColorInt
    private int mMinorTextColor;//次要文字的颜色

    @ColorInt
    private int mOptionTextColor;//备选文字的颜色


    private OnValueChanged mCallbackRef;

    private Rect itemRect;

    private int defaultValue = 0;
    private OverScroller mOverScroller;

    private int oldPos;

    public NumberPickerView(Context context) {
        super(context);
        init(context, null);
    }

    public NumberPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public NumberPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.NumberPickerView);
        mMajorTextSize = (int) array.getDimension(R.styleable.NumberPickerView_majorTextSize, 80);
        mMinorTextSize = (int) array.getDimension(R.styleable.NumberPickerView_minorTextSize, 60);
        mOptionTextSize = (int) array.getDimension(R.styleable.NumberPickerView_optionTextSize, 60);
        mMaxValue = array.getInt(R.styleable.NumberPickerView_maxValue, 0);
        mMinValue = array.getInt(R.styleable.NumberPickerView_minValue, 0);
        mPageSize = array.getInt(R.styleable.NumberPickerView_numberPageSize, 5);
        mMajorTextColor = array.getColor(R.styleable.NumberPickerView_majorTextColor, Color.GREEN);
        mMinorTextColor = array.getColor(R.styleable.NumberPickerView_minorTextColor, Color.RED);
        mOptionTextColor = array.getColor(R.styleable.NumberPickerView_optionTextColor, Color.GRAY);
        array.recycle();

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mMajorTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setStyle(Paint.Style.STROKE);

        if (mMinValue < mMaxValue) {
            mSelector = new Object[mMaxValue - mMinValue + 1];
            for (int selectorIndex = mMinValue; selectorIndex <= mMaxValue; selectorIndex++) {
                mSelector[selectorIndex - mMinValue] = selectorIndex;
            }
        }
        mOverScroller = new OverScroller(context, new DecelerateInterpolator());
        itemRect = new Rect();
        defaultValue = mPageSize / 2;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if ((mSelector == null || mSelector.length < 1))
            return;

        int width = getWidth();
        int height = mItemHeight;

        int itemHeight = getItemHeight();
        int textHeight = computeTextHeight();
        int centerY = getScrollY() + height / 2;

        int selectedPos = computePosition();
        for (int itemIndex = defaultValue; itemIndex < (mSelector.length + defaultValue); itemIndex++) {

            itemRect.set(0, itemIndex * itemHeight, width, itemIndex * itemHeight + itemHeight);

            if (itemIndex == selectedPos) {
                mTextPaint.setColor(mMajorTextColor);
                mTextPaint.setTextSize(mMajorTextSize);
            } else if (itemIndex == selectedPos - 1 || itemIndex == selectedPos + 1) {
                mTextPaint.setColor(mMinorTextColor);
                mTextPaint.setTextSize(mMinorTextSize);
            } else {
                mTextPaint.setColor(mOptionTextColor);
                mTextPaint.setTextSize(mOptionTextSize);
            }

            canvas.save();
            int y = (itemRect.top + itemRect.bottom - textHeight) / 2;

            canvas.drawText(mSelector[itemIndex - defaultValue] + "", itemRect.width() / 2 , y, mTextPaint);
            canvas.restore();
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        ViewGroup.LayoutParams lp = getLayoutParams();
        if (lp == null)
            lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        int width = calculateSize(getSuggestedMinimumWidth(), lp.width, widthMeasureSpec);
        int height = calculateSize(getSuggestedMinimumHeight(), lp.height, heightMeasureSpec);

        width += getPaddingLeft() + getPaddingRight();
        height += getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            mItemHeight = getHeight();
        }
    }

    /**
     * @param suggestedSize 最合适的大小
     * @param paramSize     配置的大小
     */
    private int calculateSize(int suggestedSize, int paramSize, int measureSpec) {
        int result = 0;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);

        switch (MeasureSpec.getMode(mode)) {
            case MeasureSpec.AT_MOST:
                if (paramSize == ViewGroup.LayoutParams.WRAP_CONTENT)
                    result = Math.min(suggestedSize, size);
                else if (paramSize == ViewGroup.LayoutParams.MATCH_PARENT)
                    result = size;
                else {
                    result = Math.min(paramSize, size);
                }
                break;
            case MeasureSpec.EXACTLY:
                result = size;
                break;
            case MeasureSpec.UNSPECIFIED:
                if (paramSize == ViewGroup.LayoutParams.WRAP_CONTENT || paramSize == ViewGroup.LayoutParams.MATCH_PARENT)
                    result = suggestedSize;
                else {
                    result = paramSize;
                }
                break;
        }

        return result;
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        int suggested = super.getSuggestedMinimumHeight();
        if (mSelector != null && mSelector.length > 0 && mPageSize > 0) {
            Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
            int height = fontMetricsInt.descent - fontMetricsInt.ascent;//文字高度
            suggested = Math.max(suggested, height * mPageSize);
        }

        return suggested;
    }


    @Override
    protected int getSuggestedMinimumWidth() {
        int suggested = super.getSuggestedMinimumWidth();
        if (mSelector != null && mSelector.length > 0 && mPageSize > 0) {
            suggested = Math.max(suggested, computeMaximumWidth());
        }

        return suggested;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();

        ViewParent parent = getParent();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (!mOverScroller.isFinished())
                    mOverScroller.abortAnimation();

                mActivePointerId = event.getPointerId(0);

                mLastTouchY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = (int) (mLastTouchY - event.getY(mActivePointerId));
                if (parent != null)
                    parent.requestDisallowInterceptTouchEvent(true);//禁止事件拦截

                if (canScroll(deltaY))
                    scrollBy(0, deltaY);
                mLastTouchY = (int) event.getY();


                break;
            case MotionEvent.ACTION_UP:


                if (parent != null)
                    parent.requestDisallowInterceptTouchEvent(false);
                adjustItem();

                break;

            case MotionEvent.ACTION_CANCEL:

                adjustItem();
                break;
        }
        return true;
    }


    private void invalidateOnAnimation() {
        if (Build.VERSION.SDK_INT >= 16)
            postInvalidateOnAnimation();
        else
            invalidate();
    }


    /**
     * 获取一个item位置，通过滚动正好将这个item放置在中间
     */
    private Rect getLocationByPosition(int position) {
        int scrollY = position * getItemHeight() + getMinimumScrollY();
        return new Rect(0, scrollY, getWidth(), scrollY + getItemHeight());
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //判断mOverScroller是否执行完毕
        if (mOverScroller.computeScrollOffset()) {
            int x = mOverScroller.getCurrX();
            int y = mOverScroller.getCurrY();
            scrollTo(x, y);
            //通过重绘来不断调用computeScroll
            invalidate();
        }
    }

    public void smoothScrollTo(final int position) {
        if (position < 0 || mSelector == null || position > mSelector.length)
            return;
        Rect actualLoc = getLocationByPosition(position);
        int scrollY = actualLoc.top - getScrollY();
        mOverScroller.startScroll(getScrollX(), getScrollY(), 0, scrollY);
        invalidateOnAnimation();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        int position = computePosition();
        if (position != oldPos)
            if (mCallbackRef != null) {
                mCallbackRef.onValueChanged(this, position - defaultValue, mSelector[position - defaultValue]);
                oldPos = position;
            }

    }


    /**
     * 调整item使对齐居中
     */
    private void adjustItem() {
        int position = computePosition();
        Rect rect = getLocationByPosition(position);
        int scrollY = rect.top - getScrollY();

        if (scrollY != 0) {
            mOverScroller.startScroll(getScrollX(), getScrollY(), 0, scrollY);
            postInvalidateOnAnimation();
        }
    }


    private int computePosition(int offset) {
        int topOffset = mItemHeight / 2;
        int scrollY = getScrollY() + topOffset + offset;
        return scrollY / getItemHeight();
    }


    /**
     * 计算当前显示的位置
     *
     * @return
     */
    public int computePosition() {
        return computePosition(0);
    }


    public void setSelector(Object[] args) {
        mSelector = args;
        postInvalidate();
    }


    public void setValues(int minValue, int maxValue) {
        this.mMinValue = minValue;
        this.mMaxValue = maxValue;
        if (mMinValue < mMaxValue) {
            mSelector = new Object[mMaxValue - mMinValue + 1];
            for (int selectorIndex = mMinValue; selectorIndex <= mMaxValue; selectorIndex++) {
                mSelector[selectorIndex - mMinValue] = selectorIndex;
            }
        }
        invalidate();
    }

    public int getMinValues() {
        return mMinValue;
    }

    public int getMaxValue() {
        return mMaxValue;
    }

    public void setOffset(int offset) {
        scrollBy(0, offset * getItemHeight());
        invalidate();
    }


    private boolean canScroll(int deltaY) {
        int scrollY = getScrollY() + deltaY;
        int top = getMinimumScrollY();
        int bottom = getMaximumScrollY();
        return scrollY > 0 && scrollY < getItemHeight() * (mSelector.length + 1 - defaultValue);
    }


    private int getMaximumScrollY() {
        return (mSelector.length - 1) * getItemHeight() + getMinimumScrollY();
    }

    private int getMinimumScrollY() {
        return -((mItemHeight - getItemHeight()) / 2);
    }


    /**
     * 计算可视区域内每个item的高度
     *
     * @return
     */
    public int getItemHeight() {
        return mItemHeight / mPageSize;
    }

    /**
     * 计算字符串的高度
     *
     * @return
     */
    private int computeTextHeight() {
        Paint.FontMetricsInt metricsInt = mTextPaint.getFontMetricsInt();
        return metricsInt.bottom + metricsInt.top;
    }

    /**
     * 计算字符串的最大高度
     *
     * @return
     */
    private int computeMaximumWidth() {
        int result = (int) mTextPaint.measureText("0");
        int width = 0;
        for (int objIndex = 0; mSelector != null && objIndex < mSelector.length; objIndex++) {
            width = (int) mTextPaint.measureText(mSelector[objIndex].toString());
            if (width > result)
                result = width;
        }

        return result;
    }


    public void setListener(OnValueChanged valueChanged) {
        mCallbackRef = valueChanged;
    }

    public interface OnValueChanged {
        void onValueChanged(View view, int position, Object defaultValue);
    }
}