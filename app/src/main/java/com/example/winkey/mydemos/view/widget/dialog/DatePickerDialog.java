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

public class DatePickerDialog extends Dialog implements NumberPickerView.OnValueChanged {


    @BindView(R.id.npv_year)
    NumberPickerView npvYear;
    @BindView(R.id.npv_mouth)
    NumberPickerView npvMouth;
    @BindView(R.id.npv_day)
    NumberPickerView npvDay;
    private Context mContext;

    private int selectYear, selectMouth, selectDay;

    public DatePickerDialog(@NonNull Context context, int currentYear, int currentMouth, int currentDay) {
        super(context);
        this.mContext = context;

        this.selectYear = currentYear;
        this.selectMouth = currentMouth;
        this.selectDay = currentDay;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.dialog_date_picker, null, false);

        ButterKnife.bind(this, view);
        npvYear.setListener(this);
        npvMouth.setListener(this);
        npvDay.setListener(this);
        npvYear.post(new Runnable() {
            @Override
            public void run() {
                npvYear.setOffset(selectYear - npvYear.getMinValues());

            }
        });
        npvMouth.post(new Runnable() {
            @Override
            public void run() {
                npvMouth.setOffset(selectMouth - 1);
               /* npvDay.setValues(1, 60);
                String[] selector = new String[60];
                for (int i = 0; i < 60; i++) {
                    if (i < 10) {
                        selector[i] = "0" + i;
                    } else {
                        selector[i] = String.valueOf(i);
                    }
                }
                npvDay.setSelector(selector);*/
            }
        });
        npvDay.post(new Runnable() {
            @Override
            public void run() {
                npvDay.setOffset(selectDay - 1);
                /*npvDay.setValues(1, 60);
                String[] selector = new String[60];
                for (int i = 0; i < 60; i++) {
                    if (i < 10) {
                        selector[i] = "0" + i;
                    } else {
                        selector[i] = String.valueOf(i);
                    }
                }
                npvDay.setSelector(selector);*/
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
            case R.id.npv_year:
                selectYear = Integer.parseInt(defaultValue.toString());
                break;
            case R.id.npv_mouth:
                selectMouth = Integer.parseInt(defaultValue.toString());
                break;
            case R.id.npv_day:
                selectDay = Integer.parseInt(defaultValue.toString());
                break;
        }
        if (view.getId() == R.id.npv_year || view.getId() == R.id.npv_mouth) {
            switch (selectMouth) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    npvDay.setValues(1, 31);
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    if (selectDay == 31) {
                        npvDay.setOffset(-1);
                    }
                    npvDay.setValues(1, 30);
                    break;
                case 2:
                    if (DateUtils.isLeapYear(selectYear)) {
                        if (selectDay == 31) {
                            npvDay.setOffset(-2);
                        } else if (selectDay == 30) {
                            npvDay.setOffset(-1);
                        }
                        npvDay.setValues(1, 29);
                    } else {
                        if (selectDay == 31) {
                            npvDay.setOffset(-3);
                        } else if (selectDay == 30) {
                            npvDay.setOffset(-2);
                        } else if (selectDay == 29) {
                            npvDay.setOffset(-1);
                        }
                        npvDay.setValues(1, 28);
                    }
                    break;
            }
        }

    }
}
