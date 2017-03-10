package com.lxy.pink.data.source;


import android.content.ContentResolver;

import com.lxy.pink.Injection;
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
import com.lxy.pink.data.model.acfun.ACVideoComment;
import com.lxy.pink.data.model.acfun.ACVideoCommentRes;
import com.lxy.pink.data.model.acfun.ACVideoInfo;
import com.lxy.pink.data.model.acfun.ACVideoMark;
import com.lxy.pink.data.model.acfun.ACVideoSearchLike;
import com.lxy.pink.data.model.auth.Auth;
import com.lxy.pink.data.model.auth.Profile;
import com.lxy.pink.data.model.location.PinkLocation;
import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.data.model.todo.Todo;
import com.lxy.pink.data.model.todo.TodoList;
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
    public Observable<BaseModel> updateLocation(Auth auth, List<PinkLocation> locationList) {
        return appDataSource.updateLocation(auth, locationList);
    }

    @Override
    public Observable<Void> saveLocation(PinkLocation location) {
        return appDataSource.saveLocation(location);
    }

    @Override
    public Observable<Weather> getWeatherInfo() {
        return appDataSource.getWeatherInfo();
    }

    @Override
    public Observable<Weather> getWeatherInfo(String location) {
        return appDataSource.getWeatherInfo(location);
    }

    @Override
    public Observable<Weather> saveWeatherInfo(Weather weather) {
        return appDataSource.saveWeatherInfo(weather);
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
    public Observable<PlayList> playList(List<String> filters) {
        return appDataSource.playList(filters);
    }

    @Override
    public Observable<ACRecommend> getRecommend() {
        return appDataSource.getRecommend();
    }

    @Override
    public Observable<ACAuthRes> ac_login(String username, String password) {
        return appDataSource.ac_login(username, password);
    }

    @Override
    public Observable<ACProfile> ac_getProfile(String uid) {
        return appDataSource.ac_getProfile(uid);
    }

    @Override
    public Observable<ACBaseModel> ac_checkSign(String access_token) {
        return appDataSource.ac_checkSign(access_token);
    }

    @Override
    public Observable<ACSign> ac_sign(String access_token) {
        return appDataSource.ac_sign(access_token);
    }

    @Override
    public Observable<ACVideoInfo> getVideoInfo(int contentId) {
        return appDataSource.getVideoInfo(contentId);
    }

    @Override
    public Observable<ACCheckFollow> checkFollow(int userId, String access_token) {
        return appDataSource.checkFollow(userId, access_token);
    }

    @Override
    public Observable<ACActionFollow> actionFollow(String name, int userId, String accessToken) {
        return appDataSource.actionFollow(name, userId, accessToken);
    }

    @Override
    public Observable<ACVideoMark> checkMark(int contentId, int userId, String accessToken) {
        return appDataSource.checkMark(contentId, userId, accessToken);
    }

    @Override
    public Observable<ACVideoMark> actionMark(String name, int userId, int contentId, String accessToken) {
        return appDataSource.actionMark(name, userId, contentId, accessToken);
    }

    @Override
    public Observable<ACBananaInfo> getBananaInfo(String accessToken) {
        return appDataSource.getBananaInfo(accessToken);
    }

    @Override
    public Observable<ACBananaCheck> getBananaCheck(String accessToken) {
        return appDataSource.getBananaCheck(accessToken);
    }

    @Override
    public Observable<ACBananaPostRes> sendBanana(String accessToken, int userId, int count, int contentId) {
        return appDataSource.sendBanana(accessToken, userId, count, contentId);
    }

    @Override
    public Observable<String> getDanmuku(int danmukuId) {
        return appDataSource.getDanmuku(danmukuId);
    }

    @Override
    public Observable<ACVideoComment> getVideoComment(int contentId, int pageNo) {
        return appDataSource.getVideoComment(contentId,pageNo);
    }

    @Override
    public Observable<ACVideoCommentRes> sendComment(String text, int quoteId, int contentId, String accessToken, int userId, String captcha) {
        return appDataSource.sendComment(text, quoteId, contentId, accessToken, userId, captcha);
    }

    @Override
    public Observable<ACVideoSearchLike> getVideoRecommend(String id) {
        return appDataSource.getVideoRecommend(id);
    }

    @Override
    public Observable<ACUserContribute> getUserContribute(int pageNo, int pageSize, int userId, int type, int sort) {
        return appDataSource.getUserContribute(pageNo, pageSize, userId, type, sort);
    }
}
