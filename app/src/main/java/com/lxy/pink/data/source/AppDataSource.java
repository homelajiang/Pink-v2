package com.lxy.pink.data.source;

import android.content.Context;

import com.lxy.pink.data.db.DaoSession;
import com.lxy.pink.data.model.BaseModel;
import com.lxy.pink.data.model.auth.Auth;
import com.lxy.pink.data.model.auth.Profile;
import com.lxy.pink.data.model.location.BdLocation;
import com.lxy.pink.data.model.weather.Forecast;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.data.retrofit.RetrofitAPI;

import java.util.List;

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
    public Observable<Profile> getProfile(String profileId) {
        return RetrofitAPI.getInstance()
                .getRemoteService()
                .queryProfile(profileId);
    }

    @Override
    public Observable<Profile> updateProfile(Auth auth, Profile profile) {
        return RetrofitAPI.getInstance()
                .getRemoteService()
                .updateProfile(profile);
    }

    @Override
    public Observable<BaseModel> updateLocation(Auth auth, List<BdLocation> locationList) {
        return null;
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
