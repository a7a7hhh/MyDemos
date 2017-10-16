package com.example.winkey.mydemos.view.fragment.ormlite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.data.database.base.UserInfoDao;
import com.example.winkey.mydemos.data.model.vo.UserInfoVO;
import com.example.winkey.mydemos.view.adapter.OrmliteQueryAdapter;
import com.example.winkey.mydemos.view.fragment.base.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Winkey
 * date: 2017/10/10
 * describe: TODO
 */

public class OrmliteQueryFragment extends BaseFragment {

    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    private UserInfoDao mUserInfoDao;
    private UserInfoVO mUserInfoVO;

    private List<UserInfoVO> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ormlite_query, null);
        ButterKnife.bind(this, view);
        return view;
    }

    public static OrmliteQueryFragment newInstance() {
        OrmliteQueryFragment fragment = new OrmliteQueryFragment();
        return fragment;
    }

    @Override
    protected void parsentData() throws Exception {

    }

    @Override
    protected void initViews(View view) throws Exception {
        mUserInfoDao = new UserInfoDao(getContext());
        data = mUserInfoDao.query();
        OrmliteQueryAdapter ormliteQueryAdapter = new OrmliteQueryAdapter(getContext(),data);
        mRvList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRvList.setAdapter(ormliteQueryAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
