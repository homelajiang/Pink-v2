package com.lxy.pink.ui.home;

import android.content.Context;
import android.support.annotation.Nullable;

import com.airbnb.epoxy.EpoxyAdapter;
import com.lxy.pink.core.PinkService;
import com.lxy.pink.core.PinkServiceContract;
import com.lxy.pink.data.model.music.Song;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.player.IPlayback;
import com.lxy.pink.player.PlaybackService;
import com.lxy.pink.ui.home.imp.SimpleTodoCallback;
import com.lxy.pink.ui.home.model.PinkCalendarModel_;
import com.lxy.pink.ui.home.model.PinkMusicModel_;
import com.lxy.pink.ui.home.model.PinkWeatherModel_;

/**
 * Created by yuan on 2016/10/20.
 */

public class HomeAdapter extends EpoxyAdapter {

    private final Context context;
    private PinkServiceContract.Presenter presenter;

    private PlaybackService mPlayer;

    private PinkCalendarModel_ pinkCalendarModel_;
    private PinkWeatherModel_ pinkWeatherModel_;
    private PinkMusicModel_ pinkMusicModel_;
    private PinkService pinkService;
    private boolean pinkServiceBind;
    private PlaybackService playbackService;
    private boolean playServiceBind;

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

//        showModel(this.pinkWeatherModel_);

        showModels(false,
                this.pinkCalendarModel_,
                this.pinkMusicModel_,
                this.pinkWeatherModel_);
    }

    public void serviceBind(PinkService service) {
        this.pinkService = service;
        this.pinkServiceBind = true;
        //显示pinkWeather weather为null
        pinkWeatherModel_.pinkService(pinkService);
        showModel(pinkWeatherModel_);


        //注册回调，在item发生改变的时候调用
//        this.pinkService.bindWeatherCallback(new SimpleWeatherCallback() {
//            @Override
//            public void weatherLoaded(Weather weather) {
//                if (pinkWeatherModel_.isShown())
//                    return;
//                pinkWeatherModel_.pinkService(pinkService);
//                pinkWeatherModel_.weather(weather);
//                showModel(pinkWeatherModel_);
//            }
//        });

        this.pinkService.bindTodoCallback(new SimpleTodoCallback() {
            @Override
            public void todoListLoaded(TodoList todoList) {
                if (pinkCalendarModel_.isShown())
                    return;
                pinkCalendarModel_.pinkService(pinkService);
                pinkCalendarModel_.todoList(todoList);
                showModel(pinkCalendarModel_,todoList.size()>0);
            }
        });
    }

    public void playServiceBind(final PlaybackService playbackService) {
        this.playbackService = playbackService;
        this.playServiceBind = true;
        this.playbackService.registerCallback(new IPlayback.Callback() {
            @Override
            public void onSwitchLast(@Nullable Song last) {
                playServiceChanged(playbackService, this);
            }

            @Override
            public void onSwitchNext(@Nullable Song next) {
                playServiceChanged(playbackService, this);
            }

            @Override
            public void onComplete(@Nullable Song next) {
                playServiceChanged(playbackService, this);
            }

            @Override
            public void onPlayStatusChanged(boolean isPlaying) {
                playServiceChanged(playbackService, this);
            }
        });
    }

    public void serviceUnBind() {

    }

    public void playServiceUnBind() {

    }

    public void playServiceChanged(PlaybackService playbackService, IPlayback.Callback callback) {
        if (pinkMusicModel_.isShown())
            return;
        pinkMusicModel_.mPlayer(playbackService);
        showModel(pinkMusicModel_);
        playbackService.registerCallback(callback);
    }

}
