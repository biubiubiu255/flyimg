package com.flyimg.comm.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

/**
 * 日期工具类
 *
 * @author yuanneng
 */

@Component
public class DateUtils {




    /** 系统默认 日期类型 yyyy-MM-dd */
    public static final String DATE_PATTERN_DEFAULT = "yyyy-MM-dd";

    /** 时间 日期类型 HH:mm:ss */
    public static final String DATE_PATTERN_TIME = "HH:mm:ss";

    /** 日期时间 日期类型 yyyy-MM-dd HH:mm:ss */
    public static final String DATE_PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";

    /** 时期格式 yyyy-MM-dd */
    public static DateFormat dateformater;

    /** 时间格式 HH:mm:ss */
    public static DateFormat timeformater;

    /** 日期时间格式 yyyy-MM-dd HH:mm */
    public static DateFormat dateTimeformater;

    static {
        if (DateUtils.dateformater == null) {
            DateUtils.dateformater = new SimpleDateFormat(DateUtils.DATE_PATTERN_DEFAULT);
        }
        if (DateUtils.timeformater == null) {
            DateUtils.timeformater = new SimpleDateFormat(DateUtils.DATE_PATTERN_TIME);
        }

        if (DateUtils.dateTimeformater == null) {
            DateUtils.dateTimeformater = new SimpleDateFormat(DateUtils.DATE_PATTERN_DATETIME);
        }
    }

    /**
     *
     * 根据 yyyy-MM-dd 字符串解析成相应的日期
     *
     * @param strDate
     *            yyyy-MM-dd 格式的日期
     *
     * @return 转换后的日期
     *
     */
    public static Date parseDate(String strDate) {
        Date date = null;
        if (org.apache.commons.lang3.StringUtils.isNotBlank(strDate)) {
            try {
                date = DateUtils.dateformater.parse(strDate);
            }
            catch (final Exception e) {
                e.printStackTrace();
                return date;
            }
        }
        return date;
    }

    /**
     *
     * 根据传入的日期格式 将字符串解析成 日期类型
     *
     * @param strDate
     *            日期字符串
     *
     * @param pattern
     *            日期格式 如果传入格式为空，则为默认格式 yyyy-MM-dd
     *
     * @return 日期类型
     *
     */
    public static Date parseDate(String strDate, String pattern) {
        Date date = null;
        try {
            final DateFormat format = DateUtils.getDateFormat(pattern);
            date = format.parse(strDate);
        }
        catch (final Exception e) {
            e.printStackTrace();
            return date;
        }
        return date;
    }

    /**
     *
     * 根据 HH:mm:ss 字符串解析成相应的时间格式
     *
     * @param strTime
     *            HH:mm:ss 格式的时间格式
     *
     * @return 转换后的时间
     *
     */
    public static Date parseTime(String strTime) {
        Date date = null;
        try {
            date = DateUtils.timeformater.parse(strTime);
        }
        catch (final Exception e) {
            e.printStackTrace();
            return date;
        }
        return date;
    }

    /**
     *
     * 根据yyyy-MM-dd HH:mm字符串解析成相应的日期时间
     * @param strDateTime 以"yyyy-MM-dd HH:mm:ss"为格式的时间字符串
     * @return 转换后的日期
     *
     */
    public static Date parseDateTime(String strDateTime) {
        Date date = new Date();
        try {
            date = DateUtils.dateTimeformater.parse(strDateTime);
        }
        catch (final Exception e) {
            e.printStackTrace();
            return date;
        }
        return date;
    }

    /**
     * 传入date转String
     */
    public static String dateToString(Date date) {
        return dateTimeformater.format(date);
    }

    /**
     * 传入date转String，支持自定义格式
     */
    public static String dateToString(Date date, String pattern) {
        return getDateFormat(pattern).format(date);
    }

    /**
     *
     * 根据传入的格式，获取日期格式化实例，如果传入格式为空，则为默认格式 yyyy-MM-dd
     * @param pattern 日期格式
     * @return 格式化实例
     *
     */
    public static DateFormat getDateFormat(String pattern) {
        if (org.apache.commons.lang3.StringUtils.isBlank(pattern.trim())) {
            return new SimpleDateFormat(DateUtils.DATE_PATTERN_DEFAULT);
        } else {
            return new SimpleDateFormat(pattern);
        }
    }


