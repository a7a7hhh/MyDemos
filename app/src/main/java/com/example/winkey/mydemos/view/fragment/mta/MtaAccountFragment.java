package com.example.winkey.mydemos.view.fragment.mta;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.fragment.base.BaseFragment;
import com.example.winkey.mydemos.view.utils.ViewUtil;
import com.tencent.stat.StatConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: Winkey
 * date: 2017/9/9
 * describe: TODO
 */

public class MtaAccountFragment extends BaseFragment {

    @BindView(R.id.edt_account_statistic)
    EditText mEdtAccountStatistic;
    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mta_account, null);
        ButterKnife.bind(this, view);
        return view;
    }

    public static MtaAccountFragment newInstance() {
        MtaAccountFragment fragment = new MtaAccountFragment();
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

    @OnClick({R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:
                if (ViewUtil.isEmpty(mEdtAccountStatistic)) {
                    showMsg("输入为空");
                } else {
                    StatConfig.setCustomUserId(getContext(), ViewUtil.getContent(mEdtAccountStatistic, null));
                    showMsg("添加中");
                }
                break;
        }
    }
}
