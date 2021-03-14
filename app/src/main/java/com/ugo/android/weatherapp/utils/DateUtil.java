package com.ugo.android.weatherapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    public static String getDayFromEpoch(long unixTime) {
        long unixSeconds = unixTime;
        String formattedDate = "";
        Date date = new Date(unixSeconds*1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+4")); // give a timezone reference for formating (see comment at the bottom
        formattedDate = sdf.format(date);
        return formattedDate;
    }
}