    /**
     * 获取当天的初始时间 00.00.00
     * @return
     */
    public static long getStartDay(){
        //当前时间毫秒数
        long current=System.currentTimeMillis();
        //今天零点零分零秒的毫秒数
        long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();
        return zero;
    }



    /**
     * 获取当天结束时间
     * @return
     */
    public static long getEndDay(){
        long startDay = getStartDay();
        //今天23点59分59秒的毫秒数
        long twelve=startDay+24*60*60*1000-1;
        return twelve;
    }

    /**
     * 获取当前时间的时分
     * @return
     */
    public static Integer getHourMinute(){
        Calendar cal1 = Calendar.getInstance();
        int hour = cal1.get(Calendar.HOUR_OF_DAY);
        int minute = cal1.get(Calendar.MINUTE);
        return hour*100+minute;
    }

    /**
     * 根据当前日期设置自定义时间
     * @param hour   时
     * @param minute        分
     * @param second        秒
     * @param millisecond   毫秒（0-999
     * @return  返回设置后的时间
     */
    public static Timestamp setCustomDate(int hour, int minute, int second,int millisecond){
        Date now = new Date();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(now);
        // 设置时分秒毫秒
        cal1.set(Calendar.HOUR_OF_DAY, hour);
        cal1.set(Calendar.MINUTE, minute);
        cal1.set(Calendar.SECOND, second);
        cal1.set(Calendar.MILLISECOND, millisecond);
        return  new Timestamp( cal1.getTimeInMillis());
    }

    /**
     * 获取当前时间的 小时加分钟 列如 1624  16点24分钟
     * @return
     */
    public static Integer getDayHourAndMinute(){
        Date now = new Date();
        SimpleDateFormat fileFormat = new SimpleDateFormat("HHmm");
        return Integer.valueOf(fileFormat.format(now));
    }


    /**
     * 获得当前日期 yyyyMMddHHmmss
     *
     * @return 20190827141240
     */
    public static String getTimeName(){
        SimpleDateFormat fileFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return fileFormat.format(new Date());
    }

    /**
     * 获得当前日期 yyyy-MM-dd HH:mm:ss
     *
     * @return 2019-08-27 14:12:40
     */
    public static String getCurrentTime() {
        // 小写的hh取得12小时，大写的HH取的是24小时
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return df.format(date);
    }

    /**
     * 获取系统当前时间戳
     *
     * @return 1566889186583
     */
    public static String getSystemTime() {
        return String.valueOf(System.currentTimeMillis());
    }


    /**
     * 获取当前日期 yy-MM-dd
     *
     * @return 2019-08-27
     */
    public static String getDateByString() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 得到两个时间差  格式yyyy-MM-dd HH:mm:ss
     *
     * @param start 2019-06-27 14:12:40
     * @param end   2019-08-27 14:12:40
     * @return 5270400000
     */
    public static long dateSubtraction(String start, String end) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = df.parse(start);
            Date date2 = df.parse(end);
            return date2.getTime() - date1.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 得到两个时间差
     *
     * @param start 开始时间
     * @param end 结束时间
     * @return
     */
    public static long dateTogether(Date start, Date end) {
        return end.getTime() - start.getTime();
    }



    /**
     * 转化long值的日期为yyyy-MM-dd  HH:mm:ss.SSS格式的日期
     *
     * @param millSec 日期long值  5270400000
     * @return 日期，以yyyy-MM-dd  HH:mm:ss.SSS格式输出 1970-03-03  08:00:00.000
     */
    public static String transferLongToDate(String millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss.SSS");
        Date date = new Date(Long.parseLong(millSec));
        return sdf.format(date);
    }

