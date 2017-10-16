package com.example.winkey.mydemos.view.fragment.ormlite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.data.database.base.UserInfoDao;
import com.example.winkey.mydemos.data.model.vo.UserInfoVO;
import com.example.winkey.mydemos.view.fragment.base.BaseFragment;
import com.example.winkey.mydemos.view.utils.Logger;
import com.example.winkey.mydemos.view.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: Winkey
 * date: 2017/10/13
 * describe: TODO
 */

public class OrmliteDeleteFragment extends BaseFragment {


    @BindView(R.id.tv_ormlite_delete)
    TextView mTvOrmliteDelete;
    @BindView(R.id.sp_ormlite_name)
    Spinner mSpOrmliteName;
    private UserInfoDao mUserInfoDao;
    private List<UserInfoVO> data;

    private List<String> list;

    private String mName = null;
    private ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ormlite_delete, null);
        ButterKnife.bind(this, view);
        return view;
    }

    public static OrmliteDeleteFragment newInstance() {
        OrmliteDeleteFragment fragment = new OrmliteDeleteFragment();
        return fragment;
    }

    @Override
    protected void parsentData() throws Exception {

    }

    @Override
    protected void initViews(View view) throws Exception {
        mUserInfoDao = new UserInfoDao(getContext());
        list = mUserInfoDao.queryName();
        adapter =new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpOrmliteName.setAdapter(adapter);
        mSpOrmliteName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mName = (String) mSpOrmliteName.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.tv_ormlite_delete)
    public void onViewClicked() {
        Logger.debug("***"+mName);
        if (mName != null) {
            mUserInfoDao.delete(mName);
            data = mUserInfoDao.query();
            list.clear();
            for (UserInfoVO userInfoVO : data) {
                list.add(userInfoVO.name);
            }
            adapter.notifyDataSetChanged();
        }
    }

}
