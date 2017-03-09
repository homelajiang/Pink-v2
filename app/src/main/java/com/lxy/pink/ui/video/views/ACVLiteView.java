package com.lxy.pink.ui.video.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */

public class ACVLiteView extends CardView {
    @BindView(R.id.video_cover)
    SimpleDraweeView mVideoCover;
    @BindView(R.id.video_title)
    TextView mVideoTitle;

    public ACVLiteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.ac_video_info_v_lite_view, this);
        ButterKnife.bind(this);
    }
}
