package com.lxy.pink.ui.home;

import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.ui.base.BasePresenter;
import com.lxy.pink.ui.base.BaseView;

/**
 * Created by yuan on 2016/10/20.
 */

public interface WeatherContract {

    interface View extends BaseView<Presenter> {
        void showLoading();

        void hideLoading();

        void handleLoadWeatherError(Throwable e);

        void weatherLoad(Weather weather);
    }

    interface Presenter extends BasePresenter {
        void getWeatherById(String cityId);
    }

}
