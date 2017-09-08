package com.example.winkey.mydemos.view.fragment.account;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.data.model.vo.account.TokenVO;
import com.example.winkey.mydemos.presentation.base.CallBackListener;
import com.example.winkey.mydemos.presentation.presenter.account.LoginPresenter;
import com.example.winkey.mydemos.view.fragment.base.BaseFragment;
import com.example.winkey.mydemos.view.fragment.base.DialogFragment;
import com.example.winkey.mydemos.view.utils.Logger;
import com.example.winkey.mydemos.view.utils.ViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

/**
 * Created by Winkey on 2017/9/8.
 */

public class LoginFragment extends DialogFragment {

    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.tv_forget_pswd)
    TextView mTvForgetPswd;
    @BindView(R.id.iv_logo)
    ImageView mIvLogo;
    @BindView(R.id.edt_account)
    EditText mEdtAccount;
    @BindView(R.id.edt_password)
    EditText mEdtPassword;

    private LoginPresenter mLoginPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mLoginPresenter = new LoginPresenter(context);
       // mUserInfoPresenter = UserInfoPresenter.getInstance();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginPresenter.register(this);
       // mUserInfoPresenter.register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLoginPresenter.unregister(this);
        //mUserInfoPresenter.unregister(this);
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void parsentData() throws Exception {

    }

    @Override
    protected void initViews(View view) throws Exception {

    }


    @OnClick({R.id.tv_login, R.id.tv_forget_pswd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                login();
                break;
            case R.id.tv_forget_pswd:
                break;
        }
    }

    private void login() {
        mLoginPresenter.login(ViewUtil.getContent(mEdtAccount, ""),
                ViewUtil.getContent(mEdtPassword, ""), new CallBackListener<TokenVO>() {
                    @Override
                    public void callBack(TokenVO tokenVO) {
                        //WorkBenchActivity.start(context());
                        Logger.debug(tokenVO.access_token);
                    }
                });
    }
}
