package com.example.winkey.mydemos.view.fragment.mta;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.activity.mta.MtaAccountAcitiviy;
import com.example.winkey.mydemos.view.activity.mta.MtaEventActivity;
import com.example.winkey.mydemos.view.fragment.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: Winkey
 * date: 2017/9/9
 * describe: TODO
 */

public class MtaFragment extends BaseFragment {


    @BindView(R.id.tv_account_statistic)
    TextView mTvAccountStatistic;
    @BindView(R.id.tv_custom_event)
    TextView mTvCustomEvent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mta, null);
        ButterKnife.bind(this, view);
        return view;
    }

    public static MtaFragment newInstance() {
        MtaFragment fragment = new MtaFragment();
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

    @OnClick({R.id.tv_account_statistic, R.id.tv_custom_event})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_account_statistic:
                MtaAccountAcitiviy.start(getContext());
                break;
            case R.id.tv_custom_event:
                MtaEventActivity.start(getContext());
                break;
        }
    }
}
