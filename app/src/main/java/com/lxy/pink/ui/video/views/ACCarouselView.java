package com.lxy.pink.ui.video.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.lxy.pink.R;

import butterknife.ButterKnife;

/**
 * Created by yuan on 2016/12/19.
 */

public class ACCarouselView extends RelativeLayout{

    public ACCarouselView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init() {
        inflate(getContext(), R.layout.ac_video_carousel_view, this);
        ButterKnife.bind(this);
    }
}
