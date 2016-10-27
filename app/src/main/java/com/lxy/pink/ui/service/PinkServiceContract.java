package com.lxy.pink.ui.service;

import android.content.ContentResolver;

import com.baidu.location.BDLocation;
import com.lxy.pink.data.model.location.BdLocation;
import com.lxy.pink.data.model.todo.Todo;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.ui.base.BasePresenter;
import com.lxy.pink.ui.base.BaseView;

import java.util.List;

/**
 * Created by yuan on 2016/10/23.
 */

public interface PinkServiceContract {
    interface View extends BaseView<Presenter> {
        void weatherLoadStart();

        void weatherLoadEnd();

        void weatherLoadError(Throwable e);

        void weatherLoaded(Weather weather);

        void todoListLoaded(TodoList todoList);

        void weatherLocationSuccess(double lat, double lon);

        void weatherLocationFail();
    }

    interface Presenter extends BasePresenter {
        void getWeatherById(String cityId);

        void getWeatherByLocation(double lat, double lon);

        void getTodoList(ContentResolver cr);
    }
}
