package com.example.winkey.mydemos.view.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.support.v4.content.FileProvider;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;


import com.example.winkey.mydemos.BuildConfig;

import java.io.File;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author: xupg
 * @date:2017/3/30
 * @description: Toast工具类
 */

@SuppressLint({"SimpleDateFormat"})
public final class SystemTool {
    public SystemTool() {
    }

    /**
     * 检查是否有sd卡
     *
     * @return
     */
    public static boolean checkSDcard() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    /**
     * 获取时间
     *
     * @param format
     * @return
     */
    public static String getDataTime(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    /**
     * 获取当前时间hh：mm
     *
     * @return
     */
    public static String getDataTime() {
        return getDataTime("HH:mm");
    }

    /**
     * 获取手机IMEI码
     *
     * @param cxt
     * @return
     */
    public static String getPhoneIMEI(Context cxt) {
        TelephonyManager tm = (TelephonyManager) cxt.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * 获取sdk版本
     *
     * @return
     */
    public static int getSDKVersion() {
        return VERSION.SDK_INT;
    }

    /**
     * 获取系统版本
     *
     * @return
     */
    public static String getSystemVersion() {
        return VERSION.RELEASE;
    }

    /**
     * 发送短信
     *
     * @param cxt
     * @param smsBody
     */
    public static void sendSMS(Context cxt, String smsBody) {
        Uri smsToUri = Uri.parse("smsto:");
        Intent intent = new Intent(Intent.ACTION_SEND, smsToUri);
        intent.putExtra("sms_body", smsBody);
        cxt.startActivity(intent);
    }

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean checkNet(Context context) {
        boolean isConnected;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        isConnected = (info != null && info.isConnectedOrConnecting());
        return isConnected;
    }

    /**
     * 是否有wifi
     *
     * @param cxt
     * @return
     */
    public static boolean isWiFi(Context cxt) {
        ConnectivityManager cm = (ConnectivityManager) cxt.getSystemService(Context.CONNECTIVITY_SERVICE);
        State state = cm.getNetworkInfo(1).getState();
        return State.CONNECTED == state;
    }

    /**
     * 隐藏键盘
     *
     * @param aty
     */
    public static void hideKeyBoard(Activity aty) {
        ((InputMethodManager) aty.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(aty.getCurrentFocus().getWindowToken(), 2);
    }

    /**
     * 是否后台运行
     *
     * @param context
     * @return
     */
    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List appProcesses = activityManager.getRunningAppProcesses();
        Iterator var4 = appProcesses.iterator();

        while (var4.hasNext()) {
            RunningAppProcessInfo appProcess = (RunningAppProcessInfo) var4.next();
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == 400) {
                    return true;
                }

                return false;
            }
        }

        return false;
    }

    /**
     * 判断手机是否处理睡眠
     *
     * @param context
     * @return
     */
    public static boolean isSleeping(Context context) {
        KeyguardManager kgMgr = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        boolean isSleeping = kgMgr.inKeyguardRestrictedInputMode();
        return isSleeping;
    }

