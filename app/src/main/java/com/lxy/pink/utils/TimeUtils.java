package com.lxy.pink.utils;

import android.annotation.SuppressLint;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by homelajiang on 2016/10/8 0008.
 */

public class TimeUtils {

    @SuppressLint("DefaultLocale")
    public static String formatDuration(int duration) {
        duration /= 1000; // milliseconds into seconds
        int minute = duration / 60;
        int hour = minute / 60;
        minute %= 60;
        int second = duration % 60;
        if (hour != 0)
            return String.format("%2d:%02d:%02d", hour, minute, second);
        else
            return String.format("%02d:%02d", minute, second);
    }

    @SuppressLint("DefaultLocale")
    public static String formatToTime(long milliseconds) {
        Date date = new Date(milliseconds);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return String.format("%02d:%02d",calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE));

    }
}
