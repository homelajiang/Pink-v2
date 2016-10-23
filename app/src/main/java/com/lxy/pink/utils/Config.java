package com.lxy.pink.utils;

/**
 * Created by homelajiang on 2016/10/8 0008.
 */

public class Config {

    public static final String HOST = "http://23.105.215.126:3000/api/v1/";
    public static final int HOST_SUCCESS_CODE = 100;

    public static final String HOST_WEATHER = "http://api.openweathermap.org/";
    public static final String HOST_WEATHER_IMG = "http://23.105.215.126:3000/api/v1/weather/";
    public static final String WEATHER_APPID = "eef3b026ac92deef6a61290b5c734960";
    public static final String WEATHER_LANG = "zh_cn";
    public static final String WEATHER_UNITS = "metric";


    public static final int HOST_WEATHER_SUCCESS_CODE = 200;

    public static final long SPLASH_DELAY = 500;
    public static long DEFAULT_LOCATE_DELAY = 30 * 1000;
    public static final long LOCATION_UPLOAD_DELAY = 2 * 60 * 1000;

    public static final long LOCATION_CLEAN_DELAY = 10 * 60 * 1000;
    public static final String DB_NAME = "pink_db";

    public static final String SP_NAME = "pink_sp";
}
