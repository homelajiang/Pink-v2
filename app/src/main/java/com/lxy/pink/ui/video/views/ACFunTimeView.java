package com.lxy.pink.ui.video.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lxy.pink.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuan on 2016/12/20.
 */

public class ACFunTimeView extends LinearLayout {
    @BindView(R.id.fun_time_left)
    public ImageView funTimeLeft;
    @BindView(R.id.fun_time_right)
    public ImageView funTimeRight;

    public ACFunTimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
        inflate(getContext(), R.layout.ac_fun_time_view, this);
        ButterKnife.bind(this);
    }

}
