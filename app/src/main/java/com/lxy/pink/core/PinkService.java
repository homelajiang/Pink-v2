package com.lxy.pink.core;

import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.lxy.pink.data.model.location.PinkLocation;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.ui.base.BaseService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuan on 2016/10/22.
 */

public class PinkService extends BaseService implements PinkServiceContract.WeatherCallback,
        PinkServiceContract.TodoCallback,
        PinkServiceContract.LocationCallback {


    private PinkServiceContract.Presenter presenter;
    private boolean weatherRequestLocation;
    private Weather lastWeather;

    private List<PinkServiceContract.WeatherCallback> weatherCallbacks = new ArrayList<>();
    private List<PinkServiceContract.TodoCallback> todoCallbacks = new ArrayList<>();
    private List<PinkServiceContract.LocationCallback> locationCallbacks = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        new PinkServicePresenter(this).subscribe();
        // TODO: 2017/2/6 0006 后台任务
//        registerCallback(CoreManager.getInstance());//添加后台计划任务
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PinkBinder();
    }

    public void bindWeatherCallback(PinkServiceContract.WeatherCallback weatherCallback) {
        if (!weatherCallbacks.contains(weatherCallback))
            weatherCallbacks.add(weatherCallback);
    }

    public void unBindWeatherCallback(PinkServiceContract.WeatherCallback weatherCallback) {
        weatherCallbacks.remove(weatherCallback);
    }

    public void bindTodoCallback(PinkServiceContract.TodoCallback todoCallback) {
        if (!todoCallbacks.contains(todoCallback))
            todoCallbacks.add(todoCallback);
    }

    public void unBindTodoCallback(PinkServiceContract.TodoCallback todoCallback) {
        todoCallbacks.remove(todoCallback);
    }

    public void bindLocationCallback(PinkServiceContract.LocationCallback locationCallback) {
        if (!locationCallbacks.contains(locationCallback))
            locationCallbacks.add(locationCallback);
    }

    public void unBindLocationCallback(PinkServiceContract.LocationCallback locationCallback) {
        locationCallbacks.remove(locationCallback);
    }

    public void clearCallback() {
        locationCallbacks.clear();
        todoCallbacks.clear();
        weatherCallbacks.clear();
    }

    @Override
    public void weatherLoadStart() {
        for (PinkServiceContract.WeatherCallback callback : weatherCallbacks) {
            callback.weatherLoadStart();
        }
    }

    @Override
    public void weatherLoadEnd() {
        for (PinkServiceContract.WeatherCallback callback : weatherCallbacks) {
            callback.weatherLoadEnd();
        }
    }

    @Override
    public void weatherLoadError(Throwable e) {
        for (PinkServiceContract.WeatherCallback callback : weatherCallbacks) {
            callback.weatherLoadError(e);
        }
    }

    @Override
    public void weatherLoaded(Weather weather) {
        lastWeather = weather;
        for (PinkServiceContract.WeatherCallback callback : weatherCallbacks) {
            callback.weatherLoaded(weather);
        }
    }

    @Override
    public void weatherLocationStart() {
        for (PinkServiceContract.WeatherCallback callback : weatherCallbacks) {
            callback.weatherLocationStart();
        }
    }

    @Override
    public void weatherLocationEnd() {
        for (PinkServiceContract.WeatherCallback callback : weatherCallbacks) {
            callback.weatherLocationEnd();
        }
    }

    @Override
    public void weatherLocationError() {
        for (PinkServiceContract.WeatherCallback callback : weatherCallbacks) {
            callback.weatherLocationError();
        }
    }

    @Override
    public void todoListLoaded(TodoList todoList) {
        for (PinkServiceContract.TodoCallback callback : todoCallbacks) {
            callback.todoListLoaded(todoList);
        }
    }

    @Override
    public void locationStart() {
        for (PinkServiceContract.LocationCallback callback : locationCallbacks) {
            callback.locationStart();
        }
        if (weatherRequestLocation) {
            weatherLoadStart();
        }
    }

    @Override
    public void locationLoaded(PinkLocation pinkLocation) {
        for (PinkServiceContract.LocationCallback callback : locationCallbacks) {
            callback.locationLoaded(pinkLocation);
        }
        if (weatherRequestLocation) {
            weatherLocationEnd();
            weatherRequestLocation = false;
            presenter.getWeather(pinkLocation.getLatitude() + ":" + pinkLocation.getLongitude());
        }
    }

    @Override
    public void locationError() {
        for (PinkServiceContract.LocationCallback callback : locationCallbacks) {
            callback.locationError();
        }
        if (weatherRequestLocation) {
            weatherLocationError();
            weatherRequestLocation = false;
            if (lastWeather != null && !TextUtils.isEmpty(lastWeather.getId())) {
                presenter.getWeather(lastWeather.getId());
            }
        }
    }

    @Override
    public void weatherLocationReq() {
        weatherRequestLocation = true;
    }

    @Override
    public void setPresenter(PinkServiceContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public class PinkBinder extends Binder {
        public PinkService getService() {
            return PinkService.this;
        }

        public void getWeather() {
            presenter.getWeather();
        }

        public void getTodoList() {
            presenter.getTodoList(getContentResolver());
        }

    }
}
