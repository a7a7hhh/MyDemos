package com.example.winkey.mydemos.view.fragment.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.adapter.XiaomeiAdapter;
import com.example.winkey.mydemos.view.fragment.base.DialogFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: Winkey
 * date: 2017/9/13
 * describe: TODO
 */

public class XiaomeiFragment extends DialogFragment {

    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;

    private Context mContext;
    private XiaomeiAdapter mXiaomeiAdapter;
    private List<String> mDatas;

    public static XiaomeiFragment newInstance() {
        XiaomeiFragment fragment = new XiaomeiFragment();
        return fragment;
    }

    @Override
    protected void parsentData() throws Exception {

    }

    @Override
    protected void initViews(View view) throws Exception {

        LinearLayoutManager llManager = new LinearLayoutManager(mContext);
        mRvList.setLayoutManager(llManager);
        mDatas = new ArrayList<>();
        mDatas.add("");
        mDatas.add("");
        mXiaomeiAdapter = new XiaomeiAdapter(mContext,mDatas);
        mRvList.setAdapter(mXiaomeiAdapter);
        mXiaomeiAdapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xiaomei, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
