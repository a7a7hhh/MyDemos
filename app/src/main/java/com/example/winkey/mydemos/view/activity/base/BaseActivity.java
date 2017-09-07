package com.example.winkey.mydemos.view.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import com.example.winkey.mydemos.AppManage;
import com.example.winkey.mydemos.view.utils.ToastUtils;

import butterknife.ButterKnife;

/**
 * @author : xupg
 * @date:2017/3/30
 * @description: TODO
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static ToastUtils toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 这句很关键，注意是调用父类的方法
        super.setContentView(getLayoutId());
//        // 经测试在代码里直接声明透明状态栏更有效
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//        }
//        initToolbar();
        //将activity加入到AppManager堆栈中
        AppManage.getAppManager().addActivity(this);
        ButterKnife.bind(this);
        parsentData();
        initViews();
    }

    /**
     * 隐藏软键盘
     */
    private void hideSoftInput() {
        View view = BaseActivity.this.getCurrentFocus();
        if (view != null && view instanceof EditText) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

    }

    /**
     * 获取布局id
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 数据相关
     */
    protected abstract void parsentData();

    /**
     * view相关
     */
    protected abstract void initViews();


    /**
     * 添加Fragment到布局
     *
     * @param containerViewId
     * @param fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.add(containerViewId, fragment);
        transaction.commit();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 从栈中移除
        AppManage.getAppManager().removeActivity(this);
        hideSoftInput();
    }

    /**
     * 弹出 toast
     *
     * @param content
     */
    /**
     * @param message
     */
    protected void showMsg(String message) {
        if (toast == null) {
            toast = new ToastUtils(BaseActivity.this);
        }
        toast.show(message);
    }

    /**
     * 处理事件分发调用的方法
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 当前获取焦点的View对象
            View view = getCurrentFocus();
            if (isShouldHideInput(view, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
                }
            }
            return super.dispatchTouchEvent(ev);
        }

        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }
    /**
     * 判断是否需要隐藏软键盘输入：如果点击的是除了EditText之外的，需要隐藏键盘
     * @param view
     * @param ev
     * @return 需要隐藏键盘的返回true，点击EditText区域返回false
     */
    private boolean isShouldHideInput(View view, MotionEvent ev) {
        if (view != null && (view instanceof EditText)) {
            /**
             * 获取输入框当前的location位置，保存到leftTop数组中
             */
            int[] leftTop = {0, 0};
            view.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int right = left + view.getWidth();
            int bottom = top + view.getHeight();
            float evX = ev.getX();
            float evY = ev.getY();
            // 点击的是输入框区域，保留点击事件
            if (evX > left && evX < right && evY > top && evY < bottom) {
                return false;
            } else {
                return true;
            }

        }
        return false;
    }

//    /**
//     * 沉浸式
//     * @param hasFocus
//     */
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        }
//    }
}
