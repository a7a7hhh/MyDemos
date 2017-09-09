package com.example.winkey.mydemos.view.fragment.mta;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.fragment.base.BaseFragment;
import com.tencent.stat.StatService;

import java.util.Properties;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: Winkey
 * date: 2017/9/9
 * describe: TODO
 */

public class MtaEventFragment extends BaseFragment {


    @BindView(R.id.tv_event_normal)
    TextView mTvEventNormal;
    @BindView(R.id.tv_event_begin_cal)
    TextView mTvEventBeginCal;
    @BindView(R.id.tv_event_end_cal)
    TextView mTvEventEndCal;
    @BindView(R.id.tv_event_visual)
    TextView mTvEventVisual;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mta_event, null);
        ButterKnife.bind(this, view);
        return view;
    }

    public static MtaEventFragment newInstance() {
        MtaEventFragment fragment = new MtaEventFragment();
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

    @OnClick({R.id.tv_event_normal, R.id.tv_event_begin_cal, R.id.tv_event_end_cal,R.id.tv_event_visual})
    public void onViewClicked(View view) {

        Properties prop = new Properties();
        switch (view.getId()) {
            case R.id.tv_event_normal:
                prop.setProperty("name", "normal_event");
                StatService.trackCustomKVEvent(getContext(), "normal_event", prop);
                break;
            case R.id.tv_event_begin_cal:
                if (mTvEventBeginCal.isEnabled()) {
                    mTvEventBeginCal.setEnabled(false);
                    mTvEventEndCal.setEnabled(true);
                    prop.setProperty("name", "calculate_begin");
                    StatService.trackCustomBeginKVEvent(getContext(),"calculate_event", prop);
                }
                break;
            case R.id.tv_event_end_cal:
                if (mTvEventEndCal.isEnabled()) {
                    mTvEventBeginCal.setEnabled(true);
                    mTvEventEndCal.setEnabled(false);
                    prop.setProperty("name", "calculate_end");
                    StatService.trackCustomBeginKVEvent(getContext(),"calculate_event", prop);
                }
                break;
            case R.id.tv_event_visual:
                if (mTvEventEndCal.isEnabled()) {
                    mTvEventBeginCal.setEnabled(true);
                    mTvEventEndCal.setEnabled(false);
                }
                break;
        }
    }

}
