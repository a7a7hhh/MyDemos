package com.example.winkey.mydemos.view.fragment.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.winkey.mydemos.view.load.LoadDataView;


/**
 * @author : xupg
 * @date:2017/3/31
 * @description: TODO
 */

public abstract class DialogFragment extends BaseFragment implements LoadDataView {

    private ProgressDialog mProgressDialog;
    private Dialog dialog;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        init();
        super.onViewCreated(view, savedInstanceState);
    }

    private void init(){
        mProgressDialog=new ProgressDialog(getContext());
        dialog= new AlertDialog.Builder(getContext()).create();
    }


    @Override
    public void showLoading() {
        if(mProgressDialog!=null)
            mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        if(mProgressDialog!=null)
            mProgressDialog.dismiss();
    }

    @Override
    public void showErrorView(String message, int messagecode) {
        showMsg(message);
        if (dialog!=null)
            dialog.setTitle(message);
    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void showNetWorkView() {

    }
    @Override
    public Context context() {
        return getActivity();
    }
}
