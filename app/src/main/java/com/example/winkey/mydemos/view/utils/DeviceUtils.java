package com.example.winkey.mydemos.view.utils;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

public class DeviceUtils {

	
	/**
	 * Gets the screen size.
	 * 返回一个宽高数组
	 * @param ctx the ctx
	 * @return the screen size
	 */
	public static int[] getScreenSize(Context ctx){
		int[] size = new int[2];
		DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
		size[0] = dm.widthPixels;
		size[1] = dm.heightPixels;
		return size;
	}
	/**
	 * 
	 * @Title: activeDeviceManager 
	 * @Description: 激活设备管理器
	 * @param context
	 * @param cls 必须继承DeviceAdminReceiver
	 * @param explanation 弹出设备管理器的时候显示的描述
	 */
	public static void activeDeviceManager(Context context, Class<? extends DeviceAdminReceiver> cls, String explanation) {
		ComponentName receiver = new ComponentName(context,cls);
		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, receiver);
		intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, explanation);
		context.startActivity(intent);
	}
	
	/**
	 * 
	 * @Title: unactiveDeviceManager 
	 * @Description: 注销设备管理器
	 * @param context
	 * @param cls 
	 */
	private static void unactiveDeviceManager(Context context, Class<? extends DeviceAdminReceiver> cls) {
		DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
		ComponentName receiver = new ComponentName(context,cls);
		if (isDeviceManagerActive(context, cls)) {
			dpm.removeActiveAdmin(receiver);
		}
	}
	
	/**
	 * 
	 * @Title: isDeviceManagerActive 
	 * @Description: 检测是否激活设备管理器
	 * @param context
	 * @param cls
	 * @return: boolean
	 */
	public static boolean isDeviceManagerActive(Context context, Class<? extends DeviceAdminReceiver> cls) {
		DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
		ComponentName receiver = new ComponentName(context,cls);
		return dpm.isAdminActive(receiver);
	}

	/**
	 * Gets the imei.
	 * 获取DeviceId
	 * @param ctx the ctx
	 * @return the imei
	 */
	public static String getIMEI(Context ctx){
		TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
		if (!TextUtils.isEmpty(tm.getDeviceId())) {
			return tm.getDeviceId();
		}
		return "此设备无IMEI";
	}

	public static int dpToPx(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int pxToDp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}
