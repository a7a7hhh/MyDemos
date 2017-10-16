package com.example.winkey.mydemos.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.activity.account.LoginActivity;
import com.example.winkey.mydemos.view.activity.expression.ExpressionActivity;
import com.example.winkey.mydemos.view.activity.mta.MtaActivity;
import com.example.winkey.mydemos.view.activity.ormlite.OrmliteActivity;
import com.example.winkey.mydemos.view.activity.recyclerview.RecyclerViewActivity;
import com.example.winkey.mydemos.view.activity.shadow.ShadowActivity;
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
    @BindView(R.id.tv_shadow)
    TextView mTvShadow;
    @BindView(R.id.tv_recycler_view)
    TextView mTvRecyclerView;
    @BindView( R.id.tv_expression_view)
    TextView mTvExpressionView;
    @BindView( R.id.tv_ormlite)
    TextView mTvOrmlite;

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


    @OnClick({R.id.tv_login, R.id.tv_mta, R.id.tv_shadow,R.id.tv_recycler_view,R.id.tv_expression_view,R.id.tv_ormlite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                LoginActivity.start(getContext());
                break;
            case R.id.tv_mta:
                MtaActivity.start(getContext());
                break;
            case R.id.tv_shadow:
                ShadowActivity.start(getContext());
                break;
            case R.id.tv_recycler_view:
                RecyclerViewActivity.start(getContext());
                break;
            case R.id.tv_expression_view:
                ExpressionActivity.start(getContext());
                break;
            case R.id.tv_ormlite:
                OrmliteActivity.start(getContext());
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
