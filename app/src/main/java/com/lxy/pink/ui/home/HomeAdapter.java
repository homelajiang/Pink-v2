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

public class HomeAdapter extends EpoxyAdapter implements PinkServiceContract.View, IPlayback, IPlayback.Callback {

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
        if (pinkMusicModel_ == null) {
            pinkMusicModel_ = new PinkMusicModel_();
            addModel(pinkMusicModel_);
        } else {
            pinkMusicModel_.onSwitchLast(last);
        }
    }

    @Override
    public void onSwitchNext(@Nullable Song next) {
        if (pinkMusicModel_ == null) {
            pinkMusicModel_ = new PinkMusicModel_();
            addModel(pinkMusicModel_);
        } else {
            pinkMusicModel_.onSwitchNext(next);
        }
    }

    @Override
    public void onComplete(@Nullable Song next) {
        if (pinkMusicModel_ == null) {
            pinkMusicModel_ = new PinkMusicModel_();
            addModel(pinkMusicModel_);
        } else {
            pinkMusicModel_.onComplete(next);
        }
    }

    @Override
    public void onPlayStatusChanged(boolean isPlaying) {
        if (pinkMusicModel_ == null) {
            pinkMusicModel_ = new PinkMusicModel_();
            addModel(pinkMusicModel_);
        } else {
            pinkMusicModel_.onPlayStatusChanged(isPlaying);
        }
    }

    @Override
    public void setPlayList(PlayList list) {
        if (mPlayer != null)
            mPlayer.setPlayList(list);
    }

    @Override
    public boolean play() {
        if (mPlayer != null)
            return mPlayer.play();
        return false;
    }

    @Override
    public boolean play(PlayList list) {
        if (mPlayer != null)
            return mPlayer.play(list);
        return false;
    }

    @Override
    public boolean play(PlayList list, int startIndex) {
        if (mPlayer != null)
            return mPlayer.play(list, startIndex);
        return false;
    }

    @Override
    public boolean play(Song song) {
        if (mPlayer != null)
            return mPlayer.play(song);
        return false;
    }

    @Override
    public boolean playLast() {
        if (mPlayer != null)
            return mPlayer.playLast();
        return false;
    }

    @Override
    public boolean playNext() {
        if (mPlayer != null)
            return mPlayer.playNext();
        return false;
    }

    @Override
    public boolean pause() {
        if (mPlayer != null)
            return mPlayer.pause();
        return false;
    }

    @Override
    public boolean isPlaying() {
        if (mPlayer != null)
            return isPlaying();
        return false;
    }

    @Override
    public int getProgress() {
        if (mPlayer != null)
            return mPlayer.getProgress();
        return 0;
    }

    @Override
    public Song getPlayingSong() {
        if (mPlayer != null)
            return mPlayer.getPlayingSong();
        return null;
    }

    @Override
    public boolean seekTo(int progress) {
        if (mPlayer != null)
            return mPlayer.seekTo(progress);
        return false;
    }

    @Override
    public void setPlayMode(PlayMode playMode) {
        if (mPlayer != null)
            mPlayer.setPlayMode(playMode);
    }

    @Override
    public void registerCallback(Callback callback) {
//nothing to do
    }

    @Override
    public void unregisterCallback(Callback callback) {
//nothing to do
    }

    @Override
    public void removeCallbacks() {
//nothing to do
    }

    @Override
    public void releasePlayer() {
//nothing to do
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
