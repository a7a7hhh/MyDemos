package com.example.winkey.mydemos.view.utils;

import android.os.CountDownTimer;

/**
 * @author : xupg
 * @date:2017/3/31
 * @description: TODO
 */

public class TimeCount extends CountDownTimer {

    private OnTimeCountListener onTimeCountListener;

    public interface OnTimeCountListener{
        void onTick(long millisUntilFinished);
        void onFinish();
    }

    public void setOnTimeCountListener(OnTimeCountListener onTimeCountListener){
        this.onTimeCountListener=onTimeCountListener;
    }

    public TimeCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long l) {
        onTimeCountListener.onTick(l);
    }

    @Override
    public void onFinish() {
        onTimeCountListener.onFinish();
    }
}
