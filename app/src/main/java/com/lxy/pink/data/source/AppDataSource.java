package com.lxy.pink.data.source;

import android.content.ContentResolver;
import android.content.Context;

import com.lxy.pink.PinkApplication;
import com.lxy.pink.R;
import com.lxy.pink.data.db.DaoSession;
import com.lxy.pink.data.db.PlayListDao;
import com.lxy.pink.data.model.BaseModel;
import com.lxy.pink.data.model.auth.Auth;
import com.lxy.pink.data.model.auth.Profile;
import com.lxy.pink.data.model.location.BdLocation;
import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.data.model.todo.Todo;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.data.model.weather.Forecast;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.data.retrofit.RetrofitAPI;
import com.lxy.pink.data.source.db.DaoMasterHelper;
import com.lxy.pink.utils.Config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

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
        return RetrofitAPI.getInstance()
                .getWeatherService()
                .getWeather(cityId, Config.WEATHER_APPID, Config.WEATHER_LANG, Config.WEATHER_UNITS);
    }

    @Override
    public Observable<Weather> getWeatherInfo(double lat, double lon) {
        return RetrofitAPI.getInstance()
                .getWeatherService()
                .getWeather(lat, lon, Config.WEATHER_APPID, Config.WEATHER_LANG, Config.WEATHER_UNITS);
    }

    @Override
    public Observable<Forecast> getWeatherForecast(String cityId) {
        return null;
    }

    @Override
    public Observable<TodoList> getTodoList(ContentResolver cr) {
        return RetrofitAPI.getInstance()
                .getTodoService()
                .queryTodo(cr);
    }

    @Override
    public Observable<Void> updateTodo(ContentResolver cr, Todo todo) {
        return RetrofitAPI.getInstance()
                .getTodoService()
                .updateTodo(cr, todo);
    }

    @Override
    public Observable<Void> deleteTodo(ContentResolver cr, String todoId) {
        return RetrofitAPI.getInstance()
                .getTodoService()
                .removeTodo(cr, todoId);
    }

    @Override
    public Observable<Void> insertTodo(ContentResolver cr, Todo todo) {
        return RetrofitAPI.getInstance()
                .getTodoService()
                .insertTodo(cr, todo);
    }

    @Override
    public Observable<List<PlayList>> playList() {
        return Observable.create(new Observable.OnSubscribe<List<PlayList>>() {
            @Override
            public void call(Subscriber<? super List<PlayList>> subscriber) {
                List<PlayList> playLists = daoSession
                        .getPlayListDao()
                        .queryBuilder()
                        .orderAsc(PlayListDao.Properties.CreatedAt)
                        .list();

                if (playLists == null)
                    playLists = new ArrayList<>();

                if (playLists.isEmpty()) {
                    PlayList tempPlayList = new PlayList();
                    tempPlayList.setName(context.getString(R.string.pink_local_play_list));
                    Date date = new Date();
                    tempPlayList.setCreatedAt(date);
                    tempPlayList.setUpdatedAt(date);
                    tempPlayList.setFavorite(true);
                    long id = daoSession.getPlayListDao().insert(tempPlayList);
                    if (id < 0) {
                        subscriber.onError(new Throwable("insert default playList error!"));
                    } else {
                        playLists.add(tempPlayList);
                        subscriber.onNext(playLists);
                        subscriber.onCompleted();
                    }
                } else {
                    subscriber.onNext(playLists);
                    subscriber.onCompleted();
                }
            }
        });
    }

    @Override
    public List<PlayList> cachedPlayList() {
        return null;
    }

    @Override
    public Observable<PlayList> create(PlayList playList) {
        return null;
    }

    @Override
    public Observable<PlayList> update(PlayList playList) {
        return null;
    }

    @Override
    public Observable<PlayList> delete(PlayList playList) {
        return null;
    }
}
