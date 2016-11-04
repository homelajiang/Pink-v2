package com.lxy.pink.data.source;


import android.content.ContentResolver;

import com.lxy.pink.Injection;
import com.lxy.pink.data.model.BaseModel;
import com.lxy.pink.data.model.auth.Auth;
import com.lxy.pink.data.model.auth.Profile;
import com.lxy.pink.data.model.location.BdLocation;
import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.data.model.todo.Todo;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.data.model.weather.Forecast;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.data.source.db.DaoMasterHelper;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by homelajiang on 2016/10/9 0009.
 */

public class AppRepository implements AppContract {

    private static volatile AppRepository instance;
    private AppDataSource appDataSource;
    private List<PlayList> mCachedPlayList;

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
    public Observable<Weather> getWeatherInfo(double lat, double lon) {
        return appDataSource.getWeatherInfo(lat, lon);
    }

    @Override
    public Observable<Forecast> getWeatherForecast(String cityId) {
        return appDataSource.getWeatherForecast(cityId);
    }

    @Override
    public Observable<TodoList> getTodoList(ContentResolver cr) {
        return appDataSource.getTodoList(cr);
    }

    @Override
    public Observable<Void> updateTodo(ContentResolver cr, Todo todo) {
        return appDataSource.updateTodo(cr, todo);
    }

    @Override
    public Observable<Void> deleteTodo(ContentResolver cr, String todoId) {
        return appDataSource.deleteTodo(cr, todoId);
    }

    @Override
    public Observable<Void> insertTodo(ContentResolver cr, Todo todo) {
        return appDataSource.insertTodo(cr, todo);
    }

    @Override
    public Observable<List<PlayList>> playList() {
        return appDataSource.playList()
                .doOnNext(new Action1<List<PlayList>>() {
                    @Override
                    public void call(List<PlayList> playLists) {
                        mCachedPlayList = playLists;
                    }
                });
    }

    @Override
    public List<PlayList> cachedPlayList() {
        if (mCachedPlayList == null) {
            return new ArrayList<>();
        }
        return mCachedPlayList;
    }

    @Override
    public Observable<PlayList> create(PlayList playList) {
        return appDataSource.create(playList);
    }

    @Override
    public Observable<PlayList> update(PlayList playList) {
        return appDataSource.update(playList);
    }

    @Override
    public Observable<PlayList> delete(PlayList playList) {
        return appDataSource.delete(playList);
    }
}