    /**
     * 安装apk
     *
     * @param context
     * @param file
     */
    public static void installApk(Context context, File file) {
        Intent i = new Intent(Intent.ACTION_VIEW);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileprovider", file);
            i.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
        }
        context.startActivity(i);
    }

    /**
     * 安装apk
     *
     * @param context
     * @param uri
     */
    public static void installApk(Context context, Uri uri) {
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.setDataAndType(uri, "application/vnd.android.package-archive");
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(install);
    }

    /**
     * 获取应用程序版本名称
     *
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        String version = "0";

        try {
            version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            return version;
        } catch (NameNotFoundException var3) {
            throw new RuntimeException(SystemTool.class.getName() + "the application not found");
        }
    }

    /**
     * 获取应用版本号
     *
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        boolean version = false;

        try {
            int version1 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            return version1;
        } catch (NameNotFoundException var3) {
            throw new RuntimeException(SystemTool.class.getName() + "the application not found");
        }
    }

    /**
     * 换回设备桌面
     *
     * @param context
     */
    public static void goHome(Context context) {
        Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);
        mHomeIntent.addCategory(Intent.CATEGORY_HOME);
        mHomeIntent.addFlags(270532608);
        context.startActivity(mHomeIntent);
    }

    /**
     * 获取应用签名
     *
     * @param context
     * @param pkgName
     * @return
     */
    public static String getSign(Context context, String pkgName) {
        try {
            PackageInfo e = context.getPackageManager().getPackageInfo(pkgName, 64);
            return hexdigest(e.signatures[0].toByteArray());
        } catch (NameNotFoundException var3) {
            throw new RuntimeException(SystemTool.class.getName() + "the " + pkgName + "\'s application not found");
        }
    }

    /**
     * @param paramArrayOfByte
     * @return
     */
    private static String hexdigest(byte[] paramArrayOfByte) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramArrayOfByte);
            byte[] arrayOfByte = localMessageDigest.digest();
            char[] arrayOfChar = new char[32];
            int i = 0;

            for (int j = 0; i < 16; ++j) {
                byte k = arrayOfByte[i];
                arrayOfChar[j] = hexDigits[15 & k >>> 4];
                ++j;
                arrayOfChar[j] = hexDigits[k & 15];
                ++i;
            }

            return new String(arrayOfChar);
        } catch (Exception var8) {
            return "";
        }
    }

    /**
     * 获取设备可用内存
     *
     * @param cxt
     * @return
     */
    public static int getDeviceUsableMemory(Context cxt) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi);
        return (int) (mi.availMem / 1048576L);
    }

    /**
     * 清理后台进程与服务
     *
     * @param cxt
     * @return
     */
    public static int gc(Context cxt) {
        long i = (long) getDeviceUsableMemory(cxt);
        int count = 0;
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List serviceList = am.getRunningServices(100);
        if (serviceList != null) {
            Iterator process = serviceList.iterator();

            while (process.hasNext()) {
                RunningServiceInfo processList = (RunningServiceInfo) process.next();
                if (processList.pid != Process.myPid()) {
                    try {
                        Process.killProcess(processList.pid);
                        ++count;
                    } catch (Exception var16) {
                        var16.getStackTrace();
                    }
                }
            }
        }

        List var17 = am.getRunningAppProcesses();
        if (var17 != null) {
            Iterator e = var17.iterator();

            label44:
            while (true) {
                RunningAppProcessInfo var18;
                do {
                    if (!e.hasNext()) {
                        break label44;
                    }

                    var18 = (RunningAppProcessInfo) e.next();
                } while (var18.importance <= 200);

                String[] pkgList = var18.pkgList;
                String[] var13 = pkgList;
                int var12 = pkgList.length;

                for (int var11 = 0; var11 < var12; ++var11) {
                    String pkgName = var13[var11];
                    Logger.debug("======正在杀死包名：" + pkgName);

                    try {
                        am.killBackgroundProcesses(pkgName);
                        ++count;
                    } catch (Exception var15) {
                        var15.getStackTrace();
                    }
                }
            }
        }

        Logger.debug("清理了" + ((long) getDeviceUsableMemory(cxt) - i) + "M内存");
        return count;
    }


    /**
     * 获取当前进程名
     *
     * @param context
     * @return 进程名
     */
    public static final String getProcessName(Context context) {
        String processName = null;

        // ActivityManager
        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));

        while (true) {
            for (RunningAppProcessInfo info : am.getRunningAppProcesses()) {
                if (info.pid == Process.myPid()) {
                    processName = info.processName;

                    break;
                }
            }

            // go home
            if (!TextUtils.isEmpty(processName)) {
                return processName;
            }

            // take a rest and again
            try {
                Thread.sleep(100L);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * app进程是否存活
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppAlive(Context context, String packageName){
        ActivityManager activityManager =
                (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> processInfos
                = activityManager.getRunningAppProcesses();
        for(int i = 0; i < processInfos.size(); i++){
            if(processInfos.get(i).processName.equals(packageName)){
                Log.i("NotificationLaunch",
                        String.format("the %s is running, isAppAlive return true", packageName));
                return true;
            }
        }
        Log.i("NotificationLaunch",
                String.format("the %s is not running, isAppAlive return false", packageName));
        return false;
    }
}
