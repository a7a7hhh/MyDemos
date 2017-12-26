package com.example.winkey.mydemos.view.fragment.time;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.fragment.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: Winkey
 * date: 2017/12/22.
 * describe: TODO
 */

public class TimePickerFragment extends BaseFragment {


    @BindView(R.id.tv_time_picker)
    TextView tvTimePicker;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_picker, null);
        ButterKnife.bind(this, view);

        return view;
    }

    public static TimePickerFragment newInstance() {
        TimePickerFragment fragment = new TimePickerFragment();
        return fragment;
    }

    @Override
    protected void parsentData() throws Exception {

    }

    @Override
    protected void initViews(View view) throws Exception {
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.tv_time_picker)
    public void onViewClicked() {
    }
}
