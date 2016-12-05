package com.infrastructure.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/11.
 */
public class TimeUtil {

    /**
 * yyyy-MM-dd HH:mm:ss
 */
@SuppressLint("SimpleDateFormat")
public static String getNowYMDHMSTime() {
    SimpleDateFormat mDateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    return mDateFormat.format(new Date());
}

    /**
     * MM-dd HH:mm:ss
     */
    @SuppressLint("SimpleDateFormat")
    public static String getNowMDHMSTime() {
        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "MM-dd HH:mm:ss");
        return mDateFormat.format(new Date());
    }

    /**
     * MM-dd
     */
    @SuppressLint("SimpleDateFormat")
    public static String getNowYMD() {

        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        return mDateFormat.format(new Date());
    }

    /**
     * yyyy-MM-dd
     */
    @SuppressLint("SimpleDateFormat")
    public static String getYMD(Date date) {

        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        return mDateFormat.format(date);
    }

    @SuppressLint("SimpleDateFormat")
    public static String getMD(Date date) {

        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "MM-dd");
        return mDateFormat.format(date);
    }


    /**
     * 获取今天是一周的周几
     * */
    public static int getTodayWeekDay(){
        return java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_WEEK);
    }


    public static int getWeekOfDatePos(Date date) {
        String[] weekDays = {"周一", "周二", "周三", "周四", "周五", "周六","周日"};

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if(1 == dayWeek) {
            dayWeek = 6;
        }else{
            dayWeek = dayWeek - 2;
        }

        return dayWeek;
    }


    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"周一", "周二", "周三", "周四", "周五", "周六","周日"};

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if(1 == dayWeek) {
            dayWeek = 6;
        }else{
            dayWeek = dayWeek - 2;
        }

        return weekDays[dayWeek];
    }

    public static ArrayList<Date> getWeekDaysListWithDate(Date date){
        ArrayList<Date> dates = new ArrayList<>();

        for (int i = 1;i<8;i++){
            dates.add(getDayOfWeekTime(date,i));
        }

        return dates;
    }

    public static Date getNextDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date date1 = new Date(calendar.getTimeInMillis());
        return date1;
    }

    public static Date getPreDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date date1 = new Date(calendar.getTimeInMillis());
        return date1;
    }

    public static String getTimeDateStr(Date date){

        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "MM/dd");

        return getWeekOfDate(date) + " " + mDateFormat.format(date);
    }

    public static String getMonthDateStr(Date date){

        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "MM");

        String monthStr = mDateFormat.format(date);

        if (monthStr.startsWith("0")){
            monthStr = monthStr.substring(1,2);
        }


        return monthStr + "月";
    }


    public static Date getDayOfWeekTime(Date targetDate,int weekDay){

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(targetDate);

        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if(1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }

        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, weekDay - 1);

        return cal.getTime();
    }


}
