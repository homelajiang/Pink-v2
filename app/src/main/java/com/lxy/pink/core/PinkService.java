package com.lxy.pink.core;

import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lxy.pink.data.model.location.PinkLocation;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.ui.base.BaseService;

/**
 * Created by yuan on 2016/10/22.
 */

public class PinkService extends BaseService implements PinkServiceContract.View {


    private PinkServiceContract.View serviceCallback;
    private PinkServiceContract.Presenter presenter;
    private boolean weatherRequestLocation;
    private Weather lastWeather;

    @Override
    public void onCreate() {
        super.onCreate();
        new PinkServicePresenter(this).subscribe();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.getLocation();
            }
        }, 10000);
        // TODO_LIST unSubscribe
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


    public void registerWeatherCallback(PinkServiceContract.View weatherCallback) {
        this.serviceCallback = weatherCallback;
    }

    public void unRegisterWeatherCallback() {
        this.serviceCallback = null;
    }

    @Override
    public void weatherLoadStart() {
        if (serviceCallback != null)
            serviceCallback.weatherLoadStart();
    }

    @Override
    public void weatherLoadEnd() {
        if (serviceCallback != null)
            serviceCallback.weatherLoadEnd();
    }

    @Override
    public void weatherLoadError(Throwable e) {
        if (serviceCallback != null)
            serviceCallback.weatherLoadError(e);
    }

    @Override
    public void weatherLoaded(Weather weather) {
        lastWeather = weather;
        if (serviceCallback != null) {
            serviceCallback.weatherLoaded(weather);
        }
    }

    @Override
    public void todoListLoaded(TodoList todoList) {
        if (serviceCallback != null)
            serviceCallback.todoListLoaded(todoList);
    }

    @Override
    public void locationStart() {
        if (weatherRequestLocation && serviceCallback != null) {
            serviceCallback.locationStart();
        }
    }

    @Override
    public void locationLoaded(PinkLocation pinkLocation) {
        if (weatherRequestLocation && serviceCallback != null) {
            serviceCallback.locationLoaded(pinkLocation);
            weatherRequestLocation = false;
            presenter.getWeather(pinkLocation.getLatitude(), pinkLocation.getLongitude());
        }
    }

    @Override
    public void locationError() {
        if (weatherRequestLocation && serviceCallback != null) {
            serviceCallback.locationError();
            weatherRequestLocation = false;
            if (lastWeather != null) {
                presenter.getWeather(lastWeather.getCoord().getLat(), lastWeather.getCoord().getLon());
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
