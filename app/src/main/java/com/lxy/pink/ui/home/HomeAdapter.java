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
import com.lxy.pink.data.model.music.Song;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.player.IPlayback;
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
    }


    @Override
    public void setPresenter(PinkServiceContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void setMusicService(PlaybackService mPlayer) {
        this.mPlayer = mPlayer;
        pinkMusicModel_ = new PinkMusicModel_();
        addModel(pinkMusicModel_);
    }

    @Override
    public void weatherLoadStart() {
        if (pinkWeatherModel_ != null) {
            pinkWeatherModel_.weatherLoadStart();
        }
    }

    @Override
    public void weatherLoadEnd() {
        if (pinkWeatherModel_ != null)
            pinkWeatherModel_.weatherLoadEnd();
    }

    @Override
    public void weatherLoadError(Throwable e) {
        if (pinkWeatherModel_ != null)
            pinkWeatherModel_.weatherLoadError(e);
    }

    @Override
    public void weatherLoaded(Weather weather) {

        if (pinkWeatherModel_ != null) {
            pinkWeatherModel_.weatherLoaded(weather);
        } else {
            pinkWeatherModel_ = new PinkWeatherModel_();
            pinkWeatherModel_
                    .presenter(presenter)
                    .weather(weather);
            addModel(pinkWeatherModel_);
        }
    }

    @Override
    public void todoListLoaded(TodoList todoList) {

        if (pinkCalendarModel_ == null) {
            pinkCalendarModel_ = new PinkCalendarModel_();
            pinkCalendarModel_
                    .presenter(presenter)
                    .todoList(todoList);
            addModel(pinkCalendarModel_);
        } else {
            pinkCalendarModel_.todoListLoaded(todoList);
        }
    }

    @Override
    public void locationStart() {
    }

    @Override
    public void locationLoaded(PinkLocation pinkLocation) {
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

    }

    @Override
    public void onSwitchNext(@Nullable Song next) {

    }

    @Override
    public void onComplete(@Nullable Song next) {

    }

    @Override
    public void onPlayStatusChanged(boolean isPlaying) {

    }


    static class OtherViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.test)
        TextView test;

        OtherViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
