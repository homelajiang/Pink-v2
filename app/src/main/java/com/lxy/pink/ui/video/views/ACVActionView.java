package com.lxy.pink.ui.video.views;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.lxy.pink.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */

public class ACVActionView extends LinearLayout {
    @BindView(R.id.video_share)
    AppCompatTextView mVideoShare;
    @BindView(R.id.video_download)
    AppCompatTextView mVideoDownload;

    public ACVActionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.ac_video_info_action_view, this);
        ButterKnife.bind(this);
    }
}