    /**
     * 获得当前日期 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getOkDate(String date) {
        try {
            if (StringUtils.isEmpty(date)) {
                return null;
            }
            Date date1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH).parse(date);
            //格式化
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(date1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    /**
     *java获取日期本周一时间0点时间
     */
    public static Date getMondayOfThisWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();

    }

    /**
     *java获取日期本周日时间接近24点时间
     */
    public static Date getSundayOfThisWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getMondayOfThisWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);

        Instant instant = cal.getTime().toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime

        LocalDate lastWeekEndlocal = instant.atZone(zoneId).toLocalDate();
        LocalDateTime localDateTime = lastWeekEndlocal.atTime(23, 59, 59, 999);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));

        return  Date.from(zonedDateTime.toInstant());
    }


    /**
     * 获取当前日期是一个星期的第几天
     *
     * @return 2
     */
    public static int getDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.DAY_OF_WEEK) - 1;
    }


    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime     当前时间
     * @param dateSection 时间区间   2018-01-08,2019-09-09
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, String dateSection) {
        try {
            String[] times = dateSection.split(",");
            String format = "yyyy-MM-dd";
            Date startTime = new SimpleDateFormat(format).parse(times[0]);
            Date endTime = new SimpleDateFormat(format).parse(times[1]);
            if (nowTime.getTime() == startTime.getTime()
                    || nowTime.getTime() == endTime.getTime()) {
                return true;
            }
            Calendar date = Calendar.getInstance();
            date.setTime(nowTime);

            Calendar begin = Calendar.getInstance();
            begin.setTime(startTime);

            Calendar end = Calendar.getInstance();
            end.setTime(endTime);

            if (isSameDay(date, begin) || isSameDay(date, end)) {
                return true;
            }
            if (date.after(begin) && date.before(end)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 != null && cal2 != null) {
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static long getTimeByDate(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(time);
            //日期转时间戳（毫秒）
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**
     * 获取当前日期前一天
     *
     * @return 2019-08-26
     */
    public static String getBeforeDay() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return sdf.format(date);
    }


    /**
     * 获取今天之前（-day天），或者今天之后（day天）
     * @param day   天
     * @return  返回日期
     */
    public static Date getAfterTomorrow(int day){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return date = calendar.getTime();
    }

    /**
     * 获取今天之前（-day天），或者今天之后（day天）
     * @param day   天
     * @return  返回日期
     */
    public static Date getAfterTomorrowTime(int day){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return date = calendar.getTime();
    }

    /**
     * 获取最近七天
     *
     * @return 2019-08-20
     */
    public static String getServen() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();

        c.add(Calendar.DATE, -7);

        Date monday = c.getTime();

        String preMonday = sdf.format(monday);

        return preMonday;
    }

    /**
     * 获取最近一个月
     *
     * @return 2019-07-27
     */
    public static String getOneMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();

        c.add(Calendar.MONTH, -1);

        Date monday = c.getTime();

        String preMonday = sdf.format(monday);

        return preMonday;
    }

    /**
     * 获取最近三个月
     *
     * @return 2019-05-27
     */
    public static String getThreeMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();

        c.add(Calendar.MONTH, -3);

        Date monday = c.getTime();

        String preMonday = sdf.format(monday);

        return preMonday;
    }

    /**
     * 获取最近一年
     *
     * @return 2018-08-27
     */
    public static String getOneYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -1);
        Date start = c.getTime();
        String startDay = sdf.format(start);
        return startDay;
    }


    private static int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
    /**
     * 获取今年月份数据
     * 说明 有的需求前端需要根据月份查询每月数据，此时后台给前端返回今年共有多少月份
     *
     * @return [1, 2, 3, 4, 5, 6, 7, 8]
     */
    public static List getMonthList(){
        List list = new ArrayList();
        for (int i = 1; i <= month; i++) {
            list.add(i);
        }
        return list;
    }

    /**
     * 返回当前年度季度list
     * 本年度截止目前共三个季度，然后根据1,2,3分别查询相关起止时间
     * @return [1, 2, 3]
     */
    public static List getQuartList(){
        int quart = month / 3 + 1;
        List list = new ArrayList();
        for (int i = 1; i <= quart; i++) {
            list.add(i);
        }
        return list;
    }

    public static String toGMTString(Long timmstampl){
        Calendar cd = Calendar.getInstance();
        cd.setTimeInMillis(timmstampl);
        SimpleDateFormat format = new SimpleDateFormat("EEE d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT")); // 设置时区为GMT
        String res = format.format(cd.getTime());
        return res;
    }



}
