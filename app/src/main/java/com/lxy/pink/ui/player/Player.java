package com.lxy.pink.ui.player;

import android.media.MediaPlayer;
import android.util.Log;

import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.data.model.music.Song;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by homelajiang on 2016/11/2 0002.
 */

public class Player implements MediaPlayer.OnCompletionListener ,IPlayback{
    private static final String TAG = "Player";
    private static volatile Player sInstance;

    private MediaPlayer mPlayer;
    private PlayList mPlayList;
    private PlayMode mPlayMode;

    private List<IPlayback.Callback> mCallbacks = new ArrayList<>(2);

    private boolean isPaused;

    private Player(){
        mPlayMode  = PlayMode.LIST;// TODO: 2016/11/2 0002 need get from sp
        mPlayer = new MediaPlayer();
        mPlayList = new PlayList();
        mPlayer.setOnCompletionListener(this);
    }

    public static Player getInstance(){
        if(sInstance == null){
            synchronized (Player.class){
                if(sInstance == null){
                    sInstance = new Player();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
            Song next = null;
    if(mPlayMode == PlayMode.LIST && mPlayList.getPlayingIndex() == mPlayList.getNumOfSongs()-1){

    }else if(mPlayMode == PlayMode.SINGLE){
        next = mPlayList.getCurrentSong();
        play();
    }else {
        boolean hasNext = mPlayList.hasNext(true,mPlayMode);
        if(hasNext){
            next = mPlayList.next(mPlayMode);
            play();
        }
    }
        notifyComplete(next);
    }


    @Override
    public void setPlayList(PlayList list) {
    if(list == null){
        list = new PlayList();
    }
        mPlayList = list;
    }

    @Override
    public boolean play() {
        if(isPaused){
            mPlayer.start();
            notifyPlayStatusChanged(true);
            return true;
        }
        // TODO: 2016/11/2 0002  change prepare way
        if(mPlayList.prepare()){
            Song song = mPlayList.getCurrentSong();
            try{
                mPlayer.reset();
                mPlayer.setDataSource(song.getPath());
                mPlayer.prepare();
                mPlayer.start();
            }catch (IOException e){
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
        return false;
    }

    @Override
    public boolean play(PlayList list, int startIndex) {
        return false;
    }

    @Override
    public boolean play(Song song) {
        return false;
    }

    @Override
    public boolean playLast() {
        return false;
    }

    @Override
    public boolean playNext() {
        return false;
    }

    @Override
    public boolean pause() {
        return false;
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public int getProgress() {
        return 0;
    }

    @Override
    public Song getPlayingSong() {
        return null;
    }

    @Override
    public boolean seekTo(int progress) {
        return false;
    }

    @Override
    public void setPlayMode(PlayMode playMode) {

    }

    @Override
    public void registerCallback(Callback callback) {

    }

    @Override
    public void unregisterCallback(Callback callback) {

    }

    @Override
    public void removeCallbacks() {

    }

    @Override
    public void releasePlayer() {

    }

    private void notifyPlayStatusChanged(boolean isPlaying){
        for(Callback callback:mCallbacks){
            callback.onPlayStatusChanged(isPlaying);
        }
    }

    private void notifyPlayLast(Song song){
        for(Callback callback:mCallbacks){
            callback.onSwitchLast(song);
        }
    }

    private void notifiyPlayNext(Song song){
        for(Callback callback:mCallbacks){
            callback.onSwitchNext(song);
        }
    }

    private void notifyComplete(Song song){
        for(Callback callback:mCallbacks){
            callback.onComplete(song);
        }
    }
}
