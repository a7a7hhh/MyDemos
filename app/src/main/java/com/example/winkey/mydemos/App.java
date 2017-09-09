package com.example.winkey.mydemos;

import android.app.Application;

import com.example.winkey.mydemos.data.api.base.ApiConfig;
import com.example.winkey.mydemos.view.utils.ToastUtils;
import com.tencent.mta.track.StatisticsDataAPI;
import com.tencent.stat.StatConfig;
import com.tencent.stat.StatReportStrategy;
import com.tencent.stat.StatService;

/**
 * Created by Winkey on 2017/9/6.
 */

public class App extends Application {
    private static App instance;

    public ToastUtils toastUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        toastUtils = new ToastUtils(this);
        initMTA();
        //setupImageLoader();
    }

    public static App getInstance() {
        return instance;
    }

    private void initMTA() {
        // 请在初始化时调用，参数为Application或Activity或Service
        StatisticsDataAPI.instance(this);
        StatService.setContext(this);
        //  初始化MTA配置
        initMTAConfig(!BuildConfig.IS_DEBUG);
        // 注册Activity生命周期监控，自动统计时长
        StatService.registerActivityLifecycleCallbacks(getInstance());
    }

    private void initMTAConfig(boolean isDebugMode) {

        if (isDebugMode) { // 调试时建议设置的开关状态
            // 查看MTA日志及上报数据内容
            StatConfig.setDebugEnable(true);
            // 禁用MTA对app未处理异常的捕获，方便开发者调试时，及时获知详细错误信息。
            StatConfig.setAutoExceptionCaught(false);
        } else { // 发布时，建议设置的开关状态，请确保以下开关是否设置合理
            // 禁止MTA打印日志
            StatConfig.setDebugEnable(false);
            // 根据情况，决定是否开启MTA对app未处理异常的捕获
            StatConfig.setAutoExceptionCaught(true);
            // 选择默认的上报策略
            StatConfig.setStatSendStrategy(StatReportStrategy.PERIOD);

            /*if (LoginManage.getInstance().isLogin(this)) {
                UserInfoPresenter profilePresenter = UserInfoPresenter.getInstance();
                UserInfoVO userInfoVO = profilePresenter.query();
                StatConfig.setCustomUserId(this, userInfoVO.phoneNumble);
            }*/
            // 10分钟上报一次的周期
            StatConfig.setSendPeriodMinutes(10);
        }
    }
}
