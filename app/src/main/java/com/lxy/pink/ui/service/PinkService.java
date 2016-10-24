package com.lxy.pink.ui.service;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lxy.pink.data.model.todo.Todo;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.data.source.PreferenceManager;
import com.lxy.pink.ui.base.BaseService;

import java.util.List;

/**
 * Created by yuan on 2016/10/22.
 */

public class PinkService extends BaseService implements PinkServiceContract.View {


    private PinkServiceContract.View serviceCallback;
    private PinkServiceContract.Presenter presenter;

    @Override
    public void onCreate() {
        super.onCreate();
        new PinkServicePresenter(this).subscribe();
        // TODO unSubscribe
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
        if (serviceCallback != null)
            serviceCallback.weatherLoaded(weather);
    }

    @Override
    public void todoListLoaded(List<Todo> todoList) {
        if (serviceCallback != null)
            serviceCallback.todoListLoaded(todoList);
    }

    @Override
    public void setPresenter(PinkServiceContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public class PinkBinder extends Binder {
        public PinkService getService() {
            return PinkService.this;
        }

        public void getWeatherInfo() {
            presenter.getWeatherById(PreferenceManager.getCityId(getContext()));
        }

        public void getTodoList() {
            presenter.getTodoList(getContentResolver(), System.currentTimeMillis());
        }
    }
}
