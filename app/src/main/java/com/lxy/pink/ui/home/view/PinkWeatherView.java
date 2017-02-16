package com.lxy.pink.ui.home.view;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.core.PinkService;
import com.lxy.pink.core.PinkServiceContract;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.utils.Config;
import com.lxy.pink.utils.FuzzyDateFormatter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2016/12/23 0023.
 */

public class PinkWeatherView extends RelativeLayout implements PinkServiceContract.WeatherCallback {
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

    private final Animation flickerAnimation
            = AnimationUtils.loadAnimation(getContext(), R.anim.flicker);
    private final Animation unLimitedRotate
            = AnimationUtils.loadAnimation(getContext(), R.anim.unlimited_rotate);
    private PinkServiceContract.Presenter presenter;
    private PinkService pinkService;


    public PinkWeatherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.pink_home_weather_view, this);
        ButterKnife.bind(this);
    }

    public void setWeather(Weather weather) {

        if (weather != null) {
            mLocation.setText(weather.getName());
            mTemperature.setText(String.valueOf(weather.getTemperature() + "°"));
            mDescription.setText(weather.getText());
            mBackground.setImageURI(getWeatherResourceUri("background", weather.getCode()));
            mLight.setImageURI(getWeatherResourceUri("light", weather.getCode()));
            mSun.setImageURI(getWeatherResourceUri("sun", weather.getCode()));
            mBuilding.setImageURI(getWeatherResourceUri("building", weather.getCode()));
            this.mDate.setText(String.format(getResources().getString(R.string.weather_publish_time),
                    FuzzyDateFormatter.getTimeAgo(getContext(), weather.getLastUpdate())));
        } else {
        }
    }

    public void setTime(String time) {
        mTime.setText(time);
    }

    private Uri getWeatherResourceUri(String partName, String weatherId) {
        String url = Config.HOST_WEATHER_IMG + partName + "/" + weatherId + "/" + System.currentTimeMillis();
        return Uri.parse(url);
    }

    @Override
    public void weatherLoadStart() {
        mRefresh.startAnimation(unLimitedRotate);
    }

    @Override
    public void weatherLoadEnd() {
        mRefresh.clearAnimation();
    }

    @Override
    public void weatherLoadError(Throwable e) {
        mRefresh.clearAnimation();
    }

    @Override
    public void weatherLoaded(Weather weather) {
        setWeather(weather);
    }

    @Override
    public void weatherLocationReq() {
        //nothing to do
    }

    @Override
    public void weatherLocationStart() {
        mLocationIcon.startAnimation(flickerAnimation);
    }

    @Override
    public void weatherLocationEnd() {
        mLocationIcon.clearAnimation();
    }

    @Override
    public void weatherLocationError() {
        mLocationIcon.clearAnimation();
    }

    @Override
    public void setPresenter(PinkServiceContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void bind(PinkService pinkService, Weather weather) {
        this.pinkService = pinkService;
        this.pinkService.bindWeatherCallback(this);
        if (weather == null)
            return;
        setWeather(weather);
    }

    public void unBind() {
        this.pinkService.unBindWeatherCallback(this);
    }
}
