package com.example.winkey.mydemos.view.fragment.shadow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.fragment.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: Winkey
 * date: 2017/9/9
 * describe: TODO
 */

public class ShadowFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shadow, null);
        ButterKnife.bind(this, view);
        return view;
    }

    public static ShadowFragment newInstance() {
        ShadowFragment fragment = new ShadowFragment();
        return fragment;
    }

    @Override
    protected void parsentData() throws Exception {

    }

    @Override
    protected void initViews(View view) throws Exception {
    }



}
