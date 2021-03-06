package com.lxy.pink.data.source;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.google.gson.Gson;
import com.lxy.pink.Injection;
import com.lxy.pink.data.db.DaoSession;
import com.lxy.pink.data.db.WeatherDao;
import com.lxy.pink.data.model.BaseModel;
import com.lxy.pink.data.model.acfun.ACActionFollow;
import com.lxy.pink.data.model.acfun.ACAuthRes;
import com.lxy.pink.data.model.acfun.ACBananaCheck;
import com.lxy.pink.data.model.acfun.ACBananaInfo;
import com.lxy.pink.data.model.acfun.ACBananaPostRes;
import com.lxy.pink.data.model.acfun.ACBaseModel;
import com.lxy.pink.data.model.acfun.ACCheckFollow;
import com.lxy.pink.data.model.acfun.ACProfile;
import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.data.model.acfun.ACSign;
import com.lxy.pink.data.model.acfun.ACUserContribute;
import com.lxy.pink.data.model.acfun.ACVideoCommentData;
import com.lxy.pink.data.model.acfun.ACVideoCommentRes;
import com.lxy.pink.data.model.acfun.ACVideoInfo;
import com.lxy.pink.data.model.acfun.ACVideoMark;
import com.lxy.pink.data.model.acfun.ACVideoSearchLike;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by homelajiang on 2016/10/9 0009.
 */

public class AppDataSource implements AppContract {
    private Context context;
    private DaoSession daoSession;
    private Gson gson;

    public AppDataSource() {
        this.context = Injection.provideContext();
        this.daoSession = DaoMasterHelper.getDaoSession();
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
                .login(username, password, "token", "ELSH6ruK0qva88DD");
    }

    @Override
    public Observable<ACProfile> ac_getProfile(String uid) {
        return RetrofitAPI.getInstance()
                .getApiAppAcfunService()
                .getUserInfo(uid);
    }

    @Override
    public Observable<ACBaseModel> ac_checkSign(String access_token) {
        return RetrofitAPI.getInstance()
                .getWebapiAppAcfunService()
                .checkSign("1", access_token);
    }

    @Override
    public Observable<ACSign> ac_sign(String access_token) {
        return RetrofitAPI.getInstance()
                .getWebapiAppAcfunService()
                .sign(access_token, "1");
    }

    @Override
    public Observable<ACVideoInfo> getVideoInfo(int contentId) {
        return RetrofitAPI.getInstance()
                .getApiAixifanService()
                .getVideoInfo(contentId);
    }

    @Override
    public Observable<ACCheckFollow> checkFollow(int userId, String access_token) {
        return RetrofitAPI.getInstance()
                .getMobileAppAcfunService()
                .checkFollow("checkFollow", userId, access_token);
    }

    @Override
    public Observable<ACActionFollow> actionFollow(String name, int userId, String accessToken) {
        return RetrofitAPI.getInstance()
                .getMobileAppAcfunService()
                .actionFollow(name, userId, accessToken);
    }

    @Override
    public Observable<ACVideoMark> checkMark(int contentId, int userId, String accessToken) {
        return RetrofitAPI.getInstance()
                .getApiAppAcfunService()
                .checkVideoMark(contentId, userId, accessToken);
    }

    @Override
    public Observable<ACVideoMark> actionMark(String name, int userId, int contentId, String accessToken) {
        return RetrofitAPI.getInstance()
                .getApiAppAcfunService()
                .videoMark(name, accessToken, userId, contentId);
    }

    @Override
    public Observable<ACBananaInfo> getBananaInfo(String accessToken) {
        return RetrofitAPI.getInstance()
                .getMobileAppAcfunService()
                .getBananaInfo(accessToken);
    }

    @Override
    public Observable<ACBananaCheck> getBananaCheck(String accessToken) {
        return RetrofitAPI.getInstance()
                .getApiAixifanService()
                .checkBanana(accessToken);
    }

    @Override
    public Observable<ACBananaPostRes> sendBanana(String accessToken, int userId, int count, int contentId) {
        return RetrofitAPI.getInstance()
                .getMobileAppAcfunService()
                .actionBanana(accessToken, userId, count, contentId);
    }

    @Override
    public Observable<String> getDanmuku(int danmukuId) {
        return RetrofitAPI.getInstance()
                .getDanmuAixifanService()
                .getDadnmu(danmukuId, 0, 500);
    }

    @Override
    public Observable<ACVideoCommentData> getVideoComment(int contentId, int pageNo) {
        return RetrofitAPI.getInstance()
                .getMobileAppAcfunService()
                .getVideoComment(4, contentId, 50, pageNo);
    }

    @Override
    public Observable<ACVideoCommentRes> sendComment(String text, int quoteId, int contentId, String accessToken, int userId, String captcha) {
        return RetrofitAPI.getInstance()
                .getMobileAppAcfunService()
                .sendVideoComment(text, quoteId, contentId, "mobile", accessToken, userId, "");
    }

    @Override
    public Observable<ACVideoSearchLike> getVideoRecommend(String id) {
        return RetrofitAPI.getInstance()
                .getSearchAppAcfunService()
                .searchByVideoId(id, 10, 1, 1);
    }

    @Override
    public Observable<ACUserContribute> getUserContribute(int pageNo, int pageSize, int userId, int type, int sort) {
        return RetrofitAPI.getInstance()
                .getApiAppAcfunService()
                .getUserContribute(pageNo, pageSize, userId, type, sort);
    }
}
