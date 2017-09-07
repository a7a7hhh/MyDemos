package com.example.winkey.mydemos.view.utils;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Format;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : xupg
 * @date:2017/3/30
 * @description: TODO
 */

public class DataUtils {

    /**
     * 格式转化
     *
     * @param num
     * @return
     */
    public static String d2Money(String num, Format format) {
        double d = Double.valueOf(num);
        String unit = "元";
        if (d > 10000) {
            unit = "万元";
            d = d / 10000;
        }
        return String.valueOf(format.format(d)) + unit;
    }

    /**
     * 保留两位小数
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static double retain(double data) throws Exception {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        return Double.parseDouble(df.format(data));
    }

    /**
     * 保留1位小数
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static double retainOne(double data) throws Exception {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.0");
        return Double.parseDouble(df.format(data));
    }

    /**
     * 保留6位小数
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static double retainSix(double data) throws Exception {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.000000");
        return Double.parseDouble(df.format(data));
    }

    /**
     * 去掉零
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String retainNotZero(String data) {
        if (data.indexOf(".") > 0) {
            //正则表达
            data = data.replaceAll("0+?$", "");//去掉后面无用的零
            data = data.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }

        return data;
    }

    /**
     * list转String
     *
     * @param list
     * @param separator 分隔符
     * @return
     */
    public static String list2Str(List<String> list, String separator) {
        int length = list.size();
        StringBuffer buf = new StringBuffer("");
        if (length > 0) {
            buf.append(list.get(0));
        }
        for (int i = 1; i < length; i++) {
            buf.append(separator);
            buf.append(list.get(i));
        }

        return buf.toString();
    }

    /**
     * @param str
     * @param separator
     * @return
     */
    public static List<?> str2List(String str, String separator) {
        List<String> resultList = new ArrayList<>();
        String[] strs = getString(str, "").split(separator);
        for (String s : strs) {
            resultList.add(s);
        }

        return resultList;
    }

    /**
     * 获取非空string
     *
     * @param value
     * @param defaultVal
     * @return
     */
    public static String getString(Object value, String defaultVal) {
        return (value == null || value == "" ? defaultVal : value.toString());
    }

    /**
     * 判断Collection是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection) {
        return null == collection || collection.isEmpty();
    }

    /**
     * 判断字符串是为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return null == str || str.equals("");
    }

    /**
     * @param bundle
     * @param key
     * @return
     */
    public static Object getDataFromBundles(Bundle bundle, String key) {
        if (bundle != null && bundle.containsKey(key)) {
            return bundle.get(key);
        }
        return null;
    }

    /**
     * 手机号码验证
     *
     * @param editText
     * @return true:通过 false：不通过
     */
    public static boolean isValidPhone(EditText editText) {
        CharSequence target = editText.getText();
        if (TextUtils.isEmpty(target))
            return false;
        // ^1[0-9]{10}$放开手机号码检测只要是1开头的11位即可
        Pattern p = Pattern.compile("^(1)\\d{10}$");
        Matcher m = p.matcher(target);
        return m.matches();
    }


    /**
     * 判断是否中文
     *
     * @param str
     * @return
     */
    public boolean isChinese(String str) {

        char[] chars = str.toCharArray();
        boolean isGB2312 = false;
        for (int i = 0; i < chars.length; i++) {
            byte[] bytes = ("" + chars[i]).getBytes();
            if (bytes.length == 2) {
                int[] ints = new int[2];
                ints[0] = bytes[0] & 0xff;
                ints[1] = bytes[1] & 0xff;
                if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40 && ints[1] <= 0xFE) {
                    isGB2312 = true;
                    break;
                }
            }
        }
        return isGB2312;
    }

    /**
     * 从assets读取json文件
     * @param context
     * @param filePath
     * @return
     */
    public static String getJsonFromAsset(Context context, String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream is = null;
        BufferedReader bufferedReader = null;
        try {
            is = context.getAssets().open(filePath);
            bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
