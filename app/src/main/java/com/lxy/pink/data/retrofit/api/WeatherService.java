package com.lxy.pink.data.retrofit.api;

import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.data.model.weather.XZWeather;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yuan on 2016/10/9.
 */

public interface WeatherService {
//    @GET("data/2.5/weather")
//    Observable<Weather> getWeather(
//            @Query("id") String cityId,
//            @Query("APPID") String appId,
//            @Query("lang") String lang,
//            @Query("units") String units
//    );
//
//    @GET("data/2.5/weather")
//    Observable<Weather> getWeather(
//      @Query("lat") double lat,
//      @Query("lon") double lon,
//      @Query("APPID") String appId,
//      @Query("lang") String lang,
//      @Query("units") String units
//    );

    @GET("v3/weather/now.json")
    Observable<XZWeather> getWeather(
            @Query("location") String location,
            @Query("key") String key,
            @Query("language") String language,
            @Query("unit") String unit
    );

}
