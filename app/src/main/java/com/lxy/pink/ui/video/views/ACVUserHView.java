package com.lxy.pink.ui.video.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.lxy.pink.R;

import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */

public class ACVUserHView extends LinearLayout {
    public ACVUserHView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.ac_video_info_user_h_view, this);
        ButterKnife.bind(this);
    }
}
