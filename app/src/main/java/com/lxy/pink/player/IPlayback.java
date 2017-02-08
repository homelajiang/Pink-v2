package com.lxy.pink.player;

import android.support.annotation.Nullable;

import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.data.model.music.Song;

/**
 * Created by homelajiang on 2016/11/2 0002.
 */

public interface IPlayback {

    void setPlayList(PlayList list);

    boolean play();

    boolean play(PlayList list);

    boolean play(PlayList list, int startIndex);

    boolean play(Song song);

    boolean playLast();

    boolean playNext();

    boolean pause();

    boolean isPlaying();

    int getProgress();

    Song getPlayingSong();

    boolean seekTo(int progress);

    void setPlayMode(PlayMode playMode);

    PlayMode getPlayMode();

    void switchPlayMode();

    void registerCallback(Callback callback);

    void unregisterCallback(Callback callback);

    void removeCallbacks();

    void releasePlayer();

    interface Callback {
        void onSwitchLast(@Nullable Song last);

        void onSwitchNext(@Nullable Song next);

        void onComplete(@Nullable Song next);

        void onPlayStatusChanged(boolean isPlaying);
    }
}
