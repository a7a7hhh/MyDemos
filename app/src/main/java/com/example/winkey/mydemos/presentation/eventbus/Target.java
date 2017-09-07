package com.example.winkey.mydemos.presentation.eventbus;

/**
 * @author: xupg
 * @date:2016/3/30
 * @description:
 */

public enum Target {

    /**
     * 登录成功
     */
    TARGET_LOGIN_SUCCESS("target.login.success"),

    /**
     * 检测升级
     */
    TARGET_UPDATE("target.update"),

    /**
     * 检测升级反馈
     */
    TARGET_UPDATE_CHECK_FEEDBACK("target.update.check.feedback"),

    /**
     * 升级确认弹出框
     */
    TARGET_UPDATE_DIALOG_ENSURE("target.update.dialog.ensure"),

    /**
     * 下载文件
     */
    TARGET_DOWNFILE("target.downfile"),

    /**
     * 后台下载
     */
    TARGET_BACKGROUND("target.background");

    private String value;

    Target(String value) {
        this.value = value;
    }

}
