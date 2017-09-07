package com.example.winkey.mydemos.view.utils;

import android.text.TextUtils;

import com.google.gson.internal.bind.util.ISO8601Utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;


public class DateUtils {
    /*  某月的天数 */
    private static int daysOfMonth = 0;

    // 具体某一天是星期几
    private static int dayOfWeek = 0;
    /**
     * yyyy/MM/dd HH:mm:ss
     */
    public static SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss");
    /**
     * yyyy-MM-dd
     */
    public static SimpleDateFormat dateFormat1 = new SimpleDateFormat(
            "yyyy-MM-dd");
    /**
     * yyyy/MM/dd
     */
    public static SimpleDateFormat dateFormat2 = new SimpleDateFormat(
            "yyyy/MM/dd");
    /**
     * yyyy/MM/dd HH:mm
     */
    public static SimpleDateFormat dateFormat3 = new SimpleDateFormat(
            "yyyy/MM/dd HH:mm");
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static SimpleDateFormat dateFormat4 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    private static long prevTime;

    /**
     * 格式ISO8601格式数据日期+小时分钟
     *
     * @param str
     * @return
     */
    public static String formatOfMinute(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try {
            Date date = ISO8601Utils.parse(str, new ParsePosition(0));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int size = str.indexOf("T");
        return str.substring(0, size);
    }

    /**
     * 格式ISO8601格式数据日期
     *
     * @param str
     * @return
     */
    public static String formatOfDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date = ISO8601Utils.parse(str, new ParsePosition(0));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int size = str.indexOf("T");
        return str.substring(0, size);
    }

    // 判断是否为闰年 指定的年份
    public static boolean isLeapYear(int year) {
        if (year % 100 == 0 && year % 400 == 0) {
            return true;
        } else if (year % 100 != 0 && year % 4 == 0) {
            return true;
        }
        return false;
    }

