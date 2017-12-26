package com.example.winkey.mydemos.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.winkey.mydemos.App;
import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.data.di.Person;
import com.example.winkey.mydemos.view.activity.account.LoginActivity;
import com.example.winkey.mydemos.view.activity.expression.ExpressionActivity;
import com.example.winkey.mydemos.view.activity.mta.MtaActivity;
import com.example.winkey.mydemos.view.activity.ormlite.OrmliteActivity;
import com.example.winkey.mydemos.view.activity.recyclerview.RecyclerViewActivity;
import com.example.winkey.mydemos.view.activity.shadow.ShadowActivity;
import com.example.winkey.mydemos.view.fragment.base.BaseFragment;
import com.example.winkey.mydemos.view.widget.dialog.DatePickerDialog;
import com.example.winkey.mydemos.view.widget.dialog.TimePickerDialog;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

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
    @BindView(R.id.tv_expression_view)
    TextView mTvExpressionView;
    @BindView(R.id.tv_ormlite)
    TextView mTvOrmlite;


    @BindView(R.id.tv_date_picker)
    TextView tvDatePicker;
    @BindView(R.id.tv_time_picker)
    TextView tvTimePicker;

    @Inject
    Person mPerson;

    @Inject
    Gson mGson;
    @Inject
    @Named("cached")
    OkHttpClient okHttpCacheClient;

    @Inject
    @Named("non_cached")
    OkHttpClient okHttpNonCacheClient;

    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        ButterKnife.bind(this, view);
        App.getInstance().getMainComponent().inject(this);
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


    @OnClick({R.id.tv_login, R.id.tv_mta, R.id.tv_shadow, R.id.tv_recycler_view, R.id.tv_expression_view, R.id.tv_ormlite, R.id.tv_date_picker, R.id.tv_time_picker})
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
            case R.id.tv_date_picker:
                datePickerDialog = new DatePickerDialog(getContext(), 1992, 10, 29);
                datePickerDialog.show();
                break;
            case R.id.tv_time_picker:
                timePickerDialog = new TimePickerDialog(getContext(),10,7);
                timePickerDialog.show();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onStop() {
        super.onStop();
        //datePickerDialog.dismiss();
    }
}
