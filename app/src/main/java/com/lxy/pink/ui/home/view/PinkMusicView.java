package com.lxy.pink.ui.home.view;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.music_layout)
    RelativeLayout mMusicLayout;

    public PinkMusicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.pink_home_music_view, this);
        ButterKnife.bind(this);
    }
}
