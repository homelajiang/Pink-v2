package com.lxy.pink.ui.video.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxy.pink.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */

public class ACVHeaderView extends LinearLayout {
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.sub_title)
    TextView mSubTitle;

    public ACVHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.ac_video_info_header_view, this);
        ButterKnife.bind(this);
    }
}
