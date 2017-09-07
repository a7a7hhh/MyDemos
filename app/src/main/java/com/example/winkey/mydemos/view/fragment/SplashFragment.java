package com.example.winkey.mydemos.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.winkey.mydemos.R;
import com.example.winkey.mydemos.view.activity.MainActivity;
import com.example.winkey.mydemos.view.fragment.base.DialogFragment;
import com.example.winkey.mydemos.view.utils.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author : xupg
 * @date:2017/4/5
 * @description: TODO
 */

public class SplashFragment extends DialogFragment {


    @BindView(R.id.fl_splash)
    FrameLayout mFlSplash;
    @BindView(R.id.iv_splash)
    ImageView mIvSplash;
    private boolean isSplashed;

    private boolean isValidated;

    public static SplashFragment newInstance() {
        SplashFragment fragment = new SplashFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, null);
        Logger.debug("进入");
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void parsentData() throws Exception {

    }

    @Override
    protected void initViews(View view) throws Exception {
        splash();

        toMain();
        mIvSplash.setBackgroundColor(Color.BLUE);
    }

    /**
     * 到主界面
     */
    private void splash() {
        //渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
        aa.setDuration(2000);
        if (mFlSplash == null) {
            Logger.debug("xwb flSplash null");
        }
        mFlSplash.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                isSplashed = true;
                toMain();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {

            }
        });
    }

    public void toMain() {
        if (!isSplashed)
            return;
        /*if (isValidated) {//账号合法
            WorkBenchActivity.start(getActivity());
        } else {//账号非法
//            LoginActivity.start(getActivity());
            LoginActivity.start(getActivity());
        }*/
        MainActivity.start(getActivity());
        getActivity().finish();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
