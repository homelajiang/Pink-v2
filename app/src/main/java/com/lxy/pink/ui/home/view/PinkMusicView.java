package com.lxy.pink.ui.home.view;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.data.model.music.Song;
import com.lxy.pink.player.IPlayback;
import com.lxy.pink.player.PlayMode;
import com.lxy.pink.player.PlaybackService;
import com.lxy.pink.utils.MediaHelper;
import com.lxy.pink.utils.TimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by homelajiang on 2016/12/23 0023.
 */

public class PinkMusicView extends RelativeLayout implements IPlayback.Callback {
    @BindView(R.id.music_play)
    FloatingActionButton mMusicPlay;
    @BindView(R.id.music_album)
    SimpleDraweeView mMusicAlbum;
    @BindView(R.id.music_music)
    TextView mMusicMusic;
    @BindView(R.id.music_sub_title)
    TextView mMusicSubTitle;
    @BindView(R.id.music_time)
    TextView mMusicTime;
    @BindView(R.id.right_area)
    RelativeLayout mRightArea;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private static final long UPDATE_PROGRESS_INTERVAL = 1000;
    PlaybackService mPlayer;
    @BindView(R.id.music_mode_toggle)
    AppCompatImageButton mMusicModeToggle;
    @BindView(R.id.music_next)
    FloatingActionButton mMusicNext;

    public PinkMusicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.pink_home_music_view, this);
        ButterKnife.bind(this);
    }

    public void bind(PlaybackService mPlayer) {
        this.mPlayer = mPlayer;
        mPlayer.registerCallback(this);
        updateUI();
    }

    public void unBind() {
        mPlayer.unregisterCallback(this);
        removeCallbacks(progressRunnable);
        this.mPlayer = null;
    }

    @OnClick(R.id.music_play)
    public void togglePlay(View view) {
        if (this.mPlayer == null)
            return;
        if (this.mPlayer.isPlaying()) {
            if (this.mPlayer.pause()) {
                removeCallbacks(progressRunnable);
                mMusicPlay.setImageResource(R.drawable.ic_play_circle_outline_black_36dp);
            }
        } else {
            if (this.mPlayer.play()) {
                post(progressRunnable);
                mMusicPlay.setImageResource(R.drawable.ic_pause_circle_outline_black_36dp);
            }
        }
    }

    @OnClick(R.id.music_next)
    public void playNext(View view) {
        if (mPlayer != null)
            mPlayer.playNext();
    }

    @OnClick(R.id.music_mode_toggle)
    public void togglePalyMode(){
        if(mPlayer==null)
            return;
        mPlayer.switchPlayMode();
        updatePlayMode();
    }

    public void updateUI() {
        if (mPlayer == null)
            return;
        Song song = mPlayer.getPlayingSong();
        if (song == null)
            return;
//        mMusicMusic.setTag(song.getId());
        mMusicMusic.setText(song.getTitle());
        mMusicSubTitle.setText(song.getArtist());
//        mMusicTime.setText(TimeUtils.formatDuration((int) song.getDuration()));
        removeCallbacks(progressRunnable);
        if (mPlayer.isPlaying()) {
            post(progressRunnable);
            mMusicPlay.setImageResource(R.drawable.ic_pause_circle_outline_black_36dp);
        } else {
            mMusicPlay.setImageResource(R.drawable.ic_play_circle_outline_black_36dp);
        }
        Uri albumUri = MediaHelper.getCoverUri(song.getAlbumId(), song.getId());
        mMusicAlbum.setImageURI(albumUri);
        updatePlayMode();
    }

    private int getCurrentSongDuration() {
        Song currentSong = mPlayer.getPlayingSong();
        int duration = 0;
        if (currentSong != null) {
            duration = (int) currentSong.getDuration();
        }
        return duration;
    }

    private void updateProgressTextWithDuration(int duration) {
        mMusicTime.setText(TimeUtils.formatDuration(duration));
    }

    private void updatePlayMode(){
        switch (mPlayer.getPlayMode()){
            case LIST:
                mMusicModeToggle.setImageResource(R.drawable.ic_repeat_black_24dp);
                break;
            case SHUFFLE:
                mMusicModeToggle.setImageResource(R.drawable.ic_shuffle_black_24dp);
                break;
            case SINGLE:
                mMusicModeToggle.setImageResource(R.drawable.ic_repeat_one_black_24dp);
                break;
        }
    }

    @Override
    public void onSwitchLast(@Nullable Song last) {
        updateUI();
    }

    @Override
    public void onSwitchNext(@Nullable Song next) {
        updateUI();
    }

    @Override
    public void onComplete(@Nullable Song next) {
        updateUI();
    }

    @Override
    public void onPlayStatusChanged(boolean isPlaying) {
        updateUI();
    }

    private Runnable progressRunnable = new Runnable() {
        @Override
        public void run() {
            if (mPlayer == null)
                return;
            if( !mPlayer.isPlaying())
                return;
            int progress = (int) (mProgressBar.getMax()
                    * ((float) mPlayer.getProgress() / (float) getCurrentSongDuration()));
            updateProgressTextWithDuration(mPlayer.getProgress());
            if (progress >= 0 && progress <= mProgressBar.getMax()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mProgressBar.setProgress(progress, true);
                } else {
                    mProgressBar.setProgress(progress);
                }
                postDelayed(this, UPDATE_PROGRESS_INTERVAL);
            }
        }
    };
}
