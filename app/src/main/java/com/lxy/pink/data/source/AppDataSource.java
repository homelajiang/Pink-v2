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
import com.lxy.pink.data.db.WeatherDao;
import com.lxy.pink.data.model.BaseModel;
import com.lxy.pink.data.model.TempModel;
import com.lxy.pink.data.model.acfun.ACAuthRes;
import com.lxy.pink.data.model.acfun.ACProfile;
import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.data.model.auth.Auth;
import com.lxy.pink.data.model.auth.Profile;
import com.lxy.pink.data.model.location.PinkLocation;
import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.data.model.music.Song;
import com.lxy.pink.data.model.todo.Todo;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.data.model.weather.Forecast;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.data.model.weather.XZWeather;
import com.lxy.pink.data.retrofit.RetrofitAPI;
import com.lxy.pink.data.source.db.DaoMasterHelper;
import com.lxy.pink.utils.Config;

import org.greenrobot.greendao.query.DeleteQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

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
    public Observable<Void> saveLocation(final PinkLocation location) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                long id = DaoMasterHelper.getDaoSession().getPinkLocationDao()
                        .insert(location);
                if (id < 0)
                    subscriber.onError(new Throwable("insert location error!!!"));
                subscriber.onNext(null);
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<Weather> getWeatherInfo() {

        return DaoMasterHelper.getDaoSession().getWeatherDao().queryBuilder()
                .orderAsc(WeatherDao.Properties.LastUpdate)
                .limit(1)
                .rx()
                .unique();
    }

    @Override
    public Observable<Weather> getWeatherInfo(String location) {
        return RetrofitAPI.getInstance()
                .getWeatherService()
                .getWeather(location,
                        Config.XINZHI_WEATHER_KEY,
                        Config.XINZHI_LANGUAGE,
                        Config.XINZHI_UNIT)
                .map(new Func1<XZWeather, Weather>() {
                    @Override
                    public Weather call(XZWeather xzWeather) {
                        return xzWeather.toWeather();
                    }
                });
    }

    @Override
    public Observable<Weather> saveWeatherInfo(final Weather weather) {

//        return DaoMasterHelper.getDaoSession().getWeatherDao()
//                .rx()
//                .deleteAll()
//                .flatMap(new Func1<Void, Observable<Weather>>() {
//                    @Override
//                    public Observable<Weather> call(Void aVoid) {
//                        return DaoMasterHelper.getDaoSession().getWeatherDao()
//                                .rx()
//                                .insert(weather);
//                    }
//                });

        return DaoMasterHelper.getDaoSession().getWeatherDao()
                .rx()
                .insertOrReplace(weather);

//        DaoMasterHelper.getDaoSession().getWeatherDao()
//                .rx()
//                .insert(weather)
//                .flatMap(new Func1<Weather, Observable<Weather>>() {
//                    @Override
//                    public Observable<Weather> call(Weather weather) {
//                        return DaoMasterHelper.getDaoSession().getWeatherDao()
//                                .rx()
//                                .delete;
//                    }
//                })
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

    @Override
    public Observable<ACRecommend> getRecommend() {
        return RetrofitAPI.getInstance()
                .getApiAixifanService()
                .getRecommend(0, -1);
    }

    @Override
    public Observable<ACAuthRes> ac_login(String username, String password) {
        return RetrofitAPI.getInstance()
                .getMobileAppAcfunService()
                .login(username,password,"token","ELSH6ruK0qva88DD");
    }

    @Override
    public Observable<ACProfile> ac_getProfile(String uid) {
        return RetrofitAPI.getInstance()
                .getApiAppAcfunService()
                .getUserInfo(uid);
    }
}
