package com.example.winkey.mydemos.view.fragment.ormlite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.data.database.base.UserInfoDao;
import com.example.winkey.mydemos.data.model.vo.UserInfoVO;
import com.example.winkey.mydemos.view.fragment.base.BaseFragment;
import com.example.winkey.mydemos.view.utils.Logger;
import com.example.winkey.mydemos.view.utils.ViewUtil;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: Winkey
 * date: 2017/10/9
 * describe: TODO
 */

public class OrmliteAddFragment extends BaseFragment {

    @BindView(R.id.edt_ormlite_name)
    EditText mEdtOrmliteName;
    @BindView(R.id.edt_ormlite_email)
    EditText mEdtOrmliteEmail;
    @BindView(R.id.edt_ormlite_phone)
    EditText mEdtOrmlitePhone;
    @BindView(R.id.tv_ormlite_add)
    TextView mTvOrmliteAdd;

    private UserInfoDao mUserInfoDao;
    private UserInfoVO mUserInfoVO;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ormlite_add, null);
        ButterKnife.bind(this, view);
        return view;
    }

    public static OrmliteAddFragment newInstance() {
        OrmliteAddFragment fragment = new OrmliteAddFragment();
        return fragment;
    }

    @Override
    protected void parsentData() throws Exception {

    }

    @Override
    protected void initViews(View view) throws Exception {
        mUserInfoDao = new UserInfoDao(getContext());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.tv_ormlite_add)
    public void onViewClicked() {
        if (verify()) {
            //mUserInfoVO = new UserInfoVO(name,email,phone);
            mUserInfoVO = new UserInfoVO.Builder().name(ViewUtil.getContent(mEdtOrmliteName,""))
                    .setEmail(ViewUtil.getContent(mEdtOrmliteEmail,""))
                    .setPhone(ViewUtil.getContent(mEdtOrmlitePhone,""))
                    .build();
            if(mUserInfoDao.create(mUserInfoVO)){
             showMsg("新增成功");
            }
        }
    }

    private boolean verify() {
        if (ViewUtil.isEmpty(mEdtOrmliteName)) {
            showMsg("姓名不能为空");
            return false;
        }
        if (ViewUtil.isEmpty(mEdtOrmliteEmail)) {
            showMsg("邮箱不能为空");
            return false;
        }
        if (ViewUtil.isEmpty(mEdtOrmlitePhone)) {
            showMsg("电话不能为空");
            return false;
        }
        String name = ViewUtil.getContent(mEdtOrmliteName,"");
        if(mUserInfoDao.queryName().contains(name)){
            showMsg("库中已经包含该姓名的用户！");
            return false;
        }
        return true;
    }
}
