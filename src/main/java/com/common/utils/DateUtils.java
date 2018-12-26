/**
 * Project: guahao-portal-web-home
 *
 * File Created at 2012-9-26
 *
 * Copyright 2012 Greenline.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Greenline Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Greenline.com.
 */
package com.common.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期公共类
 */
public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
    private static final String DATE_FORMAT_HMS = "HH:mm:ss";
    private static final String DATE_FORMAT_HM = "HH:mm";
    private static final String DATE_FORMAT_MD = "MM-dd";
    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMATYYYYMMDD = "yyyyMMdd";
    public static final String FORMAT_YYYY_MM = "yyyy-MM";
    public static final String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_TIMESTAMP = "yyyyMMddHHmmssSSS";
    public static final String DATE_FORMAT_HH_MM = "HH:mm";
    public static final String DATE_FORMAT_HH_MM_SS = "HH:mm:ss";
    public static final int ONE_MINUTE = 60000;
    public static final int ONE_DAY = 86400000;

    private static String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    private static String[] weekDays2 = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    /**
     * 上下午数组
     */
    private final static String apms[] = {"上午", "下午", "晚上", "全天"};

    public interface DAY_SECTION {
        /**
         * 上午
         */
        public Integer APM_AM = 1;
        /**
         * 下午
         */
        public Integer APM_PM = 2;
        /**
         * 晚上
         */
        public Integer APM_NT = 3;
        /**
         * 全天
         */
        public Integer APM_FL = 4;
    }

    /**
     * 获取指定日期所在的周期序号，从0开始（当前日期的下一天为周期的起始日期，7天为一个周期，即一周）
     *
     * @param date 格式为yyyy-MM-dd的日期
     * @return 0、1、2...
     */
    public static int getIndexOfCycle(String date) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(df.parse(date));
            int b = getDays(new Date(), cal.getTime());
            int a = -1;
            if ((b % 7) == 0) {
                a = (b / 7) - 1;
            } else {
                a = b / 7;
            }
            return a;
        } catch (Exception e) {
            logger.error("DateUtils.getIndexOfCycle error!", e);
        }
        return -1;
    }

    /**
     * 获取指定日期所在的周期中的序号，从0开始（当前日期的下一天为周期的起始日期，7天为一个周期，即一周）
     *
     * @param date
     * @return int
     */
    public static int getIndexInCycle(String date) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR, 1); // 第二天
            int d = cal.get(Calendar.DAY_OF_WEEK); // 星期几
            cal.setTime(df.parse(date));
            int id = cal.get(Calendar.DAY_OF_WEEK); // 输入日期的星期几
            int idx = id - d;
            idx = idx < 0 ? idx + 7 : idx; // 一周有7天
            return idx;
        } catch (Exception e) {
            logger.error("异常原因为：{}",e);
        }
        return -1;
    }

    /**
     * @param oldFormatDate
     * @param type
     * @return int
     */
    public static String getNewFormatDate(String type, String oldFormatDate) {
        if (DATE_FORMAT_TIMESTAMP.equals(type)) {
            oldFormatDate = oldFormatDate.substring(0, 4) + "-" + oldFormatDate.substring(4, 6) + "-"
                    + oldFormatDate.substring(6, 8) + " " + oldFormatDate.substring(8, 10) + ":"
                    + oldFormatDate.substring(10, 12) + ":" + oldFormatDate.substring(12, 14);
        }
        return oldFormatDate;
    }

    /**
     * 计算两个日期的差距(天数)，去掉时分秒
     *
     * @param date1
     * @param date2
     * @return Long
     */
    public static int getDays(Date date1, Date date2) {
        Long d1 = getYmdTime(date1).getTime() / 1000;
        Long d2 = getYmdTime(date2).getTime() / 1000;
        Long result = Math.abs((d2 - d1)) / (24 * 3600);
        return Integer.valueOf(result.toString());
    }

    /**
     * 计算两个时间
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getMinutes(Date startDate, Date endDate) {

        // 毫秒ms
        long diff = endDate.getTime() - startDate.getTime();
        return diff / (60 * 1000) % 60;
    }

    /**
     * 去掉时分秒
     *
     * @param date
     * @return Date
     */
    public static Date getYmdTime(Date date) {
        if (date == null) {
            return (new Date());
        }
        Calendar day = Calendar.getInstance();
        day.setTime(date);
        day.set(Calendar.HOUR_OF_DAY, 0);
        day.set(Calendar.MINUTE, 0);
        day.set(Calendar.SECOND, 0);
        day.set(Calendar.MILLISECOND, 0);
        Date convertTime = day.getTime();
        return convertTime;
    }

    /**
     * 获取年月日时分秒
     *
     * @param date
     * @return Date
     */
    public static Date getYmdHms(Date date) {
        if (date == null) {
            return (new Date());
        }
        Calendar day = Calendar.getInstance();
        day.setTime(date);
        day.set(Calendar.HOUR_OF_DAY, 23);
        day.set(Calendar.MINUTE, 59);
        day.set(Calendar.SECOND, 59);
        Date convertTime = day.getTime();
        return convertTime;
    }

    /**
     * 根据时间类型获得上/下午
     *
     * @param timeType
     * @return
     */
    public static String getApmByTimeType(Integer timeType) {
        String apm = "";
        if (timeType == null) {
            return "";
        }
        if (DAY_SECTION.APM_AM.equals(timeType)) {
            apm = apms[0];
        } else if (DAY_SECTION.APM_PM.equals(timeType)) {
            apm = apms[1];
        } else if (DAY_SECTION.APM_NT.equals(timeType)) {
            apm = apms[2];
        } else if (DAY_SECTION.APM_FL.equals(timeType)) {
            apm = apms[3];
        }
        return apm;
    }

    /**
     * 根据上/下午获得时间类型
     *
     * @param time
     * @return
     */
    public static Integer getTimeTypeByTime(String time) {
        int type = 4;
        if (StringUtils.isEmpty(time)) {
            return type;
        }
        if (time.contains(apms[0])) {
            type = 1;
        } else if (time.contains(apms[1])) {
            type = 2;
        } else if (time.contains(apms[2])) {
            type = 3;
        } else if (time.contains(apms[3])) {
            type = 4;
        }
        return type;
    }

    /**
     * 短日期转变成长日期 20120102 转换成 2012-01-02
     *
     * @param theDate
     * @return
     */
    public static String convertShortDate(String theDate) {
        if (StringUtils.isNotBlank(theDate) && theDate.length() == 8) {
            return theDate.substring(0, 4) + "-" + theDate.substring(4, 6) + "-" + theDate.substring(6, 8);
        }
        return StringUtils.EMPTY;
    }

    /**
     * 字符串转换为日期
     *
     * @throws ParseException
     */
    public static Date getStringToDate(String date) throws ParseException {
        return new SimpleDateFormat("yyyyMMdd").parse(date);
    }

    /**
     * 用yyyyMMdd来格式化日期字符串
     *
     * @param s
     * @return 如果s==null或者格式异常，返回null
     */
    public static Date getYyyyMMddDate(String s) {
        return getDateByFormat("yyyy-MM-dd", s);
    }

    /**
     * 用yyyy-MM-dd来格式化日期字符串
     *
     * @param format
     * @param dateStr
     * @return 如果s==null或者格式异常，返回null
     */
    public static Date getDateByFormat(String format, String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        try {
            return new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
           logger.error("日期格式转换失败 ，format={} , dateStr={}",format,dateStr);
        }
        return null;
    }

    /**
     * 获取日期指定的格式的字符串
     *
     * @param date
     * @param pattern
     * @return Date 对象类型字符串形式
     */
    public static String formatDate(Date date, String pattern) {
        if (null != date) {
            DateFormat dateFomat = new SimpleDateFormat(pattern);
            return dateFomat.format(date);
        }
        return "";
    }

    /**
     * 包含时分秒的date类型
     *
     * @param date
     * @return Date
     */
    public static Date getYmdHmsDate(Date date) {
        DateFormat dateFomat = new SimpleDateFormat(DATE_FORMAT_YMDHMS);
        String dateStr = dateFomat.format(date);
        try {
            return dateFomat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取当前日期是星期几
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        if (date == null) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 获取当前日期是星期几
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate2(Date date) {
        if (date == null) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays2[w];
    }

    /**
     * 根据周几动态计算当前周的排班的日期
     *
     * @param weekday 1 周一， 2 周二 。。。7 周日
     * @return
     */
    public static String getDate(Integer weekday) {
        String date = null;
        if (null == weekday || weekday < 0 || weekday > 6) {
            logger.error("静态排班的weekday值不在正常范围内，请检查：[" + weekday + "]");
        } else {
            int d = weekday.intValue();
            d = d % 7 + 1; // Calendar类中周日为第一天，即1 周日， 2 周一 。。。7 周六，需要进行转换
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c = Calendar.getInstance();
            int nd = c.get(Calendar.DAY_OF_WEEK); // 当天的周期序号
            c.set(Calendar.DAY_OF_WEEK, d);
            if (d <= nd) { // 当天之前的（包括当天），取下周的这天
                c.add(Calendar.WEEK_OF_MONTH, 1);
            }
            date = sdf.format(c.getTime());
        }
        return date;
    }

    /**
     * 取得医院放号时间
     *
     * @param reserveOpentime
     */
    public static String getOpenTime(String reserveOpentime) {
        if ("00:00".equals(reserveOpentime)) {
            return reserveOpentime;
        }

        String openTime = StringUtils.EMPTY;
        try {
            if (StringUtils.isNotBlank(reserveOpentime)) {
                SimpleDateFormat osdf = new SimpleDateFormat("HHmm");
                SimpleDateFormat nsdf = new SimpleDateFormat("HH:mm");
                try {
                    openTime = nsdf.format(osdf.parse(reserveOpentime));
                } catch (ParseException e) {
                }
            }
        } catch (Exception e) {
            logger.error("获取放号时间异常：", e);
        }
        openTime = "".equals(openTime) ? "00:00" : openTime;

        return openTime;
    }

    /**
     * 用yyyy-MM-dd T HH:mm:ss Z来格式化日期字符串
     *
     * @param s
     * @return 如果s==null或者格式异常，返回null
     */
    public static Date getYyyyMMddTZDate(String s) {
        if (s == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(s);
        } catch (ParseException e) {
            logger.error("格式转化错误，string=" + s, e);
            return null;
        }
    }


    /**
     * 某时间点是否已经超过某整点 比如判断当前时间是否已经 过了下午5点
     *
     * @param source
     * @param hour
     * @return
     */
    public static boolean isAfterHour(Date source, int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(source);
        int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
        if (hourOfDay - hour >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 用yyyy-MM-dd 来格式化日期
     *
     * @param d
     * @return String
     * @author qinying
     */
    public static String getDateString(Date d, String template) {
        try {
            SimpleDateFormat destsmf = new SimpleDateFormat(template);
            return destsmf.format(d);
        } catch (Exception e) {
            logger.error("格式化日期出错", e);
        }
        return StringUtils.EMPTY;
    }

    /**
     * 用yyyyMMdd来格式化日期字符串
     *
     * @param s
     * @return 如果s==null或者格式异常，返回null
     */
    public static Date getYyyyMMddHHDate(String s) {
        if (s == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyyMMdd HH:mm:ss").parse(s);
        } catch (ParseException e) {
            logger.error("DateUtils.getYyyyMMddHHDate error!", e);
            return null;
        }
    }

    /**
     * 得到日期的中文,格式是8月3日8:00
     * @return
     */
    public static String getDateZW(Date date){
        try {
            SimpleDateFormat destsmf = new SimpleDateFormat("MMddHH:mm");
            String dateString = destsmf.format(date);
            int month = Integer.parseInt(dateString.substring(0, 2));
            int day = Integer.parseInt(dateString.substring(2, 4));
            String time = dateString.substring(4, 9);
            return new StringBuilder().append(month).append("月").append(day).append("日").append(time).toString();
        } catch (Exception e) {
            logger.error("解析日期格式出错，d=" + date, e);
        }
        return null;
    }
    /**
     * 获取上下午
     *
     * @return String
     * @author wangbiao
     */
    public static String getApm() {
        GregorianCalendar ca = new GregorianCalendar();
        Integer apm = ca.get(GregorianCalendar.AM_PM);
        return apm == 0 ? "上午" : "下午";
    }

    /**
     * 在年份基础上，增加年份
     * @param amount
     * @param amount
     * @return Date
     * @author wangbiao
     */
    public static Date addYear(Date date,int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, amount);
        return calendar.getTime();
    }
    /**
     * 在当前年份基础上，增加年份
     *
     * @param amount
     * @return Date
     * @author wangbiao
     */
    public static Date addYear(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, amount);
        return calendar.getTime();
    }

    /**
     * 在当前月份基础上增加月份
     *
     * @param amount
     * @return Date
     * @author wangbiao
     */
    public static Date addMonth(int amount) {
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MONTH, amount);
        return calendar.getTime();
    }
    public static Date addMonth(Date date,int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, amount);
        return calendar.getTime();
    }

    /**
     * 在当前日基础增加天数
     *
     * @param amount
     * @return Date
     * @author wangbiao
     */
    public static Date addDay(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        return calendar.getTime();
    }

    public static Date addDay(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        return calendar.getTime();
    }

    /**
     * 在当前小时基础增加小时
     *
     * @param amount
     * @return Date
     * @author wangbiao
     */
    public static Date addHour(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, amount);
        return calendar.getTime();
    }

    /**
     * 在当前小时基础增加小时
     *
     * @param amount
     * @return Date
     * @author wangbiao
     */
    public static Date addHour(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, amount);
        return calendar.getTime();
    }

    /**
     * 获取一个星期的起止日期显示 如：2013.01.14-2013.01.20
     *
     * @return String
     */
    public static String getOneWeekTime() {
        Calendar now = Calendar.getInstance();
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR, now.get(Calendar.YEAR));
        time.set(Calendar.MONTH, now.get(Calendar.MONTH));
        time.set(Calendar.DAY_OF_YEAR, now.get(Calendar.DAY_OF_YEAR));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

        StringBuffer result = new StringBuffer();
        result.append(sdf.format(time.getTime()).substring(5));
        result.append("-");
        time.add(Calendar.DAY_OF_YEAR, 6); // 一周
        result.append(sdf.format(time.getTime()).substring(5));
        return result.toString();
    }

    /**
     * 获取日期，格式为yyyy-MM-dd HH:mm:ss
     *
     * @param d
     * @return String
     */
    public static String getYMDHMSDateYmd(Date d) {
        if (null == d) {
            return StringUtils.EMPTY;
        }
        try {
            SimpleDateFormat destsmf = new SimpleDateFormat(DATE_FORMAT_YMDHMS);
            return destsmf.format(d);
        } catch (Exception e) {
            logger.error("格式化日期出错，date=" + d, e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * 获取日期，格式为yyyy-MM-dd HH:mm
     *
     * @param d
     * @return String
     */
    public static String getDateYmdHm(Date d) {
        if (null == d) {
            return StringUtils.EMPTY;
        }
        try {
            SimpleDateFormat destsmf = new SimpleDateFormat(DATE_FORMAT_YMDHM);
            return destsmf.format(d);
        } catch (Exception e) {
            logger.error("格式化日期出错，date=" + d, e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * 获取日期，格式为yyyy-MM-dd
     *
     * @param d
     * @return String
     */
    public static String getDateYmd(Date d) {
        if (null == d) {
            return StringUtils.EMPTY;
        }
        try {
            SimpleDateFormat destsmf = new SimpleDateFormat(DATE_FORMAT_YMD);
            return destsmf.format(d);
        } catch (Exception e) {
            logger.error("格式化日期出错，date=" + d, e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * 获取日期，格式为HH:mm:ss
     *
     * @param d
     * @return String
     */
    public static String getDateHms(Date d) {
        if (null == d) {
            return StringUtils.EMPTY;
        }
        try {
            SimpleDateFormat destsmf = new SimpleDateFormat(DATE_FORMAT_HMS);
            return destsmf.format(d);
        } catch (Exception e) {
            logger.error("格式化日期出错，date=" + d, e);
            return StringUtils.EMPTY;
        }
    }
    /**
     * 获取日期，格式为HH:mm
     *
     * @param d
     * @return String
     */
    public static String getDateHm(Date d) {
        if (null == d) {
            return StringUtils.EMPTY;
        }
        try {
            SimpleDateFormat destsmf = new SimpleDateFormat(DATE_FORMAT_HM);
            return destsmf.format(d);
        } catch (Exception e) {
            logger.error("格式化日期出错，date=" + d, e);
            return StringUtils.EMPTY;
        }
    }
    /**
     * 获取日期，格式为HH:mm:ss 今天显示HH:mm:ss 其他显示 月：日
     *
     * @param d
     * @return String
     */
    public static String getDateHmsTshu(Date d) {
        Date curTime = new Date();

        if (getDateYmd(curTime).equals(getDateYmd(d))) {
            return getDateHms(d);
        } else {
            try {
                SimpleDateFormat destsmf = new SimpleDateFormat(DATE_FORMAT_MD);
                return destsmf.format(d);
            } catch (Exception e) {
                logger.error("格式化日期出错，date=" + d, e);
                return StringUtils.EMPTY;
            }
        }

    }

    /**
     * 获取某一天的下一天
     *
     * @param date
     * @return Date
     */
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    /**
     * 获取未来某个月的1号，时间格式：yyyy-MM-dd 00:00:00
     *
     * @param monthLen
     * @return Date
     */
    public static Date getNextMonthTime(Integer monthLen) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
        cal.add(Calendar.MONTH, monthLen); // 将当前日期n一个月
        // 当前月的第一天
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date beginTime = cal.getTime();
        String beginTimes = datef.format(beginTime) + " 00:00:00";
        try {
            return datef.parse(beginTimes);
        } catch (ParseException e) {
            logger.error("时间解析出错", e);
        }

        return null;
    }

    /**
     * @param time 19990102格式
     * @return
     */
    public static Date getDateByString(String time) {
        try {
            SimpleDateFormat datef = new SimpleDateFormat("yyyyMMdd");
            Date date = datef.parse(time);
            return date;

        } catch (Exception e) {
            logger.error("时间解析出错", e);
        }
        return null;
    }

    @SuppressWarnings("static-access")
    public static Date getNextday() {
        Date date = new Date();// 取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String dateString = formatter.format(date);
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            logger.error("getNextday error", e);
        }
        return date;
    }

    /**
     * 取得前几年时间
     *
     * @return String
     */
    public static String getAppDate(int year) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        date = calendar.getTime();
        SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT_YMD);
        return sf.format(date);
    }

    /**
     * 用yyyyMMdd来格式化日期
     *
     * @author qinying
     * @param d
     * @return String
     */
    public static String getYmdDateString(Date d) {
        SimpleDateFormat destsmf = new SimpleDateFormat("yyyy MM dd");
        return destsmf.format(d);
    }

    /**
     * 获取日期，格式为yyyy年MM月dd日
     *
     * @author wangbiao
     * @param d
     * @return String
     */
    public static String getCurrentDateYmd(Date d) {
        SimpleDateFormat destsmf = new SimpleDateFormat("yyyy年MM月dd日");
        return destsmf.format(d);
    }


    /**
     * 用yyyyMMddHHmmssSSS来格式化日期字符串
     *
     * @param s
     * @return 如果s==null或者格式异常，返回null
     */
    public static Date getYyyyMMddHHmmssSSSConnectDate(String s) {
        if (s == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyyMMddHHmmssSSS").parse(s);
        } catch (ParseException e) {
            logger.error("格式化日期yyyyMMddHHmmssSSS出错", e);
            return null;
        }
    }

    /**
     * 获取日期，格式为yyyyMMddHHmmssSSS
     *
     * @param d
     * @return String
     */
    public static String getYMDHMS(Date d) {
        if (null == d) {
            return StringUtils.EMPTY;
        }
        try {
            SimpleDateFormat destsmf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            return destsmf.format(d);
        } catch (Exception e) {
            logger.error("格式化日期出错，date=" + d, e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * 用yyyy-MM-dd hh:mm:sss 来格式化日期
     *
     * @author qinying
     * @param d
     * @return String
     */
    public static String getYmdhmssDateString(Date d) {
        if (d == null) {
            return StringUtils.EMPTY;
        }
        try {
            SimpleDateFormat destsmf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return destsmf.format(d);
        } catch (Exception e) {
            logger.error("解析日期格式出错，d=" + d, e);
        }

        return StringUtils.EMPTY;
    }

    /**
     * 获取日期，格式为yyyy年MM月dd日
     *
     * @param d
     * @return String
     * @author wangbiao
     */
    public static String getDateYmdHms(Date d) {
        return getDateFormat(d, DATE_FORMAT_YMDHMS);
    }
    public static String getDateFormat(Date d, String format) {
        if (d == null || StringUtils.isBlank(format)) {
            return "";
        }
        SimpleDateFormat destsmf = new SimpleDateFormat(format);
        return destsmf.format(d);
    }

    /**
     * 获取每年初 2016-01-01
     *
     * @return Date
     */
    public static String  getStartTime() {

        Calendar day = Calendar.getInstance();
        Date date = new Date();
        day.setTime(date);
        day.set(Calendar.MONTH,0);
        day.set(Calendar.DAY_OF_MONTH,1);
        day.set(Calendar.HOUR_OF_DAY, 0);
        day.set(Calendar.MINUTE, 0);
        day.set(Calendar.SECOND, 0);
        day.set(Calendar.MILLISECOND, 0);
        Date convertTime = day.getTime();

        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_YMD);
        return dateFormat.format(convertTime);
    }

    /**
     * 获取每年初 2016-01-01
     *
     * @return Date
     */
    public static String getEndTime() {

        Calendar day = Calendar.getInstance();
        Date date = new Date();
        day.setTime(date);
        day.set(Calendar.MONTH,11);
        day.set(Calendar.DAY_OF_MONTH,31);
        day.set(Calendar.HOUR_OF_DAY, 0);
        day.set(Calendar.MINUTE, 0);
        day.set(Calendar.SECOND, 0);
        day.set(Calendar.MILLISECOND, 0);
        Date convertTime = day.getTime();

        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_YMD);
        return dateFormat.format(convertTime);
    }
    public static String stringTimestamp() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String tsStr = "";
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            //方法一
            tsStr = sdf.format(ts);
/*            //方法二
            tsStr = ts.toString();
            System.out.println(tsStr);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  tsStr;
    }

    public static long getLong(Date d){
        return d.getTime();
    }

    public static void main(String[] args) {

        getDateByFormat("FORMATYYYYMMDD","19900101");
    }
}
