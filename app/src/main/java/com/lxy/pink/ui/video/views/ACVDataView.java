package com.lxy.pink.ui.video.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.lxy.pink.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */

public class ACVDataView extends LinearLayout {
    @BindView(R.id.video_views)
    LinearLayout mVideoViews;
    @BindView(R.id.video_danmakuSize)
    LinearLayout mVideoDanmuKuSize;
    @BindView(R.id.video_goldBanana)
    LinearLayout mVideoBanana;
    @BindView(R.id.video_stows)
    LinearLayout mVideoStows;

    public ACVDataView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.ac_video_info_data_view, this);
        ButterKnife.bind(this);
    }
}
