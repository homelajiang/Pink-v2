package com.lxy.pink.ui.video.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.utils.FrescoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuan on 2016/12/20.
 */

public class ACBananaVideoView extends CardView {
    @BindView(R.id.video_cover)
    SimpleDraweeView videoCover;
    @BindView(R.id.videoTitle)
    TextView videoTitle;
    @BindView(R.id.videoBanana)
    TextView videoBanana;
    @BindView(R.id.upName)
    TextView upName;

    public ACBananaVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.ac_banana_video_view, this);
        ButterKnife.bind(this);
    }

    public void setVideoCover(String url) {
        FrescoUtils.setpImage(url, videoCover);
    }

    public void setVideoTitle(String title) {
        this.videoTitle.setText(title);
    }

    public void setUpName(String upName) {
        this.upName.setText(upName);
    }

    public void setVideoBanana(int videoBanana) {
        this.videoBanana.setText(String.valueOf(videoBanana));
    }
}
