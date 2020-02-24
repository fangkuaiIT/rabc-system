package com.lin.utils;





import com.yyfly.common.exception.GlobalException;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author : fangkauiIT
 * @version : 1.0
 */
public class LocalDateUtils {

    /**
     * 默认使用系统当前时区
     */
    public static final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

    /**
     * 日期格式
     */
    public static final String MONTH_DATE_FORMAT = "yyyy-MM";
    /**
     * MONTH_DATE_FORMAT_REGEX
     */
    public static final String MONTH_DATE_FORMAT_REGEX = "^\\d{4}-\\d{1,2}$";
    /**
     * DAY_DATE_FORMAT_MYSQL
     */
    public static final String MONTH_DATE_FORMAT_MYSQL = "%Y-%m";

    /**
     * 日期格式
     */
    public static final String DAY_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 日期格式 regex
     */
    public static final String DAY_DATE_FORMAT_REGEX = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
    /**
     * DAY_DATE_FORMAT_MYSQL
     */
    public static final String DAY_DATE_FORMAT_MYSQL = "%Y-%m-%d";

    /**
     * 日期格式
     */
    public static final String MINUTE_DATE_FORMAT = "yyyy-MM-dd HH:mm";
    /**
     * 日期格式 regex
     */
    public static final String MINUTE_DATE_FORMAT_REGEX = "^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$";
    /**
     * MINUTE_DATE_FORMAT_MYSQL
     */
    public static final String MINUTE_DATE_FORMAT_MYSQL = "%Y-%m-%d %H:%i";

    /**
     * 日期格式
     */
    public static final String SECOND_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期格式 regex
     */
    public static final String SECOND_DATE_FORMAT_REGEX = "^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$";
    /**
     * SECOND_DATE_FORMAT_MYSQL
     */
    public static final String SECOND_DATE_FORMAT_MYSQL = "%Y-%m-%d %H:%i:%S";

    /**
     * 默认日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = SECOND_DATE_FORMAT;

    /**
     * 默认日期格式
     */
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);

    /**
     * 当前时间.
     *
     * @param pattern the pattern
     * @return the string
     * @author : fangkauiIT / 2019-04-08
     */
    public static String now(String pattern){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.now().format(dateTimeFormatter);
    }

    /**
     * Date 转 LocalDateTime.
     *
     * @param date the date
     * @return the local date time
     * @author : fangkauiIT / 2019-04-08
     */
    public static LocalDateTime dateToLocalDateTime(Date date){
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, DEFAULT_ZONE);
    }

    /**
     * Date 转 LocalDate.
     *
     * @param date the date
     * @return the local date
     * @author : fangkauiIT / 2019-04-08
     */
    public static LocalDate dateToLocalDate(Date date){
        return dateToLocalDateTime(date).toLocalDate();
    }

    /**
     * Date 转 LocalTime.
     *
     * @param date the date
     * @return the local time
     * @author : fangkauiIT / 2019-04-08
     */
    public static LocalTime dateToLocalTime(Date date){
        return dateToLocalDateTime(date).toLocalTime();
    }

    /**
     * LocalDateTime 转 Date.
     *
     * @param localDateTime the local date time
     * @return the date
     * @author : fangkauiIT / 2019-04-08
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime){
        Instant instant = localDateTime.atZone(DEFAULT_ZONE).toInstant();
        return Date.from(instant);
    }

    /**
     * LocalDate 转 Date.
     *
     * @param localDate the local date
     * @return the date
     * @author : fangkauiIT / 2019-04-08
     */
    public static Date localDateToDate(LocalDate localDate){
        Instant instant = localDate.atStartOfDay().atZone(DEFAULT_ZONE).toInstant();
        return Date.from(instant);
    }

    /**
     * 时间字符串 转 LocalDateTime.
     *
     * @param time the time
     * @return the local date time
     * @author : fangkauiIT / 2019-04-08
     */
    public static LocalDateTime stringToLocalDateTime(String time){
        if (time.matches(MINUTE_DATE_FORMAT_REGEX)){
            return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(MINUTE_DATE_FORMAT));
        }
        else if (time.matches(SECOND_DATE_FORMAT_REGEX)){
            return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(SECOND_DATE_FORMAT));
        }
        else {
            throw new GlobalException("时间格式不正确：" + time);
        }
    }

    /**
     * 时间字符串 转 LocalDate.
     *
     * @param time the time
     * @return the local date
     * @author : fangkauiIT / 2019-04-08
     */
    public static LocalDate stringToLocalDate(String time){
        if (time.matches(MONTH_DATE_FORMAT_REGEX)){
            return LocalDate.parse(time, DateTimeFormatter.ofPattern(MONTH_DATE_FORMAT));
        }
        else if (time.matches(DAY_DATE_FORMAT_REGEX)){
            return LocalDate.parse(time, DateTimeFormatter.ofPattern(DAY_DATE_FORMAT));
        }
        else {
            throw new GlobalException("时间格式不正确：" + time);
        }
    }

    /**
     * 获取时间戳.
     *
     * @param time the time
     * @return the long
     * @author : fangkauiIT / 2019-04-16
     */
    public static Instant getInstant(String time){
        if (time.matches(MINUTE_DATE_FORMAT_REGEX)){
            LocalDateTime localDateTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(MINUTE_DATE_FORMAT));
            return localDateTime.atZone(DEFAULT_ZONE).toInstant();
        }
        else if (time.matches(SECOND_DATE_FORMAT_REGEX)){
            LocalDateTime localDateTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(SECOND_DATE_FORMAT));
            return localDateTime.atZone(DEFAULT_ZONE).toInstant();
        }
        else if (time.matches(MONTH_DATE_FORMAT_REGEX)){
            LocalDate localDate = LocalDate.parse(time, DateTimeFormatter.ofPattern(MONTH_DATE_FORMAT));
            return localDate.atStartOfDay().atZone(DEFAULT_ZONE).toInstant();
        }
        else if (time.matches(DAY_DATE_FORMAT_REGEX)){
            LocalDate localDate = LocalDate.parse(time, DateTimeFormatter.ofPattern(DAY_DATE_FORMAT));
            return localDate.atStartOfDay().atZone(DEFAULT_ZONE).toInstant();
        }
        else {
            throw new GlobalException("时间格式不正确：" + time);
        }
    }

    /**
     * 通过时间字符串获取mysql时间格式.
     *
     * @param time the time
     * @return the string
     * @author : fangkauiIT / 2019-04-08
     */
    public static String getMySqlDateFormatByTime(String time){
        if (time.matches(MONTH_DATE_FORMAT_REGEX)){
            return MONTH_DATE_FORMAT_MYSQL;
        }
        else if (time.matches(DAY_DATE_FORMAT_REGEX)){
            return DAY_DATE_FORMAT_MYSQL;
        }
        else if (time.matches(MINUTE_DATE_FORMAT_REGEX)){
            return MINUTE_DATE_FORMAT_MYSQL;
        }
        else if (time.matches(SECOND_DATE_FORMAT_REGEX)){
            return SECOND_DATE_FORMAT_MYSQL;
        }
        else {
            throw new GlobalException("时间格式不正确：" + time);
        }
    }
}
