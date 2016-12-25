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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuan on 2016/10/22.
 */

public class PinkService extends BaseService implements PinkServiceContract.View {


    private PinkServiceContract.Presenter presenter;
    private boolean weatherRequestLocation;
    private Weather lastWeather;
    private List<PinkServiceContract.View> mCallbacks = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        registerCallback(CoreManager.getInstance());
        new PinkServicePresenter(this).subscribe();
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


    public void registerCallback(PinkServiceContract.View serviceCallback) {
        if (!mCallbacks.contains(serviceCallback)) {
            mCallbacks.add(serviceCallback);
        }
    }

    public void unRegisterCallback(PinkServiceContract.View serviceCallback) {
        mCallbacks.remove(serviceCallback);
    }

    public void clearCallback() {
        mCallbacks.clear();
    }

    @Override
    public void weatherLoadStart() {
        for (PinkServiceContract.View callback : mCallbacks) {
            callback.weatherLoadStart();
        }
    }

    @Override
    public void weatherLoadEnd() {
        for (PinkServiceContract.View callback : mCallbacks) {
            callback.weatherLoadEnd();
        }
    }

    @Override
    public void weatherLoadError(Throwable e) {
        for (PinkServiceContract.View callback : mCallbacks) {
            callback.weatherLoadError(e);
        }
    }

    @Override
    public void weatherLoaded(Weather weather) {
        lastWeather = weather;
        for (PinkServiceContract.View callback : mCallbacks) {
            callback.weatherLoaded(weather);
        }
    }

    @Override
    public void todoListLoaded(TodoList todoList) {
        for (PinkServiceContract.View callback : mCallbacks) {
            callback.todoListLoaded(todoList);
        }
    }

    @Override
    public void locationStart() {
        for (PinkServiceContract.View callback : mCallbacks) {
            callback.locationStart();
        }
    }

    @Override
    public void locationLoaded(PinkLocation pinkLocation) {
        for (PinkServiceContract.View callback : mCallbacks) {
            callback.locationLoaded(pinkLocation);
        }
        if (weatherRequestLocation) {
            weatherRequestLocation = false;
            presenter.getWeather(pinkLocation.getLatitude(), pinkLocation.getLongitude());
        }
    }

    @Override
    public void locationError() {
        for (PinkServiceContract.View callback : mCallbacks) {
            callback.locationError();
        }
        if (weatherRequestLocation) {
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
        for (PinkServiceContract.View callback : mCallbacks) {
            callback.setPresenter(presenter);
        }
    }

    public class PinkBinder extends Binder {
        // TODO: 2016/12/25 合成为一个接口
        public PinkService getService() {
            return PinkService.this;
        }

        public void refreshNotifition() {

        }

        public void getWeather() {
            presenter.getWeather();
        }

        public void getTodoList() {
            presenter.getTodoList(getContentResolver());
        }

    }
}
