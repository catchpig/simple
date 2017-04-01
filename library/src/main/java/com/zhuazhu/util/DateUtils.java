package com.zhuazhu.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by tao on 2016/12/20.
 */

public class DateUtils {
    /**
     * 得到当前日期是星期几。
     *
     * @return 当为周日时，返回0，当为周一至周六时，则返回对应的1-6。
     */
    public static final int dayOfWeek() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
    }
    /**
     * 获取日
     * @param
     * @return
     */
    public static int day(String date,String pattern){
        DateFormat df = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.get(Calendar.DATE);
    }
    /**
     * 获取日
     * @param calendar
     * @return
     */
    public static int day(Calendar calendar){
        return calendar.get(Calendar.DATE);
    }
    /**
     * 获取月
     * @param date
     * @return
     */
    public static int month(String date,String pattern){
        DateFormat df = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.get(Calendar.MONTH)+1;
    }
    /**
     * 获取月
     * @param calendar
     * @return
     */
    public static int month(Calendar calendar){
        return calendar.get(Calendar.MONTH)+1;
    }
    /**
     * 获取年
     * @param date
     * @return
     */
    public static int year(String date,String pattern){
        DateFormat df = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.get(Calendar.YEAR);
    }
    /**
     * 获取年
     * @param calendar
     * @return
     */
    public static int year(Calendar calendar){
        return calendar.get(Calendar.YEAR);
    }
    /**
     * 日期转换为字符串
     * @param date 日期
     * @param pattern 字符串日期格式
     * @return
     */
    public static String dateToString(Date date,String pattern){
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 字符串转换为日期
     * @param date 日期字符串
     * @param pattern 字符串日期格式
     * @return
     */
    public static Date stringToDate(String date,String pattern){
        DateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
    /**
     * 字符串转换为日期
     * @param date 日期字符串
     * @param pattern 字符串日期格式
     * @return
     */
    public static Calendar stringToCalendar(String date,String pattern){
        DateFormat df = new SimpleDateFormat(pattern);
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(df.parse(date));
            return calendar;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 日期字符串从一种格式转换为另一种格式
     * @param date 日期
     * @param pattern_old 当前字符串的日期格式
     * @param pattern_new 转换之后的日期格式
     * @return
     */
    public static String string(String date,String pattern_old,String pattern_new){
        DateFormat df_old = new SimpleDateFormat(pattern_old);
        DateFormat df_new = new SimpleDateFormat(pattern_new);
        try {
            Date d = df_old.parse(date);
            return df_new.format(d);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将长整型日期转化为字符串
     * @param date 长整型日期
     * @param pattern 日期格式
     * @return
     */
    public static String string(long date,String pattern){
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(new Date(date));
    }

    /**
     *
     * @param date
     * @param pattern
     * @return
     */
    public static long stringToLong(String date,String pattern){
        DateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(date).getTime();
        } catch (ParseException e) {
            return 0;
        }
    }
    /**
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String longToString(long date,String pattern){
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(new Date(date));
    }
    /**
     *
     * @param date
     * @param pattern
     * @return
     */
    public static long dateToLong(Date date,String pattern){
        DateFormat df = new SimpleDateFormat(pattern);
        try {
            String d = df.format(date);
            return df.parse(d).getTime();
        } catch (ParseException e) {
            return 0;
        }
    }
}
