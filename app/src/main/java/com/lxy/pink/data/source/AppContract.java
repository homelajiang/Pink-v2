package com.lxy.pink.data.source;

import android.content.ContentResolver;

import com.lxy.pink.data.model.BaseModel;
import com.lxy.pink.data.model.acfun.ACRecommend;
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

    Observable<Weather> getWeatherInfo(double lat, double lon);

    Observable<Void> saveWeatherInfo(Weather weather);

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
}
