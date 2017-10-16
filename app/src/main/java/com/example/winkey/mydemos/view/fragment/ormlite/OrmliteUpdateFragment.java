package com.example.winkey.mydemos.view.fragment.ormlite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.data.database.base.UserInfoDao;
import com.example.winkey.mydemos.data.model.vo.UserInfoVO;
import com.example.winkey.mydemos.view.fragment.base.BaseFragment;
import com.example.winkey.mydemos.view.utils.Logger;
import com.example.winkey.mydemos.view.utils.ViewUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: Winkey
 * date: 2017/10/13
 * describe: TODO
 */

public class OrmliteUpdateFragment extends BaseFragment {

    @BindView(R.id.sp_ormlite_name)
    Spinner spOrmliteName;
    @BindView(R.id.edt_ormlite_email)
    EditText edtOrmliteEmail;
    @BindView(R.id.edt_ormlite_phone)
    EditText edtOrmlitePhone;
    @BindView(R.id.tv_ormlite_update)
    TextView tvOrmliteUpdate;
    private UserInfoDao mUserInfoDao;
    private UserInfoVO mUserInfoVO;

    private List<UserInfoVO> data;
    private List<String> list;

    private String mName = null;
    private ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ormlite_update, null);
        ButterKnife.bind(this, view);
        return view;
    }

    public static OrmliteUpdateFragment newInstance() {
        OrmliteUpdateFragment fragment = new OrmliteUpdateFragment();
        return fragment;
    }

    @Override
    protected void parsentData() throws Exception {

    }

    @Override
    protected void initViews(View view) throws Exception {
        mUserInfoDao = new UserInfoDao(getContext());
        list = mUserInfoDao.queryName();
        Logger.debug("****"+list.size());
        adapter =new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOrmliteName.setAdapter(adapter);
        spOrmliteName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mName = (String) spOrmliteName.getSelectedItem();
                mUserInfoVO = mUserInfoDao.query(mName);
                edtOrmlitePhone.setText(mUserInfoVO.phone);
                edtOrmliteEmail.setText(mUserInfoVO.email);
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

    @OnClick(R.id.tv_ormlite_update)
    public void onViewClicked() {
        if(verify()){
            if(mUserInfoDao.update(mName,ViewUtil.getContent(edtOrmlitePhone,""),ViewUtil.getContent(edtOrmlitePhone,""))) {
                showMsg("修改成功");
            }
        }
    }
    public boolean verify(){
        if(ViewUtil.isEmpty(edtOrmliteEmail)){
            showMsg("邮箱不能为空");
            return false;
        }
        if(ViewUtil.isEmpty(edtOrmlitePhone)){
            showMsg("电话不能为空");
            return false;
        }
        return true;
    }
}
