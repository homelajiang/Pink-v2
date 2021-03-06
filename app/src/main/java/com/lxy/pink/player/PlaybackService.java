package com.lxy.pink.player;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import com.lxy.pink.R;
import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.data.model.music.Song;
import com.lxy.pink.data.source.PreferenceManager;
import com.lxy.pink.ui.main.MainActivity;
import com.lxy.pink.utils.MediaHelper;

/**
 * Created by homelajiang on 2016/11/3 0003.
 */

public class PlaybackService extends Service implements IPlayback, IPlayback.Callback,
        AudioManager.OnAudioFocusChangeListener {

    private static final String ACTION_PLAY_TOGGLE = "com.lxy.pink.music.ACTION.PLAY_TOGGLE";
    private static final String ACTION_PLAY_LAST = "com.lxy.pink.music.ACTION.PLAY_LAST";
    private static final String ACTION_PLAY_NEXT = "com.lxy.pink.music.ACTION.PLAY_NEXT";
    private static final String ACTION_STOP_SERVICE = "com.lxy.pink.music.ACTION.STOP_SERVICE";

    private static final int NOTIFICATION_ID = 1;
    private RemoteViews mContentViewBig, mContentViewSmall;
    private Player mPlayer;
    private final Binder mBinder = new LocalBuilder();
    private NoisyReceiver noisyReceiver;
    private IntentFilter noisyFilter;
    private AudioManager audioManager;
    private boolean hasFocus;

    @Override
    public void onAudioFocusChange(int focusChange) {
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_GAIN://获取焦点长期占有
                hasFocus = true;
                    play();
                    mPlayer.setVolume(1.0f, 1.0f);
//                }
                break;
            case AudioManager.AUDIOFOCUS_LOSS://长久的失去焦点
                releasePlayer();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT://暂时失去焦点，暂停，重新获取是继续播放
                hasFocus = false;
                pause();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK://暂时获取焦点，不必停止，降低音量即可
                mPlayer.setVolume(.5f, .5f);
                break;
        }
    }


    public class LocalBuilder extends Binder {
        public PlaybackService getService() {
            return PlaybackService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        noisyReceiver = new NoisyReceiver();
        noisyFilter = new IntentFilter();
        noisyFilter.addAction(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mPlayer = Player.getInstance();
        mPlayer.registerCallback(this);
        mPlayer.setPlayMode(PreferenceManager.getPlayMode(this));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        if (ACTION_PLAY_TOGGLE.equals(action)) {
            if (isPlaying()) {
                pause();
            } else {
                play();
            }
        } else if (ACTION_PLAY_NEXT.equals(action)) {
            playNext();
        } else if (ACTION_PLAY_LAST.equals(action)) {
            playLast();
        } else if (ACTION_STOP_SERVICE.equals(action)) {
            releasePlayer();
            stopForeground(true);
        }
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean stopService(Intent name) {
        stopForeground(true);
        unregisterCallback(this);
        return super.stopService(name);
    }

    @Override
    public void onDestroy() {
        releasePlayer();
        super.onDestroy();
    }

    @Override
    public void setPlayList(PlayList list) {
        mPlayer.setPlayList(list);
    }

    @Override
    public boolean play() {
        if (!hasFocus)
            requestAudioFocus();
        return mPlayer.play();
    }

    @Override
    public boolean play(PlayList list) {
        requestAudioFocus();
        return mPlayer.play(list);
    }

    @Override
    public boolean play(PlayList list, int startIndex) {
        requestAudioFocus();
        return mPlayer.play(list, startIndex);
    }

    @Override
    public boolean play(Song song) {
        requestAudioFocus();
        return mPlayer.play(song);
    }

    @Override
    public boolean playLast() {
        requestAudioFocus();
        return mPlayer.playLast();
    }

    @Override
    public boolean playNext() {
        requestAudioFocus();
        return mPlayer.playNext();
    }

    private void requestAudioFocus() {
        /**
         * AUDIOFOCUS_GAIN 申请焦点不知道持续多长时间，可能会长时间占有
         * AUDIOFOCUS_GAIN_TRANSIENT 申请短时间占用
         * AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK 临时申请，只用降低音量即可
         */
        if (!hasFocus)
            audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
    }

    @Override
    public boolean pause() {
        //暂停时放弃音频焦点
        audioManager.abandonAudioFocus(this);
        hasFocus = false;
        return mPlayer.pause();
    }

    @Override
    public boolean isPlaying() {
        return mPlayer.isPlaying();
    }

    @Override
    public int getProgress() {
        return mPlayer.getProgress();
    }

    @Override
    public Song getPlayingSong() {
        return mPlayer.getPlayingSong();
    }

    @Override
    public boolean seekTo(int progress) {
        return mPlayer.seekTo(progress);
    }

    @Override
    public void setPlayMode(PlayMode playMode) {
        mPlayer.setPlayMode(playMode);
    }

    @Override
    public PlayMode getPlayMode() {
        return mPlayer.getPlayMode();
    }

    @Override
    public void switchPlayMode() {
        mPlayer.switchPlayMode();
        PreferenceManager.setPlayMode(this, mPlayer.getPlayMode());
    }

    @Override
    public void registerCallback(Callback callback) {
        mPlayer.registerCallback(callback);
    }

    @Override
    public void unregisterCallback(Callback callback) {
        mPlayer.unregisterCallback(callback);
    }

    @Override
    public void removeCallbacks() {
        mPlayer.removeCallbacks();
    }

    @Override
    public void releasePlayer() {

        audioManager.abandonAudioFocus(this);//放弃音频焦点
        hasFocus = false;

        mPlayer.releasePlayer();
    }

    @Override
    public void onSwitchLast(@Nullable Song last) {
        showNotification();
    }

    @Override
    public void onSwitchNext(@Nullable Song next) {
        showNotification();
    }

    @Override
    public void onComplete(@Nullable Song next) {
        showNotification();
    }

    @Override
    public void onPlayStatusChanged(boolean isPlaying) {
        showNotification();
        if (isPlaying) {
            registerReceiver(noisyReceiver, noisyFilter);
        } else {
            unregisterReceiver(noisyReceiver);
        }
    }

    private void showNotification() {

        if (mPlayer.getPlayingSong() != null) {
            PreferenceManager.setLastSong(this, mPlayer.getPlayingSong().getId());
        }

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(contentIntent)
                .setCustomContentView(getSmallContentView())
                .setCustomBigContentView(getBigContentView())
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setOngoing(true)
                .build();
        startForeground(NOTIFICATION_ID, notification);

    }

    private RemoteViews getSmallContentView() {
        if (mContentViewSmall == null) {
            mContentViewSmall = new RemoteViews(getPackageName(), R.layout.remote_view_music_player_small);
            setUpRemoteView(mContentViewSmall, false);
        }
        updateRemoteViews(mContentViewSmall);
        return mContentViewSmall;
    }

    private RemoteViews getBigContentView() {
        if (mContentViewBig == null) {
            mContentViewBig = new RemoteViews(getPackageName(), R.layout.remote_view_music_player);
            setUpRemoteView(mContentViewBig, true);
        }
        updateRemoteViews(mContentViewBig);
        return mContentViewBig;
    }

    private void setUpRemoteView(RemoteViews remoteView, boolean isBig) {
        remoteView.setImageViewResource(R.id.image_view_close, R.drawable.ic_clear_black_24dp);
        remoteView.setImageViewResource(R.id.image_view_play_next, R.drawable.ic_skip_next_black_24dp);
        remoteView.setImageViewResource(R.id.image_view_play_toggle, R.drawable.ic_play_circle_outline_black_36dp);

        remoteView.setOnClickPendingIntent(R.id.button_close, getPendingIntent(ACTION_STOP_SERVICE));
        remoteView.setOnClickPendingIntent(R.id.button_play_next, getPendingIntent(ACTION_PLAY_NEXT));
        remoteView.setOnClickPendingIntent(R.id.button_play_toggle, getPendingIntent(ACTION_PLAY_TOGGLE));

        if (isBig) {
            remoteView.setImageViewResource(R.id.image_view_play_last, R.drawable.ic_skip_previous_black_24dp);
            remoteView.setOnClickPendingIntent(R.id.button_play_last, getPendingIntent(ACTION_PLAY_LAST));
        }
    }

    private void updateRemoteViews(RemoteViews remoteView) {
        Song currentSong = mPlayer.getPlayingSong();
        if (currentSong != null) {
            remoteView.setTextViewText(R.id.text_view_name, currentSong.getTitle());
            remoteView.setTextViewText(R.id.text_view_artist, currentSong.getArtist());
        }

        remoteView.setImageViewResource(R.id.image_view_play_toggle, isPlaying() ?
                R.drawable.ic_pause_circle_outline_black_36dp : R.drawable.ic_play_circle_outline_black_36dp);

        remoteView.setImageViewUri(R.id.image_view_album,
                MediaHelper.getCoverUri(getPlayingSong().getAlbumId(), getPlayingSong().getId()));
    }

    private PendingIntent getPendingIntent(String action) {
        return PendingIntent.getService(this, 0, new Intent(action), 0);
    }

    class NoisyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (AudioManager.ACTION_AUDIO_BECOMING_NOISY.equals(intent.getAction())) {
                pause();
            }
        }
    }


}
