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
 * Created by homelajiang on 2016/12/20 0020.
 */

public class ACFunView extends CardView {
    @BindView(R.id.fun_cover)
    SimpleDraweeView mFunCover;
    @BindView(R.id.fun_title)
    TextView mFunTitle;
    @BindView(R.id.fun_info)
    TextView mFunInfo;

    public ACFunView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.ac_fun_v_view, this);
        ButterKnife.bind(this);
    }

    // TODO: 2016/12/20 0020  set methods


}
