package com.lxy.pink.ui.video.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.utils.FrescoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2016/12/20 0020.
 */

public class ACVideoView extends CardView {
    @BindView(R.id.video_cover)
    SimpleDraweeView mVideoCover;
    @BindView(R.id.video_title)
    TextView mVideoTitle;
    @BindView(R.id.video_play_count)
    TextView mVideoPlayCount;
    @BindView(R.id.video_danmu_count)
    TextView mVideoDanmuCount;

    public ACVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.ac_video_h_view, this);
        ButterKnife.bind(this);
    }

    public void setVideoCover(String url) {
        FrescoUtils.setImage(url, mVideoCover);
    }

    public void setVideoTitle(String title) {
        mVideoTitle.setText(title);
    }

    public void setVideoPlayCount(int count) {
        mVideoPlayCount.setText(String.valueOf(count));
    }

    public void setVideoDanmuCount(int count) {
        mVideoDanmuCount.setText(String.valueOf(count));
    }

}
