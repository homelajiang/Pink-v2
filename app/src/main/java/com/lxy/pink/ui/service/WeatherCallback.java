package com.lxy.pink.ui.service;

import com.lxy.pink.data.model.weather.Weather;

/**
 * Created by yuan on 2016/10/22.
 */

public interface WeatherCallback {
    void loadWeatherStart();

    void loadWeather(Weather weather);

    void loadWeatherError(Throwable e);

    void loadWeatherCompleted();
}
