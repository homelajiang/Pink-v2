package com.lxy.pink.data.source;

import android.content.ContentResolver;

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

import java.util.List;

import rx.Observable;

/**
 * Created by homelajiang on 2016/10/9 0009.
 */

public interface AppContract {

    //remote info
    /*Auth*/

    Observable<Auth> signIn(String username, String password);

    Observable<Auth> signUp(String username, String password);

    /**
     * Profile
     */

    Observable<Profile> getProfile(String profileId);

    Observable<Profile> updateProfile(Auth auth, Profile profile);

    /*PinkLocation*/

    Observable<BaseModel> updateLocation(Auth auth, List<PinkLocation> locationList);

    Observable<Void> saveLocation(PinkLocation location);

    // weather info

    /**
     * get current weather info
     */

    Observable<Weather> getWeatherInfo();

    Observable<Weather> getWeatherInfo(String location);

    Observable<Weather> saveWeatherInfo(Weather weather);

    /**
     * get forecast weather info
     */
    Observable<Forecast> getWeatherForecast(String cityId);

    /**
     * todoList
     */

    Observable<TodoList> getTodoList(ContentResolver cr);

    Observable<Void> updateTodo(ContentResolver cr, Todo todo);

    Observable<Void> deleteTodo(ContentResolver cr, String todoId);

    Observable<Void> insertTodo(ContentResolver cr, Todo todo);

    /**
     * playList
     *
     * @param filters
     * @return
     */
    Observable<PlayList> playList(List<String> filters);

    /**
     * video
     */
    Observable<ACRecommend> getRecommend();

    Observable<ACAuthRes> ac_login(String username, String password);

    Observable<ACProfile> ac_getProfile(String uid);

    /**
     * 检查是否签到
     *
     * @param access_token
     * @return
     */
    Observable<ACBaseModel> ac_checkSign(String access_token);

    /**
     * 签到
     *
     * @param access_token
     * @return
     */
    Observable<ACSign> ac_sign(String access_token);

    Observable<ACVideoInfo> getVideoInfo(int contentId);

    Observable<ACCheckFollow> checkFollow(int userId, String access_token);

    Observable<ACActionFollow> actionFollow(String name, int userId, String accessToken);

    Observable<ACVideoMark> checkMark(int contentId, int userId, String accessToken);

    Observable<ACVideoMark> actionMark(String name, int userId, int contentId, String accessToken);

    Observable<ACBananaInfo> getBananaInfo(String accessToken);

    Observable<ACBananaCheck> getBananaCheck(String accessToken);

    Observable<ACBananaPostRes> sendBanana(String accessToken, int userId, int count, int contentId);

    Observable<String> getDanmuku(int danmukuId);

    Observable<ACVideoComment> getVideoComment(int contentId, int pageNo);

    Observable<ACVideoCommentRes> sendComment(String text, int quoteId, int contentId, String accessToken, int userId, String captcha);

    Observable<ACVideoSearchLike> getVideoRecommend(String id);

    Observable<ACUserContribute> getUserContribute(int pageNo, int pageSize, int userId, int type, int sort);
}
