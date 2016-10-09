package com.lxy.pink.data.source;

import com.lxy.pink.data.model.auth.Auth;
import com.lxy.pink.data.model.weather.Forecast;
import com.lxy.pink.data.model.weather.Weather;

import rx.Observable;

/**
 * Created by homelajiang on 2016/10/9 0009.
 */

public interface AppContract {

    //remote info
    /*Auth*/

    Observable<Auth> signIn(String username,String password );

    Observable<Auth> signUp(String username,String password );

    // weather info
/**
 * get current weather info
 */
    Observable<Weather> getWeatherInfo(String cityId);

/**
 * get forecast weather info
 */
    Observable<Forecast> getWeatherForecast(String cityId);
    // playList

    // Song

    // Folder
}
