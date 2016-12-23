package com.lxy.pink.ui.home.view;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2016/12/23 0023.
 */

public class PinkWeatherView extends RelativeLayout {
    @BindView(R.id.background)
    SimpleDraweeView mBackground;
    @BindView(R.id.sun)
    SimpleDraweeView mSun;
    @BindView(R.id.light)
    SimpleDraweeView mLight;
    @BindView(R.id.temperature)
    TextView mTemperature;
    @BindView(R.id.description)
    TextView mDescription;
    @BindView(R.id.location_icon)
    AppCompatImageView mLocationIcon;
    @BindView(R.id.location)
    TextView mLocation;
    @BindView(R.id.refresh)
    AppCompatImageView mRefresh;
    @BindView(R.id.date)
    TextView mDate;
    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.building)
    SimpleDraweeView mBuilding;

    public PinkWeatherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.pink_home_weather_view, this);
        ButterKnife.bind(this);
    }

}
