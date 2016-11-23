package com.lxy.pink.data.source;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.google.gson.Gson;
import com.lxy.pink.data.db.DaoMaster;
import com.lxy.pink.data.db.DaoSession;
import com.lxy.pink.data.db.TempModelDao;
import com.lxy.pink.data.model.BaseModel;
import com.lxy.pink.data.model.TempModel;
import com.lxy.pink.data.model.auth.Auth;
import com.lxy.pink.data.model.auth.Profile;
import com.lxy.pink.data.model.location.PinkLocation;
import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.data.model.music.Song;
import com.lxy.pink.data.model.todo.Todo;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.data.model.weather.Forecast;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.data.retrofit.RetrofitAPI;
import com.lxy.pink.data.source.db.DaoMasterHelper;
import com.lxy.pink.utils.Config;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by homelajiang on 2016/10/9 0009.
 */

public class AppDataSource implements AppContract {
    private Context context;
    private DaoSession daoSession;
    private Gson gson;

    public AppDataSource(Context context, DaoSession daoSession) {
        this.context = context;
        this.daoSession = daoSession;
        this.gson = new Gson();
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
    public Observable<BaseModel> updateLocation(Auth auth, List<PinkLocation> locationList) {
        return null;
    }

    @Override
    public Observable<Weather> getWeatherInfo() {
        return Observable.create(new Observable.OnSubscribe<Weather>() {
            @Override
            public void call(Subscriber<? super Weather> subscriber) {
                List<TempModel> tempModelList =
                        DaoMasterHelper.getDaoSession().getTempModelDao().queryBuilder()
                                .where(TempModelDao.Properties.Type.eq(TempModel.TYPE_WEATHER))
                                .orderAsc(TempModelDao.Properties.UpdateTime)
                                .limit(1)
                                .list();
                if (tempModelList != null && tempModelList.size() == 1) {
                    TempModel tempModel = tempModelList.get(0);
                    Weather weather = gson.fromJson(tempModel.getRaw(), Weather.class);
                    subscriber.onNext(weather);
                } else {
                    subscriber.onNext(null);
                }
                subscriber.onCompleted();
            }
        });
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
    public Observable<Void> saveWeatherInfo(final Weather weather) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                TempModel tempModel = new TempModel();
                tempModel.setRaw(gson.toJson(weather));
                tempModel.setType(TempModel.TYPE_WEATHER);

                long updateTime = tempModel.getUpdateTime();

                    long id = DaoMasterHelper.getDaoSession().getTempModelDao().insert(tempModel);
                if (id < 0) {
                    subscriber.onError(new Throwable("insert location error"));
                    return;
                }

                DeleteQuery<TempModel> query =
                        DaoMasterHelper.getDaoSession().getTempModelDao().queryBuilder()
                                .where(TempModelDao.Properties.Type.eq(TempModel.TYPE_WEATHER)
                                        , TempModelDao.Properties.UpdateTime.notEq(updateTime))
                                .buildDelete();
                if (query == null) {
                    subscriber.onError(new Throwable("delete query TempModel Error!"));
                } else {
                    query.executeDeleteWithoutDetachingEntities();
                    subscriber.onNext(null);
                }
                subscriber.onCompleted();
            }
        });
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
    public Observable<PlayList> playList(List<String> filters) {
        return Observable.create(new Observable.OnSubscribe<PlayList>() {
            @Override
            public void call(Subscriber<? super PlayList> subscriber) {
                ContentResolver cr = context.getContentResolver();

                Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//                String options = MediaStore.Audio.Media.DATA+""


                Cursor musicCursor = cr.query(
                        songUri,
                        null,
                        null,
                        null,
                        MediaStore.Audio.Media._ID + " DESC");

                if (musicCursor == null) {
                    subscriber.onError(new Throwable("musicCursor is null"));
                    return;
                }
                List<Song> songList = new ArrayList<>();
                while (musicCursor.moveToNext()) {

                    int isMusic = musicCursor.getInt(musicCursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
                    if (isMusic != 0) {
                        Song song = new Song();
                        song.setId(musicCursor.getLong(musicCursor.getColumnIndex(MediaStore.Audio.Media._ID)));
                        song.setTitle(musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                        song.setArtist(musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                        song.setAlbum(musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
                        song.setAlbumId(musicCursor.getLong(musicCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)));
                        song.setDuration(musicCursor.getLong(musicCursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
                        song.setSize(musicCursor.getLong(musicCursor.getColumnIndex(MediaStore.Audio.Media.SIZE)));
                        song.setPath(musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                        songList.add(song);
                    }
                }
                musicCursor.close();
                Date date = new Date();
                PlayList playList = new PlayList(
                        0,
                        "default",
                        songList.size(),
                        true,
                        date,
                        date);
                playList.setSongs(songList);

                subscriber.onNext(playList);
                subscriber.onCompleted();
            }
        });
    }
}
