package com.lxy.pink.ui.main;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.RxBus;
import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.data.model.music.Song;
import com.lxy.pink.data.source.PreferenceManager;
import com.lxy.pink.event.PlayListLoadedEvent;
import com.lxy.pink.event.PlayListNowEvent;
import com.lxy.pink.ui.base.BaseFragment;
import com.lxy.pink.ui.home.HomeFragment;
import com.lxy.pink.ui.music.SongListFragment;
import com.lxy.pink.ui.news.NewsFragment;
import com.lxy.pink.player.IPlayback;
import com.lxy.pink.player.PlayMode;
import com.lxy.pink.player.PlaybackService;
import com.lxy.pink.ui.video.VideoFragment;
import com.lxy.pink.utils.MediaHelper;
import com.lxy.pink.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by yuan on 2016/10/16.
 */

public class MainFragment extends BaseFragment implements MainFragmentContract.View,
        IPlayback.Callback {

    static final int DEFAULT_PAGE_INDEX = 0;
    public static final String TAG = "MainFragment";
    @BindViews({R.id.radio_btn_main, R.id.radio_btn_music,
            R.id.radio_btn_video, R.id.radio_btn_news})
    List<RadioButton> radioButtons;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.bottomSheet)
    LinearLayout bottomSheetLayout;
    @BindView(R.id.radio_btn_main)
    RadioButton mRadioBtnMain;
    @BindView(R.id.radio_btn_music)
    RadioButton mRadioBtnMusic;
    @BindView(R.id.radio_btn_video)
    RadioButton mRadioBtnVideo;
    @BindView(R.id.radio_btn_news)
    RadioButton mRadioBtnNews;
    @BindView(R.id.music_album)
    SimpleDraweeView mCtrlAlbum;
    @BindView(R.id.ctrl_title)
    TextView mCtrlTitle;
    @BindView(R.id.ctrl_sub_title)
    TextView mCtrlSubTitle;
    @BindView(R.id.linearLayout)
    LinearLayout mLinearLayout;
    @BindView(R.id.ctrl_next)
    AppCompatImageView mCtrlNext;
    @BindView(R.id.ctrl_play)
    AppCompatImageView mCtrlPlay;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.image_view_album)
    SimpleDraweeView mImageViewAlbum;
    @BindView(R.id.text_view_name)
    TextView mTextViewName;
    @BindView(R.id.text_view_artist)
    TextView mTextViewArtist;
    @BindView(R.id.text_view_progress)
    TextView mTextViewProgress;
    @BindView(R.id.seek_bar)
    AppCompatSeekBar mSeekBar;
    @BindView(R.id.text_view_duration)
    TextView mTextViewDuration;
    @BindView(R.id.layout_progress)
    LinearLayout mLayoutProgress;
    @BindView(R.id.button_play_mode_toggle)
    AppCompatImageView mButtonPlayModeToggle;
    @BindView(R.id.button_play_last)
    AppCompatImageView mButtonPlayLast;
    @BindView(R.id.button_play_toggle)
    AppCompatImageView mButtonPlayToggle;
    @BindView(R.id.button_play_next)
    AppCompatImageView mButtonPlayNext;
    @BindView(R.id.button_favorite_toggle)
    AppCompatImageView mButtonFavoriteToggle;
    @BindView(R.id.layout_play_controls)
    LinearLayout mLayoutPlayControls;
    @BindView(R.id.ctrl_layout)
    LinearLayout mCtrlLayout;
    private String[] titles;
    private final int UPDATE_PROGRESS_INTERVAL = 1000;
    private PlaybackService mPlayer;
    private Handler handler = new Handler();
    public int bottomSheetState;
    private Runnable progressCallback = new Runnable() {
        @Override
        public void run() {
            if (isDetached())
                return;

            if (mPlayer != null && mPlayer.isPlaying()) {
                int progress = (int) (mSeekBar.getMax()
                        * ((float) mPlayer.getProgress() / (float) getCurrentSongDuration()));
                updateProgressTextWithDuration(mPlayer.getProgress());
                if (progress >= 0 && progress <= mSeekBar.getMax()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        mSeekBar.setProgress(progress, true);
                    } else {
                        mSeekBar.setProgress(progress);
                    }
                    mProgressBar.setProgress(progress);
                    handler.postDelayed(this, UPDATE_PROGRESS_INTERVAL);
                }
            }
        }
    };
    private MainFragmentContract.Presenter presenter;
    private BottomSheetBehavior<LinearLayout> bottomSheetBehavior;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);
        bottomSheetBehavior.setHideable(true);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetCallback() {

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                bottomSheetState = newState;
                updateBottomSheetState();
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    updateProgressTextWithProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                handler.removeCallbacks(progressCallback);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekTo(getDuration(seekBar.getProgress()));
                if (mPlayer.isPlaying()) {
                    handler.removeCallbacks(progressCallback);
                    handler.post(progressCallback);
                }
            }
        });
        mCtrlLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetState == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });

        ((AppCompatActivity) getActivity())
                .setSupportActionBar(toolbar);
        titles = getResources().getStringArray(R.array.pink_main_titles);

        BaseFragment[] fragments = new BaseFragment[titles.length];

        fragments[0] = new HomeFragment();
        fragments[1] = new SongListFragment();
        fragments[2] = new VideoFragment();
        fragments[3] = new NewsFragment();

        MainPagerAdapter adapter = new MainPagerAdapter(getActivity()
                .getSupportFragmentManager(), titles, fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(titles.length - 1);
        viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.mp_margin_large));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                radioButtons.get(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        radioButtons.get(DEFAULT_PAGE_INDEX).setChecked(true);
        new MainFragmentPresenter(getContext(), this).subscribe();
        return root;
    }

    public void updateBottomSheetState() {
        if (bottomSheetState == BottomSheetBehavior.STATE_EXPANDED) {
            mCtrlPlay.setVisibility(View.GONE);
            mCtrlNext.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.GONE);
        }
        if (bottomSheetState == BottomSheetBehavior.STATE_COLLAPSED) {
            mCtrlPlay.setVisibility(View.VISIBLE);
            mCtrlNext.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @OnCheckedChanged({R.id.radio_btn_main, R.id.radio_btn_music,
            R.id.radio_btn_video, R.id.radio_btn_news})
    public void onRadioButtonChecked(RadioButton button, boolean isChecked) {
        if (isChecked) {
            onItemChecked(radioButtons.indexOf(button));
        }
    }

    @OnClick(R.id.button_play_last)
    public void onPlayLastAction(View view) {
        if (mPlayer == null)
            return;
        mPlayer.playLast();
    }

    @OnClick({R.id.button_play_next, R.id.ctrl_next})
    public void onPlayNextAction(View view) {
        if (mPlayer == null)
            return;
        mPlayer.playNext();
    }

    @OnClick(R.id.button_play_mode_toggle)
    public void onPlayModeToggleAction(View view) {
        if (mPlayer == null)
            return;

        PlayMode playMode = PreferenceManager.lastPlayMode(getContext());
        PlayMode newMode = PlayMode.switchNextMode(playMode);
        PreferenceManager.setPlayMode(getContext(), newMode);
        mPlayer.setPlayMode(newMode);
        updatePlayMode(newMode);
    }

    @OnClick({R.id.button_play_toggle, R.id.ctrl_play})
    public void onPlayToggleAction(View view) {
        if (mPlayer == null)
            return;

        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        } else {
            mPlayer.play();
        }
    }


    private void onItemChecked(int position) {
        viewPager.setCurrentItem(position);
        toolbar.setTitle(titles[position]);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void handleError(Throwable e) {

    }

    @Override
    protected Subscription subscribeEvents() {
        return RxBus.getInstance().toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        if (o instanceof PlayListNowEvent) {
                            onPlayListNowEvent((PlayListNowEvent) o);
                        } else if (o instanceof PlayListLoadedEvent) {
                            onPlayListEvent((PlayListLoadedEvent) o);
                        }
                    }
                })
                .subscribe(RxBus.defaultSubscriber());
    }

    private void onPlayListEvent(PlayListLoadedEvent o) {
        PlayList playList = o.playList;

        if (playList == null)
            return;

        long songId = PreferenceManager.getLastSong(getContext());

        if (songId == 0)
            return;

        int playIndex = -1;

        for (int i = 0; i < playList.getNumOfSongs(); i++) {
            if (songId == playList.getSong(i).getId()) {
                playIndex = i;
                break;
            }
        }

        if (playIndex == -1)
            return;

        playList.setPlayingIndex(playIndex);
        mPlayer.setPlayList(playList);

        onSongUpdated(playList.getCurrentSong());

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setHideable(false);
    }

    private void onPlayListNowEvent(PlayListNowEvent o) {
        PlayList playList = o.playList;
        int playIndex = o.playIndex;
        playSong(playList, playIndex);
    }

    private void playSong(PlayList playList, int playIndex) {
        if (playList == null)
            return;

        mPlayer.play(playList, playIndex);

        Song song = playList.getCurrentSong();
        onSongUpdated(song);
    }

    @Override
    public void onPlaybackServiceBound(PlaybackService service) {
        mPlayer = service;
        mPlayer.registerCallback(this);
    }

    @Override
    public void onPlaybackServiceUnbound() {
        mPlayer.unregisterCallback(this);
        mPlayer = null;
    }

    @Override
    public void onSongUpdated(@Nullable Song song) {

        if (song == null) {
            mButtonPlayToggle.setImageResource(R.drawable.ic_play_circle_outline_black_36dp);
            mCtrlPlay.setImageResource(R.drawable.ic_play_circle_outline_black_36dp);
            mSeekBar.setProgress(0);
            mProgressBar.setProgress(0);
            updateProgressTextWithProgress(0);
            seekTo(0);
            handler.removeCallbacks(progressCallback);
            return;
        }
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            bottomSheetBehavior.setHideable(false);
        }

        mTextViewName.setText(song.getTitle());
        mTextViewArtist.setText(song.getArtist());
        mTextViewDuration.setText(TimeUtils.formatDuration((int) song.getDuration()));
        mCtrlTitle.setText(song.getTitle());
        mCtrlSubTitle.setText(song.getArtist());
        handler.removeCallbacks(progressCallback);
        if (mPlayer.isPlaying()) {
            handler.post(progressCallback);
            mButtonPlayToggle.setImageResource(R.drawable.ic_pause_circle_outline_black_36dp);
            mCtrlPlay.setImageResource(R.drawable.ic_pause_circle_outline_black_36dp);
        }
        Uri albumUri = MediaHelper.getCoverUri(song.getAlbumId(), song.getId());
        mCtrlAlbum.setImageURI(albumUri);
        mImageViewAlbum.setImageURI(albumUri);
    }

    @Override
    public void updatePlayMode(PlayMode playMode) {
        if (playMode == null) {
            playMode = PlayMode.getDefault();
        }
        switch (playMode) {
            case LIST:
                mButtonPlayModeToggle.setImageResource(R.drawable.ic_play_mode_list);
                break;
            case LOOP:
                mButtonPlayModeToggle.setImageResource(R.drawable.ic_play_mode_loop);
                break;
            case SHUFFLE:
                mButtonPlayModeToggle.setImageResource(R.drawable.ic_play_mode_shuffle);
                break;
            case SINGLE:
                mButtonPlayModeToggle.setImageResource(R.drawable.ic_play_mode_single);
                break;
        }
    }

    @Override
    public void updatePlayToggle(boolean play) {
        mButtonPlayToggle.setImageResource(play ? R.drawable.ic_pause_circle_outline_black_36dp
                : R.drawable.ic_play_circle_outline_black_36dp);

        mCtrlPlay.setImageResource(play ? R.drawable.ic_pause_circle_outline_black_36dp
                : R.drawable.ic_play_circle_outline_black_36dp);
    }

    @Override
    public void setPresenter(MainFragmentContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onSwitchLast(@Nullable Song last) {
        onSongUpdated(last);
    }

    @Override
    public void onSwitchNext(@Nullable Song next) {
        onSongUpdated(next);
    }

    @Override
    public void onComplete(@Nullable Song next) {
        onSongUpdated(next);
    }

    @Override
    public void onPlayStatusChanged(boolean isPlaying) {
        updatePlayToggle(isPlaying);
        if (isPlaying) {
            handler.removeCallbacks(progressCallback);
            handler.post(progressCallback);
        } else {
            handler.removeCallbacks(progressCallback);
        }
    }

    private void updateProgressTextWithProgress(int progress) {
        int targetDuration = getDuration(progress);
        mTextViewProgress.setText(TimeUtils.formatDuration(targetDuration));
    }

    private void updateProgressTextWithDuration(int duration) {
        mTextViewProgress.setText(TimeUtils.formatDuration(duration));
    }

    private int getDuration(int progress) {
        return (int) (getCurrentSongDuration() * ((float) progress / mSeekBar.getMax()));
    }

    private int getCurrentSongDuration() {
        Song currentSong = mPlayer.getPlayingSong();
        int duration = 0;
        if (currentSong != null) {
            duration = (int) currentSong.getDuration();
        }
        return duration;
    }

    private void seekTo(int duration) {
        mPlayer.seekTo(duration);
    }

    public int getBottomSheetState() {
        return bottomSheetState;
    }

    public void collapsedBottomSheet() {
        if (bottomSheetState == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

}
