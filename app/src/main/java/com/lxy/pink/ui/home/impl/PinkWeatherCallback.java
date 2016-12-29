package com.lxy.pink.ui.home.impl;

import com.lxy.pink.data.model.weather.Weather;

/**
 * Created by homelajiang on 2016/12/29 0029.
 */

public interface PinkWeatherCallback {
    void weatherLoadStart();

    void weatherLoadEnd();

    void weatherLoadError(Throwable e);

    void weatherLoaded(Weather weather);
}
