package com.example.winkey.mydemos.view.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * @author: xupg
 * @date:2017/3/30
 * @description: Toast工具类
 */
public class ViewUtil {


    /**
     * 还没有对Invisible进行处理
     */
    public static void setVisibility(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    /**
     * 空验证
     *
     * @param editText
     * @return
     */
    public static boolean isEmpty(@NonNull EditText editText) {
        if (TextUtils.isEmpty(editText.getText())) {
            return true;
        }
        return false;
    }


    /**
     * 获取EditText内容
     *
     * @param editText
     * @return
     */
    public static String getContent(EditText editText, @Nullable String defaultVal) {
        return (TextUtils.isEmpty(editText.getText()) ? defaultVal : editText.getText().toString().trim());
    }

    /**
     * @param editText  目标输入框
     * @param inputType 输入类型
     * @param accepted  接收输入的字符，设置了这个就默认了一个KeyListener
     * @param maxLength 最大输入字符数，默认设置Filters
     * @Title: setInputPolicy
     * @Description: 为EditText设置输入策略
     * @return: void
     */
    public static void setInputPolicy(EditText editText, int inputType,
                                      String accepted, int maxLength) {
        if (editText == null)
            return;
        editText.setInputType(inputType);
        editText.setKeyListener(DigitsKeyListener.getInstance(accepted));
        InputFilter[] filters = {new InputFilter.LengthFilter(maxLength)};
        editText.setFilters(filters);
    }

    public static int getListViewHeightBasedOnAdapter(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return -1;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        return totalHeight;

    }

    /**
     * Checks if is list view higher than screen. 判断ListView的高度是否高过屏幕的高度
     *
     * @param listView the list view
     * @return true, if is list view higher than screen
     */
    public static boolean isListViewHigherThanScreen(Context ctx,
                                                     ListView listView) {

        return DeviceUtils.getScreenSize(ctx)[1] < getListViewHeightBasedOnAdapter(listView);
    }

    /**
     * 按钮加载中
     *
     * @param ctx
     * @param button
     * @param loadingText
     */
    public static void buttonLoding(Context ctx, Button button, String loadingText) {
        button.setText("加载中...");
    }


    /**
     * 设置EditText错误提示
     *
     * @param editText
     * @param inputLayout
     * @param errorString
     */
    public static void editTextError(EditText editText, TextInputLayout inputLayout, String errorString) {
        editText.setError(errorString);
        inputLayout.setError(errorString);
    }
}
