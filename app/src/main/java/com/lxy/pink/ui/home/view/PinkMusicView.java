package com.lxy.pink.ui.home.view;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.BuildConfig;
import com.lxy.pink.R;
import com.lxy.pink.data.model.music.Song;
import com.lxy.pink.player.PlaybackService;
import com.lxy.pink.utils.MediaHelper;
import com.lxy.pink.utils.TimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by homelajiang on 2016/12/23 0023.
 */

public class PinkMusicView extends RelativeLayout {
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
    private Runnable progressRunnable = new Runnable() {
        @Override
        public void run() {
            if (mPlayer != null && mPlayer.isPlaying()) {
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
        }
    };

    public void bind(PlaybackService mPlayer) {
        this.mPlayer = mPlayer;
        Song song = mPlayer.getPlayingSong();
        updateUI(song);
    }

    public void unBind() {
        removeCallbacks(progressRunnable);
    }

    @OnClick(R.id.music_play)
    public void togglePlay(View view) {
        if (this.mPlayer == null)
            return;
        if (this.mPlayer.isPlaying()) {
            if (this.mPlayer.pause()) {
                removeCallbacks(progressRunnable);
            }
        } else {
            if (this.mPlayer.play()) {
                post(progressRunnable);
            }
        }
    }

    public void updateUI(Song song) {
        if (song == null)
            return;
        mMusicMusic.setTag(song.getId());
        mMusicMusic.setText(song.getTitle());
        mMusicSubTitle.setText(song.getArtist());
        mMusicTime.setText(TimeUtils.formatDuration((int) song.getDuration()));
        removeCallbacks(progressRunnable);
        if (mPlayer.isPlaying()) {
            post(progressRunnable);
            mMusicPlay.setImageResource(R.drawable.ic_pause_circle_outline_black_36dp);
        } else {
            mMusicPlay.setImageResource(R.drawable.ic_play_circle_outline_black_36dp);
        }
        Uri albumUri = MediaHelper.getCoverUri(song.getAlbumId(), song.getId());
        mMusicAlbum.setImageURI(albumUri);
    }

    public void updatePlayToggle(boolean play) {
        if (mPlayer == null)
            return;
        Song playingSong = mPlayer.getPlayingSong();
        if (playingSong == null)
            return;
        //如果音乐没有变则不更新界面，只更新播放控制
        if (mMusicMusic.getTag() != null && playingSong.getId() == (long) mMusicMusic.getTag()) {
            if (BuildConfig.DEBUG)
                Log.d("", "music not changed.");
            if (play) {
                removeCallbacks(progressRunnable);
                post(progressRunnable);
            } else {
                removeCallbacks(progressRunnable);
            }
            mMusicPlay.setImageResource(play ? R.drawable.ic_pause_circle_outline_black_36dp
                    : R.drawable.ic_play_circle_outline_black_36dp);
        } else {
            //更新界面和控制器
            updateUI(playingSong);
        }
    }

    public PinkMusicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.pink_home_music_view, this);
        ButterKnife.bind(this);
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
}
