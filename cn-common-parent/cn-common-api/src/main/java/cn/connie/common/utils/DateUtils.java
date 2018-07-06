package cn.connie.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 日期操作工具类.
 */

public final class DateUtils {

    public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_SECOND = "yyyy-MM-dd HH:mm";
    public static final String WEB_TIME_FORMAT = "yyyyMMddHHmmssSSS";
    public static final String FORMATDATA = "yyyy-MM-dd";
    public static final String FORMATDATA2 = "yyyy年MM月dd日";
    public static final String FORMATDATA3 = "yyyy年MM月dd日 HH:mm:ss";
    public static final String FORMATDATA4 = "MM月dd日 HH:mm";
    public static final String FORMATDATA5 = "yyyy.MM.dd";
    public static final String FORMATDATA6 = "MM月dd日";
    public static final String FORMATDAT7 = "yyyy.MM.dd HH:mm";
    public static final String FORMATTIME = "HH:mm";
    private static final String[] WEEKS = new String[]{"天", "一", "二", "三", "四", "五", "六"};
    public static final String FORMAT_DAILY = "MM-dd HH:mm";

    private DateUtils() {

    }

    public static Date str2Date(String str) {
        return str2Date(str, null);
    }

    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;

    }

    public static Calendar str2Calendar(String str) {
        return str2Calendar(str, null);

    }

    public static Calendar str2Calendar(String str, String format) {

        Date date = str2Date(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c;

    }

    public static String date2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
        return date2Str(c, null);
    }

    public static String date2Str(Calendar c, String format) {
        if (c == null) {
            return null;
        }
        return date2Str(c.getTime(), format);
    }

    public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
        return date2Str(d, null);
    }

    public static String isYeaterday(Date oldTime, Date newTime) throws Exception {
        if (newTime == null) {
            newTime = new Date();
        }
        // 将下面的 理解成 yyyy-MM-dd 00：00：00 更好理解点
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = format.format(newTime);
        Date today = format.parse(todayStr);
        // 昨天 86400000=24*60*60*1000 一天
        if ((today.getTime() - oldTime.getTime()) > 0 && (today.getTime() - oldTime.getTime()) <= 86400000) {
            return "昨天";
        } else if ((today.getTime() - oldTime.getTime()) <= 0) { // 至少是今天
            return date2Str(oldTime, "FORMATTIME");
        } else { // 至少是前天
            return date2Str(oldTime, "FORMATDATA");
        }

    }

    public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        if (format.equals("FORMATDATA")) {
            format = FORMATDATA;
        }
        if (format.equals("FORMATTIME")) {
            format = FORMATTIME;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }

    public static String getTimeByPattern(long time, String pattern) {
        Date d = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String s = sdf.format(d);
        return s;
    }

    public static String getCurDateStr() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH) + "-" + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
    }

    public static String getCurrentYear() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int intYear = c.get(Calendar.YEAR);
        return intYear + "";
    }

    public static int getCurrentMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int intYear = c.get(Calendar.MONTH);
        return intYear;
    }

    public static String getYear(long time) {
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        date.setTime(time);
        c.setTime(date);
        int intYear = c.get(Calendar.YEAR);
        return intYear + "";
    }

    public static String getCurrentWeek() {
        Calendar calendar = Calendar.getInstance();
        // 获取当前时间为本月的第几周
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        return week + "";
    }

    public static String getWeek(long time) {
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        date.setTime(time);
        c.setTime(date);
        int week = c.get(Calendar.WEEK_OF_MONTH);
        return week + "";
    }

    /**
     * 获得当前日期的字符串格式
     *
     * @param format
     * @return
     */
    public static String getCurDateStr(String format) {
        Calendar c = Calendar.getInstance();
        return date2Str(c, format);
    }

    public static String formatNetBirthDay(String netTime, String format) {
        try {
            Date date = new SimpleDateFormat(FORMATDATA).parse(netTime);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            return netTime;
        }
    }

    public static String formatNetTeamBuyDay(String netTime, String format) {
        try {
            Date date = new SimpleDateFormat(FORMAT).parse(netTime);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            return netTime;
        }
    }

    public static long formatNetTime(String netTime) {
        try {
            Date date = new SimpleDateFormat(FORMAT).parse(netTime);
            return date.getTime();
        } catch (Exception e) {
            return 0;
        }
    }

    // 格式到秒
    public static String getMillons(long time) {

        // return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
        return new SimpleDateFormat("yyyy-MM-dd ").format(time);

    }

    // 格式到秒
    public static String getMillon(long time) {

        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time);

    }

    // 格式到天
    public static String getDay(long time) {

        return new SimpleDateFormat("yyyy-MM-dd").format(time);

    }

    // 格式到毫秒
    public static String getSMillon(long time) {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time);
    }

    /***
     * 得到输入时间的时间戳
     *
     * @param time
     * @return
     */
    public static long getTimes(String time) {
        if (time == null) {
            return System.currentTimeMillis();
        }
        Date date = str2Date(time);
        return date.getTime();
    }


    public static String addOneDay(String date) {
        Date time = str2Date(date, "yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.DATE, 1);
        return (new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime());
    }


    /**
     * 根据过期时间计算出天数，默认是一天
     *
     * @param expiredSecond
     * @return
     */
    public static int getDayBySeconds(String expiredSecond) {
        Long integer_expiredSecond = Long.valueOf(expiredSecond);
        int dayTime = 60 * 60 * 24;
        if (integer_expiredSecond > dayTime) {
            return (int) (integer_expiredSecond / dayTime);
        } else {
            return 1;
        }
    }

    /**
     * 将 yyyy-MM-dd HH:mm:ss 转  yyyy-MM-dd HH:mm
     *
     * @param netTime
     * @return
     */
    public static String formatTimeByStr(String netTime, String format) {
        try {
            Date date = new SimpleDateFormat(FORMAT).parse(netTime);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            return netTime;
        }

    }

    /**
     * 将 yyyy-MM-dd HH:mm:ss 转  yyyy-MM-dd HH:mm
     *
     * @param netTime
     * @return
     */
    public static String formatTimeByStrWeek(String netTime) {
        try {
            Date date = new SimpleDateFormat(FORMAT).parse(netTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int week = calendar.get(Calendar.DAY_OF_WEEK);
            int index = week - 1;
            return "周" + WEEKS[index];
        } catch (Exception e) {
            return "周" + WEEKS[0];
        }

    }

    public static String formatDisplayTime(String netTime) {
        try {
            Date date = new SimpleDateFormat(FORMAT).parse(netTime);
            return formatDisplayTime(date.getTime());
        } catch (Exception e) {
            return netTime;
        }


    }

    /**
     * 当前时间距离发布时间，小于等于1分钟，显示【刚刚】
     * 当前时间距离发布时间，大于1分钟小于等于60分钟显示【xx分钟前】，示例：34分钟前；
     * 当前时间距离发布时间，大于1小时小于等于24小时显示【xx小时前】，示例：23小时前；
     * 当前时间距离发布时间，大于1天小于等于2天显示【昨天】
     * 当前时间距离发布时间，大于2天显示【x月x日】，示例：10-08
     */
    public static String formatDisplayTime(long time) {
        String display = "";
        int tMin = 60 * 1000;
        int tHour = 60 * tMin;
        int tDay = 24 * tHour;

        try {
            Date tDate = new Date();
            tDate.setTime(time);
            Date today = new Date();
            SimpleDateFormat thisYearDf = new SimpleDateFormat("yyyy");
            SimpleDateFormat todayDf = new SimpleDateFormat("yyyy-MM-dd");
            Date thisYear = new Date(thisYearDf.parse(thisYearDf.format(today)).getTime());
            Date yesterday = new Date(todayDf.parse(todayDf.format(today)).getTime());
            Date beforeYes = new Date(yesterday.getTime() - tDay);
            if (tDate != null) {
                SimpleDateFormat halfDf = new SimpleDateFormat("MM-dd");
                long dTime = today.getTime() - tDate.getTime();
                if (tDate.before(thisYear)) {
                    display = new SimpleDateFormat(FORMATDATA2).format(tDate);
                } else {
                    if (dTime < tMin) {
                        display = "刚刚";
                    } else if (dTime < tHour) {
                        display = (int) Math.ceil(dTime / tMin) + "分钟前";
                    } else if (dTime < tDay && tDate.after(yesterday)) {
                        display = (int) Math.ceil(dTime / tHour) + "小时前";
                    } else if (tDate.after(beforeYes) && tDate.before(yesterday)) {
                        display = "昨天" + new SimpleDateFormat("HH:mm").format(tDate);
                    } else {
                        display = halfDf.format(tDate);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return display;
    }

    public static boolean isGatherTwoDay(long timeDifference) {
        long showTime = 2 * 24 * 60 * 60 * 1000; //48小时
        return showTime > timeDifference && timeDifference > 0;
    }


    /**
     * 秒转化为天小时分秒字符串
     *
     * @param seconds
     * @return String
     */
    public static String formatSeconds(long seconds) {
        String timeStr = seconds + "秒";
        if (seconds > 60) {
            long second = seconds % 60;
            long min = seconds / 60;
            timeStr = min + "分" + second + "秒";
            if (min > 60) {
                min = (seconds / 60) % 60;
                long hour = (seconds / 60) / 60;
                timeStr = hour + "小时" + min + "分" + second + "秒";
                if (hour > 24) {
                    hour = ((seconds / 60) / 60) % 24;
                    long day = (((seconds / 60) / 60) / 24);
                    timeStr = day + "天" + hour + "小时" + min + "分" + second + "秒";
                }
            }
        }
        return timeStr;
    }

    /**
     * 计算某年某月有多少天
     *
     * @return
     */
    public static int getDateNum(String netTime) {
        try {
            Date date = new SimpleDateFormat(FORMAT).parse(netTime);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c.getActualMaximum(Calendar.DAY_OF_MONTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 31;
    }

    /**
     * 计算当前时间是哪一天
     *
     * @return
     */
    public static int getDay(String netTime) {
        try {
            Date date = new SimpleDateFormat(FORMAT).parse(netTime);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c.get(Calendar.DAY_OF_MONTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算当前时间是哪一个月
     *
     * @return
     */
    public static int getMonth(String netTime) {
        try {
            Date date = new SimpleDateFormat(FORMAT).parse(netTime);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c.get(Calendar.MONTH) + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算当前时间是哪一个年
     *
     * @return
     */
    public static int getYear(String netTime) {
        try {
            Date date = new SimpleDateFormat(FORMAT).parse(netTime);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c.get(Calendar.YEAR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算当前时间是哪一个年
     *
     * @return
     */
    public static int getCurrentDay(String netTime) {
        try {
            Date date = new SimpleDateFormat(FORMAT).parse(netTime);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c.get(Calendar.DAY_OF_MONTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算某年某月有多少天
     *
     * @param year  年份
     * @param month 月份 0-11
     * @return
     */
    public static int getDateNum(int year, int month) {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR, year + 1900);
        time.set(Calendar.MONTH, month);
        return time.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 比较两个日期的大小
     *
     * @param date1 第一个日期
     * @param date2 第二个日期
     * @return 1.同一天返回0；2.之前返回－1；3.之后返回1
     */
    public static int compareDateDay(Date date1, Date date2) {
        int year1 = date1.getYear() + 1900;
        int year2 = date2.getYear() + 1900;

        int month1 = date1.getMonth();
        int month2 = date2.getMonth();

        int day1 = date1.getDate();
        int day2 = date2.getDate();

        if (year1 > year2) {
            return 1;
        } else if (year1 < year2) {
            return -1;
        }

        if (month1 > month2) {
            return 1;
        } else if (month1 < month2) {
            return -1;
        }

        if (day1 > day2) {
            return 1;
        } else if (day1 < day2) {
            return -1;
        }

        return 0;
    }

    public static String getYearMonthDay(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMATDATA);
        return simpleDateFormat.format(calendar.getTime());
    }


    public static String getCurrentTime() {
        return String.valueOf(Calendar.getInstance().getTime().getTime());
    }
}
