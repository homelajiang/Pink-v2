package com.lxy.pink.core;

import android.content.ContentResolver;

import com.lxy.pink.data.model.location.PinkLocation;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.ui.base.BasePresenter;
import com.lxy.pink.ui.base.BaseView;

/**
 * Created by yuan on 2016/10/23.
 */

public interface PinkServiceContract {

    interface WeatherCallback extends BaseView<Presenter> {
        void weatherLoadStart();

        void weatherLoadEnd();

        void weatherLoadError(Throwable e);

        void weatherLoaded(Weather weather);

        void weatherLocationReq();
    }

    interface TodoCallback extends BaseView<Presenter> {
        void todoListLoaded(TodoList todoList);
    }

    interface LocationCallback extends BaseView<Presenter> {
        void locationStart();

        void locationLoaded(PinkLocation pinkLocation);

        void locationError();
    }


    interface Presenter extends BasePresenter {
        void getWeather();

        void saveWeather(Weather weather);

        void getWeather(double lat, double lon);

        void getTodoList(ContentResolver cr);

        void getLocation();

        void saveLocation(PinkLocation location);
    }
}
