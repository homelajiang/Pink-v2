package com.lxy.pink.data.retrofit.api;

import com.lxy.pink.data.model.weather.Weather;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yuan on 2016/10/9.
 */

public interface WeatherService {
    @GET("data/2.5/weather")
    Observable<Weather> getWeatherById(
            @Query("id") String cityId,
            @Query("APPID") String appId,
            @Query("lang") String lang,
            @Query("units") String units
    );
}
