package com.lxy.pink.ui.video.views;

import android.content.Context;
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

public class ACVUserVView extends LinearLayout {
    @BindView(R.id.user_icon)
    public SimpleDraweeView mUserIcon;
    @BindView(R.id.user_name)
    public TextView mUserName;
    @BindView(R.id.more)
    public TextView mMore;

    public ACVUserVView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.ac_video_info_user_v_view, this);
        ButterKnife.bind(this);
    }
}
