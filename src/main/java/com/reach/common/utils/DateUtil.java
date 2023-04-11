package com.reach.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
@Slf4j
public class DateUtil {

    private DateUtil() {
    }

    /**
     * current time
     * format: yyyy-MM-dd HH:mm:ss
     */
    public static String currentFormatTimeFromDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }


    /**
     * current system time
     *
     * @return
     */
    public static String currentSystemLongTimeAsString() {
        return String.valueOf(currentSystemTimeAsLong());
    }

    /**
     * current system time
     *
     * @return
     */
    public static Long currentSystemTimeAsLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current year and month
     */
    public static String currentYearAndMonth() {
        Calendar instance = Calendar.getInstance();
        StringBuffer buffer = new StringBuffer();
        buffer.append(instance.get(Calendar.YEAR));
        buffer.append(instance.get(Calendar.MONTH) + 1);
        return buffer.toString();
    }

    /**
     * Days a few days after the current date
     */
    public static String dateAfterCurrentDate(int num) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, num);
        date = calendar.getTime();
        return sdf.format(date);
    }

    /**
     * convert string time to timestamp
     */
    public static Long convertStringToLong(String time) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = (Date) sdf.parse(time);
            return date.getTime();
        } catch (ParseException e) {
            log.error("convertDateStringToLong:{}",e.getMessage());
        }
        return -1l;
    }
}
