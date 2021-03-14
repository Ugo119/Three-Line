package com.ugo.android.weatherapp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    public static String getDayFromEpoch(long unixTime) {
        long unixSeconds = unixTime;
        String formattedDate = "";
        Date date = new Date(unixSeconds*1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("EE"); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+4")); // give a timezone reference for formating (see comment at the bottom
        formattedDate = sdf.format(date);
        return formattedDate;
    }

    public static Date getDate(long unixTime) {
        Date date = null;
        date = new Date(unixTime*1000L);
        return date;
    }

    public static String getDayOfWeek(Date date) {
        String day = "";
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        day = String.valueOf(dayOfWeek);
        return day;
    }
}
