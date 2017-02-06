package com.lxy.pink.ui.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAdapter;
import com.lxy.pink.R;
import com.lxy.pink.core.PinkService;
import com.lxy.pink.core.PinkServiceContract;
import com.lxy.pink.data.model.location.PinkLocation;
import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.data.model.music.Song;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.player.IPlayback;
import com.lxy.pink.player.PlayMode;
import com.lxy.pink.player.PlaybackService;
import com.lxy.pink.ui.home.model.PinkCalendarModel_;
import com.lxy.pink.ui.home.model.PinkMusicModel_;
import com.lxy.pink.ui.home.model.PinkWeatherModel_;
import com.lxy.pink.utils.Config;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuan on 2016/10/20.
 */

public class HomeAdapter extends EpoxyAdapter implements PinkServiceContract.View, IPlayback.Callback {

    private final Context context;
    private PinkServiceContract.Presenter presenter;

    private PlaybackService mPlayer;

    private PinkCalendarModel_ pinkCalendarModel_;
    private PinkWeatherModel_ pinkWeatherModel_;
    private PinkMusicModel_ pinkMusicModel_;

    public HomeAdapter(Context context) {
        enableDiffing();
        this.context = context;
        this.pinkCalendarModel_ = new PinkCalendarModel_();
        this.pinkWeatherModel_ = new PinkWeatherModel_();
        this.pinkMusicModel_ = new PinkMusicModel_();

        addModels(
                this.pinkWeatherModel_,
                this.pinkMusicModel_,
                this.pinkCalendarModel_
        );

        showModels(false,
                this.pinkCalendarModel_,
                this.pinkMusicModel_,
                this.pinkWeatherModel_);
    }

    @Override
    public void setPresenter(PinkServiceContract.Presenter presenter) {
        this.presenter = presenter;
        pinkWeatherModel_.presenter(this.presenter);
        pinkCalendarModel_.presenter(this.presenter);
    }

    public void setMusicService(PlaybackService mPlayer) {
        this.mPlayer = mPlayer;
        pinkMusicModel_.mPlayer(this.mPlayer);
    }

    @Override
    public void weatherLoadStart() {
        showModel(pinkWeatherModel_);
        pinkWeatherModel_.weatherLoadStart();
    }

    @Override
    public void weatherLoadEnd() {
        showModel(pinkWeatherModel_);
        pinkWeatherModel_.weatherLoadEnd();
    }

    @Override
    public void weatherLoadError(Throwable e) {
        showModel(pinkWeatherModel_);
        pinkWeatherModel_.weatherLoadError(e);
    }

    @Override
    public void weatherLoaded(Weather weather) {
        showModel(pinkWeatherModel_);
        pinkWeatherModel_.weatherLoaded(weather);
    }

    @Override
    public void todoListLoaded(TodoList todoList) {
        if (todoList.size() <= 0)
            return;
        showModel(pinkCalendarModel_);
        pinkCalendarModel_.todoListLoaded(todoList);
    }

    @Override
    public void locationStart() {
        if (pinkWeatherModel_.isShown()) {
            pinkWeatherModel_.locationStart();
        }
    }

    @Override
    public void locationLoaded(PinkLocation pinkLocation) {
        if (pinkWeatherModel_.isShown()) {
            pinkWeatherModel_.locationLoaded(pinkLocation);
        }
    }

    @Override
    public void locationError() {
    }

    @Override
    public void weatherLocationReq() {
        //nothing to do
    }


    @Override
    public void onSwitchLast(@Nullable Song last) {
        showModel(pinkMusicModel_);
        pinkMusicModel_.onSwitchLast(last);
    }

    @Override
    public void onSwitchNext(@Nullable Song next) {
        showModel(pinkMusicModel_);
        pinkMusicModel_.onSwitchNext(next);
    }

    @Override
    public void onComplete(@Nullable Song next) {
        showModel(pinkMusicModel_);
        pinkMusicModel_.onComplete(next);
    }

    @Override
    public void onPlayStatusChanged(boolean isPlaying) {
        showModel(pinkMusicModel_);
        pinkMusicModel_.onPlayStatusChanged(isPlaying);
    }
}
