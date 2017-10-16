package com.example.winkey.mydemos.view.fragment.ormlite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.activity.ormlite.OrmliteAddActivity;
import com.example.winkey.mydemos.view.activity.ormlite.OrmliteDeleteActivity;
import com.example.winkey.mydemos.view.activity.ormlite.OrmliteQueryActivity;
import com.example.winkey.mydemos.view.activity.ormlite.OrmliteUpdateActivity;
import com.example.winkey.mydemos.view.fragment.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: Winkey
 * date: 2017/10/9
 * describe: TODO
 */

public class OrmliteFragment extends BaseFragment {
    @BindView(R.id.tv_add)
    TextView mTvAdd;
    @BindView(R.id.tv_delete)
    TextView mTvDelete;
    @BindView(R.id.tv_update)
    TextView mTvUpgrade;
    @BindView(R.id.tv_query)
    TextView mTvQuery;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ormlite, null);
        ButterKnife.bind(this, view);
        return view;
    }

    public static OrmliteFragment newInstance() {
        OrmliteFragment fragment = new OrmliteFragment();
        return fragment;
    }

    @Override
    protected void parsentData() throws Exception {

    }

    @Override
    protected void initViews(View view) throws Exception {
    }

    @OnClick({R.id.tv_add, R.id.tv_delete, R.id.tv_update, R.id.tv_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_add:
                OrmliteAddActivity.start(getContext());
                break;
            case R.id.tv_delete:
                OrmliteDeleteActivity.start(getContext());
                break;
            case R.id.tv_update:
                OrmliteUpdateActivity.start(getContext());
                break;
            case R.id.tv_query:
                OrmliteQueryActivity.start(getContext());
                break;
        }
    }
}
