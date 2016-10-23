package com.lxy.pink.ui.service;

import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.ui.base.BasePresenter;
import com.lxy.pink.ui.base.BaseView;

/**
 * Created by yuan on 2016/10/20.
 */

public interface WeatherContract {

    interface View extends BaseView<Presenter> {
        void showWeatherLoading();

        void hideWeatherLoading();

        void handleLoadWeatherError(Throwable e);

        void weatherLoad(Weather weather);
    }

    interface Presenter extends BasePresenter {
        void getWeatherById(String cityId);
    }

}
