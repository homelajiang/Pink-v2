package com.lxy.pink.data.retrofit;

import com.lxy.pink.data.retrofit.api.RemoteService;
import com.lxy.pink.data.retrofit.api.WeatherService;

/**
 * Created by yuan on 2016/10/9.
 */

public interface RetrofitApimpl {

    RemoteService getRemoteService();

    WeatherService getWeatherService();
}
