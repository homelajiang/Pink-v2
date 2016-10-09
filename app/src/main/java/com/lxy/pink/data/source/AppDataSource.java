package com.lxy.pink.data.source;

import android.content.Context;

import com.lxy.pink.data.db.DaoSession;
import com.lxy.pink.data.model.auth.Auth;
import com.lxy.pink.data.model.weather.Forecast;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.data.retrofit.RetrofitAPI;

import rx.Observable;

/**
 * Created by homelajiang on 2016/10/9 0009.
 */

public class AppDataSource implements AppContract {
    private Context context;
    private DaoSession daoSession;

    public AppDataSource(Context context, DaoSession daoSession) {
        this.context = context;
        this.daoSession = daoSession;
    }

    @Override
    public Observable<Auth> signIn(String username, String password) {
        return RetrofitAPI.getInstance()
                .getRemoteService()
                .signIn(username, password);
    }

    @Override
    public Observable<Auth> signUp(String username, String password) {
        return RetrofitAPI.getInstance()
                .getRemoteService()
                .signUp(username, password);
    }

    @Override
    public Observable<Weather> getWeatherInfo(String cityId) {
        return null;
    }

    @Override
    public Observable<Forecast> getWeatherForecast(String cityId) {
        return null;
    }
}
