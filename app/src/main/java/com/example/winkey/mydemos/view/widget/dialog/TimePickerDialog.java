package com.example.winkey.mydemos.view.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.utils.DateUtils;
import com.example.winkey.mydemos.view.utils.Logger;
import com.example.winkey.mydemos.view.widget.NumberPickerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Winkey
 * date: 2017/12/22.
 * describe: TODO
 */

public class TimePickerDialog extends Dialog implements NumberPickerView.OnValueChanged {


    @BindView(R.id.npv_hour)
    NumberPickerView npvHour;
    @BindView(R.id.npv_minute)
    NumberPickerView npvMinute;
    private Context mContext;

    private int selectHour, selectMinute;

    public TimePickerDialog(@NonNull Context context, int currentHour, int currentMinute) {
        super(context);
        this.mContext = context;

        this.selectHour = currentHour;
        this.selectMinute = currentMinute;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.dialog_time_picker, null, false);

        ButterKnife.bind(this, view);
        npvHour.setListener(this);
        npvMinute.setListener(this);
        npvHour.post(new Runnable() {
            @Override
            public void run() {
                //npvHour.setOffset(selectHour - 1);
                //npvHour.setValues(1, 24);
                String[] selector = new String[24];
                for (int i = 0; i < 24; i++) {
                    if (i < 10) {
                        selector[i] = "0" + i;
                    } else {
                        selector[i] = String.valueOf(i);
                    }
                }
                npvHour.setSelector(selector);
            }
        });
        npvMinute.post(new Runnable() {
            @Override
            public void run() {
                //npvMinute.setOffset(selectMinute - 1);
                npvMinute.setValues(1, 60);
                String[] selector = new String[60];
                for (int i = 0; i < 60; i++) {
                    if (i < 10) {
                        selector[i] = "0" + i;
                    } else {
                        selector[i] = String.valueOf(i);
                    }
                }
                npvMinute.setSelector(selector);
            }
        });


        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics();
        //dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);
        lp.width = (int) (d.widthPixels * 0.7);
        dialogWindow.setAttributes(lp);
        dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(view);

    }


    @Override
    public void onValueChanged(View view, int position, Object defaultValue) {

        switch (view.getId()) {
            case R.id.npv_mouth:
                selectHour = Integer.parseInt(defaultValue.toString());
                break;
            case R.id.npv_minute:
                selectMinute = Integer.parseInt(defaultValue.toString());
                break;
        }


    }
}
