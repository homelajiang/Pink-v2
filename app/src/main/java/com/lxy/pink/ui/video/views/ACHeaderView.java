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
 * Created by yuan on 2016/12/20.
 */

public class ACHeaderView extends LinearLayout {
    @BindView(R.id.icon)
    SimpleDraweeView icon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.more)
    TextView more;

    public ACHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setRotation(HORIZONTAL);
        inflate(getContext(), R.layout.ac_header_view, this);
        ButterKnife.bind(this);
    }
}
