package com.lxy.pink.player;

import android.media.MediaPlayer;

import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.data.model.music.Song;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by homelajiang on 2016/11/2 0002.
 */

public class Player implements MediaPlayer.OnCompletionListener, IPlayback, MediaPlayer.OnPreparedListener {
    private static final String TAG = "Player";
    private static volatile Player sInstance;

    private MediaPlayer mPlayer;
    private PlayList mPlayList = new PlayList();
    private PlayMode mPlayMode = PlayMode.LIST;

    private List<Callback> mCallbacks = new ArrayList<>(2);

    private boolean isPaused;
    private int playProgress;

    private Player() {
        mPlayer = new MediaPlayer();
        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnCompletionListener(this);
    }

    public static Player getInstance() {
        if (sInstance == null) {
            synchronized (Player.class) {
                if (sInstance == null) {
                    sInstance = new Player();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Song next = null;
        if (mPlayMode == PlayMode.LIST && mPlayList.getPlayingIndex() == mPlayList.getNumOfSongs() - 1) {

        } else if (mPlayMode == PlayMode.SINGLE) {
            next = mPlayList.getCurrentSong();
            play();
        } else {
            boolean hasNext = mPlayList.hasNext(true, mPlayMode);
            if (hasNext) {
                next = mPlayList.next(mPlayMode);
                play();
            }
        }
        notifyComplete(next);
    }


    @Override
    public void setPlayList(PlayList list) {
        if (list == null) {
            list = new PlayList();
        }
        mPlayList = list;
    }

    @Override
    public boolean play() {
        if (isPaused && mPlayer != null) {
            mPlayer.start();
            notifyPlayStatusChanged(true);
            isPaused = false;
            return true;
        }
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
            mPlayer.setOnPreparedListener(this);
            mPlayer.setOnCompletionListener(this);
        }

        if (mPlayList.prepare()) {
            Song song = mPlayList.getCurrentSong();
            try {
                mPlayer.reset();
                mPlayer.setDataSource(song.getPath());
                mPlayer.prepareAsync();
            } catch (IOException e) {
                Logger.d(e);
                notifyPlayStatusChanged(false);
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean play(PlayList list) {
        playProgress = 0;
        if (list == null)
            return false;
        isPaused = false;
        setPlayList(list);
        return play();
    }

    @Override
    public boolean play(PlayList list, int startIndex) {
        playProgress = 0;
        if (list == null || startIndex < 0 || startIndex >= list.getNumOfSongs())
            return false;

        isPaused = false;
        list.setPlayingIndex(startIndex);
        setPlayList(list);
        return play();
    }

    @Override
    public boolean play(Song song) {
        playProgress = 0;
        if (song == null)
            return false;

        isPaused = false;
        mPlayList.getSongs().clear();
        mPlayList.getSongs().add(song);
        return play();
    }

    @Override
    public boolean playLast() {
        playProgress = 0;
        isPaused = false;
        boolean hasLast = mPlayList.hasLast();
        if (hasLast) {
            Song last = mPlayList.last(mPlayMode);
            play();
            notifyPlayLast(last);
            return true;
        }
        return false;
    }

    @Override
    public boolean playNext() {
        playProgress = 0;
        isPaused = false;
        boolean hasNext = mPlayList.hasNext(false, mPlayMode);
        if (hasNext) {
            Song next = mPlayList.next(mPlayMode);
            play();
            notifyPlayNext(next);
            return true;
        }
        return false;
    }

    @Override
    public boolean pause() {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
            isPaused = true;
            notifyPlayStatusChanged(false);
            return true;
        }
        return false;
    }

    @Override
    public boolean isPlaying() {
        if (mPlayer == null || !mPlayer.isPlaying())
            return false;
        return true;
    }

    @Override
    public int getProgress() {
        return mPlayer.getCurrentPosition();
    }

    @Override
    public Song getPlayingSong() {
        return mPlayList.getCurrentSong();
    }

    @Override
    public boolean seekTo(int progress) {
        if (mPlayList.getSongs().isEmpty())
            return false;
        Song currentSong = mPlayList.getCurrentSong();
        if (currentSong != null) {
            if (currentSong.getDuration() <= progress) {
                onCompletion(mPlayer);
            } else {
                mPlayer.seekTo(progress);
            }
            return true;
        }
        return false;
    }

    @Override
    public void setPlayMode(PlayMode playMode) {
        this.mPlayMode = playMode;
    }

    @Override
    public PlayMode getPlayMode() {
        return mPlayMode;
    }

    @Override
    public void switchPlayMode() {
        mPlayMode = PlayMode.switchNextMode(mPlayMode);
    }

    @Override
    public void registerCallback(Callback callback) {
        mCallbacks.add(callback);
    }

    @Override
    public void unregisterCallback(Callback callback) {
        mCallbacks.remove(callback);
    }

    @Override
    public void removeCallbacks() {
        mCallbacks.clear();
    }

    @Override
    public void releasePlayer() {
        if(mPlayer==null)//MediaPlayer 已经释放
            return;
        //首先暂停播放并通知状态
        pause();
        playProgress = getProgress();
        mPlayer.stop();
        mPlayer.release();
        mPlayer = null;
    }

    private void notifyPlayStatusChanged(boolean isPlaying) {
        if (mPlayer == null)
            return;
        for (Callback callback : mCallbacks) {
            callback.onPlayStatusChanged(isPlaying);
        }
    }

    private void notifyPlayLast(Song song) {
        if (mPlayer == null)
            return;
        for (Callback callback : mCallbacks) {
            callback.onSwitchLast(song);
        }
    }

    private void notifyPlayNext(Song song) {
        if (mPlayer == null)
            return;
        for (Callback callback : mCallbacks) {
            callback.onSwitchNext(song);
        }
    }

    private void notifyComplete(Song song) {
        if (mPlayer == null)
            return;
        for (Callback callback : mCallbacks) {
            callback.onComplete(song);
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        if (playProgress <= 0 || playProgress <= mPlayList.getCurrentSong().getDuration()) {
            mp.seekTo(playProgress);
        }
        mp.start();
        playProgress = 0;
        notifyPlayStatusChanged(true);
        isPaused = false;
    }

    public void setVolume(float left, float right) {
        if (mPlayer != null && mPlayer.isPlaying())
            mPlayer.setVolume(left, right);
    }
}
