package com.example.winkey.mydemos.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.activity.account.LoginActivity;
import com.example.winkey.mydemos.view.fragment.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Winkey on 2017/9/7.
 */

public class MainFragment extends BaseFragment {


    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.tv_mta)
    TextView mTvMta;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        ButterKnife.bind(this, view);
        return view;
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    protected void parsentData() throws Exception {

    }

    @Override
    protected void initViews(View view) throws Exception {
    }


    @OnClick({R.id.tv_login, R.id.tv_mta})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                LoginActivity.start(getContext());
                break;
            case R.id.tv_mta:
                break;
        }
    }
}
