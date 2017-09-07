package com.example.winkey.mydemos.view.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.winkey.mydemos.view.utils.ToastUtils;


/**
 * @author : xupg
 * @date:2017/3/30
 * @description: TODO
 */

public abstract class BaseFragment extends Fragment {
    private static ToastUtils toast;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            parsentData();
            initViews(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param message
     */
    protected void showMsg(String message) {
        if (toast == null) {
            toast = new ToastUtils(getActivity());
        }
        toast.show(message);
    }

    /**
     * 数据相关
     */
    protected abstract void parsentData() throws Exception;

    /**
     * view相关
     */
    protected abstract void initViews(View view) throws Exception;

    /**
     * 添加Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        if (!fragment.isAdded()) {
            FragmentTransaction transaction = this.getChildFragmentManager().beginTransaction();
            transaction.add(containerViewId, fragment);
            transaction.commit();
        }
    }

    /**
     * 获取args value
     *
     * @param key
     * @param defaultValues
     * @return
     */
    protected Object getExtra(String key, Object defaultValues) {
        if (getArguments() != null && getArguments().containsKey(key)) {
            return getArguments().get(key);
        }

        return defaultValues;
    }

    /**
     * 获取args value
     *
     * @param key
     * @param defaultValues
     * @return
     */
    protected Object getParcelable(String key, Object defaultValues) {
        Object res = null;
        if (getArguments() != null && getArguments().containsKey(key)) {
            res = getArguments().getParcelable(key);
        }

        return (res == null || res == "") ? defaultValues : res;
    }
}
