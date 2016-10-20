package com.lxy.pink.data.source;


import com.lxy.pink.Injection;
import com.lxy.pink.data.model.BaseModel;
import com.lxy.pink.data.model.auth.Auth;
import com.lxy.pink.data.model.auth.Profile;
import com.lxy.pink.data.model.location.BdLocation;
import com.lxy.pink.data.model.weather.Forecast;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.data.source.db.DaoMasterHelper;

import java.util.List;

import rx.Observable;

/**
 * Created by homelajiang on 2016/10/9 0009.
 */

public class AppRepository implements AppContract {

    private static volatile AppRepository instance;
    private AppDataSource appDataSource;

    private AppRepository() {
        appDataSource = new AppDataSource(Injection.provideContext(), DaoMasterHelper.getDaoSession());
    }

    public static AppRepository getInstance() {
        if (instance == null) {
            synchronized (AppRepository.class) {
                if (instance == null) {
                    instance = new AppRepository();
                }
            }
        }
        return instance;
    }

    @Override
    public Observable<Auth> signIn(String username, String password) {
        return appDataSource.signIn(username, password);
    }

    @Override
    public Observable<Auth> signUp(String username, String password) {
        return appDataSource.signUp(username, password);
    }

    @Override
    public Observable<Profile> getProfile(String profileId) {
        return appDataSource.getProfile(profileId);
    }

    @Override
    public Observable<Profile> updateProfile(Auth auth, Profile profile) {
        return appDataSource.updateProfile(auth, profile);
    }

    @Override
    public Observable<BaseModel> updateLocation(Auth auth, List<BdLocation> locationList) {
        return appDataSource.updateLocation(auth, locationList);
    }


    @Override
    public Observable<Weather> getWeatherInfo(String cityId) {
        return appDataSource.getWeatherInfo(cityId);
    }

    @Override
    public Observable<Forecast> getWeatherForecast(String cityId) {
        return appDataSource.getWeatherForecast(cityId);
    }
}
