package com.lxy.pink.ui.video.views;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */

public class ACVUserHView extends LinearLayout {
    @BindView(R.id.user_icon)
    public SimpleDraweeView mUserIcon;
    @BindView(R.id.up_name)
    public TextView mUpName;
    @BindView(R.id.publish_time)
    public TextView mPublishTime;
    @BindView(R.id.follow)
    public AppCompatButton mFollow;

    public ACVUserHView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.ac_video_info_user_h_view, this);
        ButterKnife.bind(this);
    }
}
