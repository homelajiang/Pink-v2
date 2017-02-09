package com.lxy.pink.ui.home.view;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2016/12/23 0023.
 */

public class PinkWeatherView extends RelativeLayout implements PinkServiceContract.WeatherCallback{
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
    private SimpleDateFormat timeFormat;
    private SimpleDateFormat dateTimeFormat;

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
        this.timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        this.dateTimeFormat = new SimpleDateFormat("MM/dd HH:mm", Locale.getDefault());

    }

    public void bind(Weather weather, boolean locationRun, boolean refreshRun) {
        if (weather != null)
            setWeather(weather);
        setLocationAnim(locationRun);
        setRefreshAnim(refreshRun);
    }

    public void setLocationAnim(boolean running) {
        mLocationIcon.clearAnimation();
        if (running)
            mLocationIcon.setAnimation(flickerAnimation);
    }

    public void setRefreshAnim(boolean running) {
        mRefresh.clearAnimation();
        if (running)
            mRefresh.setAnimation(unLimitedRotate);
    }

    public void setWeather(Weather weather) {
        mLocation.setText(String.valueOf(weather.getName()));
        mTemperature.setText(String.valueOf((int) weather.getMain().getTemp() + "°"));
        if (weather.getWeather() != null && weather.getWeather().size() > 0) {
            Weather.WeatherBean weatherBean = weather.getWeather().get(0);

            mDescription.setText(String.valueOf(weatherBean.getDescription()));
            mBackground.setImageURI(getWeatherResourceUri("background", weatherBean.getId()));
            mLight.setImageURI(getWeatherResourceUri("light", weatherBean.getId()));
            mSun.setImageURI(getWeatherResourceUri("sun", weatherBean.getId()));
            mBuilding.setImageURI(getWeatherResourceUri("building", weatherBean.getId()));
            Date publishDate = new Date(weather.getDt() * 1000l);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            this.mDate.setText(new Date().toString().substring(0, 10));
        } else {
            mDescription.setText(null);
        }
    }

    public void setTime(String time) {
        mTime.setText(time);
    }

    private Uri getWeatherResourceUri(String partName, int weatherId) {
        String url = Config.HOST_WEATHER_IMG + partName + "/" + weatherId + "/" + System.currentTimeMillis();
        return Uri.parse(url);
    }

    @Override
    public void weatherLoadStart() {

    }

    @Override
    public void weatherLoadEnd() {

    }

    @Override
    public void weatherLoadError(Throwable e) {

    }

    @Override
    public void weatherLoaded(Weather weather) {

    }

    @Override
    public void weatherLocationReq() {

    }

    @Override
    public void setPresenter(PinkServiceContract.Presenter presenter) {
    this.presenter = presenter;
    }

    public void bind (PinkService pinkService,Weather weather){
        this.pinkService = pinkService;
        this.pinkService.bindWeatherCallback(this);
        setWeather(weather);
    }

    public void unBind(){
        this.pinkService.unBindWeatherCallback(this);
    }
}