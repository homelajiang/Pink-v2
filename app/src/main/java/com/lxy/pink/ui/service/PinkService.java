package com.lxy.pink.ui.service;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.ui.base.BaseService;

/**
 * Created by yuan on 2016/10/22.
 */

public class PinkService extends BaseService implements WeatherContract.View {


    private WeatherPresenter weatherPresenter;
    private WeatherCallback weatherCallback;

    @Override
    public void onCreate() {
        super.onCreate();

        weatherPresenter = new WeatherPresenter(this);
        weatherPresenter.subscribe();
        //TODO weatherPresenter.unSubscribe();
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


    public void registerWeatherCallback(WeatherCallback weatherCallback) {
        this.weatherCallback = weatherCallback;
    }

    public void unRegisterWeatherCallback() {
        this.weatherCallback = null;
    }


    @Override
    public void showWeatherLoading() {
        if (weatherCallback != null)
            weatherCallback.loadWeatherStart();
    }

    @Override
    public void hideWeatherLoading() {
        if (weatherCallback != null)
            weatherCallback.loadWeatherCompleted();
    }

    @Override
    public void handleLoadWeatherError(Throwable e) {
        if (weatherCallback != null)
            weatherCallback.loadWeatherError(e);
    }

    @Override
    public void weatherLoad(Weather weather) {
        if (weatherCallback != null)
            weatherCallback.loadWeather(weather);
    }

    @Override
    public void setPresenter(WeatherContract.Presenter presenter) {
        //nothing to do
    }

    public class PinkBinder extends Binder {
        public PinkService getService() {
            return PinkService.this;
        }

        public void getWeatherInfo(String cityId) {
            weatherPresenter.getWeatherById(cityId);
        }
    }
}