    /**
     * 得到某月有多少天数
     *
     * @param isLeapyear 目标年份
     * @param month      目标月份      
     * @return          
     */
    public static int getDaysOfMonth(boolean isLeapyear, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                daysOfMonth = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                daysOfMonth = 30;
                break;
            case 2:
                if (isLeapyear) {
                    daysOfMonth = 29;
                } else {
                    daysOfMonth = 28;
                }
        }
        return daysOfMonth;
    }

    /**
     * 指定某年中的某月的第一天是星期几
     *
     * @param year  目标年份
     * @param month 目标月份
     * @return          
     */
    public static int getWeekdayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);
        dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return dayOfWeek;
    }

    /**
     * 获取当前年份与月份 返回日期数组，整形array[0]为年份，array[1]为月份, array[2]为日期          
     */
    public static int[] getCurrentYearAndMonth() {
        int[] result = new int[3];
        String str = "";
        Date date = new Date();
        str = dateFormat1.format(date); // 当期日期
        result[0] = Integer.parseInt(str.split("-")[0]);
        result[1] = Integer.parseInt(str.split("-")[1]);
        result[2] = Integer.parseInt(str.split("-")[2]);
        return result;
    }

    /**
     * 获取当前年份与月份 返回日期数组，整形array[0]为年份，array[1]为月份, array[2]为日期          
     */
    public static int[] getYearAndMonthFromDate(String date) throws ParseException {
        int[] result = new int[3];
        String str = "";
        str = dateFormat1.format(dateFormat1.parse(date)).toString(); // 当期日期
        result[0] = Integer.parseInt(str.split("-")[0]);
        result[1] = Integer.parseInt(str.split("-")[1]);
        result[2] = Integer.parseInt(str.split("-")[2]);
        return result;
    }

    /**
     * 从yyyy/MM/dd HH:mm:ss格式的时间提出日期
     */
    public static String getDay(String time) {
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = dateFormat.parse(time);
            calendar.setTime(date);
            return calendar.get(Calendar.DAY_OF_MONTH) + "";
        } catch (ParseException e) {
            return "";
        }
    }

    /**
     * 从yyyy-MM-dd HH:mm:ss格式的时间提出时间
     */
    public static int[] getTimes(String time) {
        if (TextUtils.isEmpty(time)) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        int[] result = new int[3];
        try {
            Date date = dateFormat4.parse(time);
            calendar.setTime(date);
            //小时
            result[0] = calendar.get(Calendar.HOUR_OF_DAY);
            //分钟
            result[1] = calendar.get(Calendar.MINUTE);
            //秒
            result[2] = calendar.get(Calendar.SECOND);
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从yyyy/MM/dd HH:mm:ss格式的时间转化成yyyy-MM-dd
     */
    public static String getDateString(String time,
                                       SimpleDateFormat oldDateFormat, SimpleDateFormat newDateFormat) {
        try {
            return newDateFormat.format(oldDateFormat.parse(time));
        } catch (ParseException e) {
            return "";
        }
    }


    /**
     * 获取当前月的最后一天
     *
     * @return
     * @Title: getCurrentLastDayOfMonth
     * @Description: TODO
     * @return: String
     */
    public static String getCurrentLastDayOfMonth() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH,
                ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = dateFormat1.format(ca.getTime());
        return last;
    }

    /**
     * 获取当前月的第一天
     *
     * @return
     * @Title: getCurrentFirstDayOfMonth
     * @Description: TODO
     * @return: String
     */
    public static String getCurrentFirstDayOfMonth() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        String first = dateFormat1.format(c.getTime());
        return first;
    }
    /**
     * 获取当前周的第一天
     *
     * @return
     * @Title: getCurrentFirstDayOfWeek
     * @Description: TODO
     * @return: String
     */
    public static String getCurrentFirstDayOfWeek() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, 2);// 设置为1号,周一为本周第一天
        String first = dateFormat1.format(c.getTime());
        return first;
    }
    /**
     * 获取今天日期
     *
     * @return
     * @Title: getToday
     * @Description: TODO
     * @return: String
     */
    public static String getToday() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 0);
        String first = dateFormat1.format(c.getTime());
        return first;
    }
    /**
     * 获取昨天日期
     *
     * @return
     * @Title: getYesterday
     * @Description: TODO
     * @return: String
     */
    public static String getYesterday() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        String first = dateFormat1.format(c.getTime());
        return first;
    }

    /**
     * 获取系统当前日期
     *
     * @return
     * @Title: getCurrentDay
     * @Description: TODO
     * @return: String
     */
    public static String getCurrentDay() {
        Calendar c = Calendar.getInstance();
        return dateFormat1.format(c.getTime());
    }


    /**
     * 获取系统明天日期
     *
     * @return
     * @Title: getCurrentDay
     * @Description: TODO
     * @return: String
     */
    public static String getTomorrowDay() {
        Calendar c = Calendar.getInstance();
        c.roll(Calendar.DAY_OF_YEAR, 1);
        return dateFormat1.format(c.getTime());
    }


    /**
     * 获取当前时间毫秒数
     *
     * @return
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static String getCurrentTime() {
        Calendar c = Calendar.getInstance();
        return dateFormat4.format(c.getTime());
    }

    /**
     * 获取指定月数 yyyy-mm-dd
     *
     * @param interval 正数增加几天，负数减去几天
     * @return
     */
    public static String getIntervalMonth(int interval) {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, interval);
        return dateFormat1.format(new Date(c.getTimeInMillis()));
    }

    /**
     * 获取当前日期至区间日期数据
     *
     * @param section
     * @return
     */
    public static List<String> getDateSectionList(int section) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < section; i++) {
            list.add(getIntervalMonth(i));
        }
        return list;
    }


    /**
     * @param dateAStr 日期a
     * @param dataBStr 日期b
     * @return -1 a小于b，0 等于 ,1 大于
     * @throws ParseException
     */
    public static int ACompareB(final String dateAStr, final String dataBStr) throws ParseException {
//        java.util.Date nowdate=new java.util.Date();
//        String myString = "2008-09-08";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd", Locale.CHINA);
        Date dataA = sdf.parse(dateAStr);
        Date dataB = sdf.parse(dataBStr);

        int flag = dataA.compareTo(dataB);
        return flag;
    }


    /**
     * 判断日期是星期几
     *
     * @param pTime
     * @return
     * @throws Throwable
     */
    public static int dayForWeek(String pTime) throws Throwable {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date tmpDate = format.parse(pTime);

        Calendar cal = new GregorianCalendar();

        cal.set(tmpDate.getYear(), tmpDate.getMonth(), tmpDate.getDay());

        return cal.get(Calendar.DAY_OF_WEEK);
    }


    /**
     * 计算日期相差天数
     *
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 分钟转天，时，分
     *
     * @param min
     * @return
     */
    public static String minConvertDayHourMin(Double min) {
        String html = "0分";
        if (min != null) {
            Double m = Math.abs((Double) min);
            String format;
            Object[] array;
            Integer days = (int) (m / (60 * 24));
            Integer hours = (int) (m / (60) - days * 24);
            Integer minutes = (int) (m - hours * 60 - days * 24 * 60);
            if (days > 0) {
                format = "%1$,d天%2$,d小时%3$,d分钟";
                array = new Object[]{days, hours, minutes};
            } else if (hours > 0) {
                format = "%1$,d小时%2$,d分钟";
                array = new Object[]{hours, minutes};
            } else {
                format = "%1$,d分钟";
                array = new Object[]{minutes};
            }
            html = String.format(format, array);
        }

        return html;
    }


    /**
     * 获取时间数组
     *
     * @param hourStart
     * @param minuteStart
     * @param hourEnd
     * @param divider
     * @return
     */
    public static List<String> getTimes(int hourStart, int minuteStart, int hourEnd, int divider) {
        List<String> timeList = new ArrayList<>();
        for (int i = hourStart; i < hourEnd; i++) {
            if (i == hourStart) {
                for (int j = minuteStart; j < 60; j += divider) {
                    timeList.add(assembly(i, j));
                }
            } else {
                for (int j = 0; j < 60; j += divider) {
                    timeList.add(assembly(i, j));
                }
            }

        }
        return timeList;
    }

    /**
     * 时间组装
     *
     * @param hour
     * @param minute
     * @return
     */
    private static String assembly(int hour, int minute) {
        String time = (String.valueOf(hour) + ":" +
                (minute == 0 ? "00" : String.valueOf(minute)));
        return time;
    }


    /**
     * 获取间隔
     *
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    public static String timesBetween(String start, String end) throws Exception {

        Calendar cal = Calendar.getInstance();
        cal.setTime(dateFormat4.parse(start));
        long time1 = cal.getTimeInMillis();
        cal.setTime(dateFormat4.parse(end));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1);

        return formatDuring(between_days);
    }


    /**
     * @param mss
     * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
     */
    public static String formatDuring(long mss) {
        String result = "";
        long days, hours, minutes, seconds;
        days = mss / (1000 * 60 * 60 * 24);
        if (days == 0) {
            hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            if (hours == 0) {
                minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
                if (minutes == 0) {
                    seconds = (mss % (1000 * 60)) / 1000;
                    result = seconds + "秒";
                } else {
                    result = minutes + "分";
                }

            } else {
                result = hours + "小时";
            }

        } else {
            result = days + "天";
        }

        return result;
    }

}
