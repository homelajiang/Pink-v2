package com.lxy.pink.ui.service;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.lxy.pink.data.model.location.BdLocation;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.data.source.PreferenceManager;
import com.lxy.pink.ui.base.BaseService;
import com.lxy.pink.utils.Config;
import com.orhanobut.logger.Logger;

/**
 * Created by yuan on 2016/10/22.
 */

public class PinkService extends BaseService implements PinkServiceContract.View {


    private PinkServiceContract.View serviceCallback;
    private PinkServiceContract.Presenter presenter;
    private boolean weatherRequestLocation;

    @Override
    public void onCreate() {
        super.onCreate();
        new PinkServicePresenter(this).subscribe();
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
        if (serviceCallback != null)
            serviceCallback.weatherLoaded(weather);
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
    public void locationLoaded(BdLocation bdLocation) {
        if (weatherRequestLocation && serviceCallback != null) {
            serviceCallback.locationLoaded(bdLocation);
            weatherRequestLocation = false;
            presenter.getWeatherByLocation(bdLocation.getLatitude(), bdLocation.getLongitude());
        }
    }

    @Override
    public void locationError() {
        if (weatherRequestLocation && serviceCallback != null) {
            serviceCallback.locationError();
            weatherRequestLocation = false;
            String cityId = PreferenceManager.getCityId(getContext());
            if (!TextUtils.isEmpty(cityId)) {
                presenter.getWeatherById(cityId);
            }
        }
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
            weatherRequestLocation = true;
            presenter.getLocation();
        }

        public void getTodoList() {
            presenter.getTodoList(getContentResolver());
        }

    }
}
