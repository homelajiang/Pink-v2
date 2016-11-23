package com.lxy.pink.ui.home;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.ui.base.adapter.IAdapterView;
import com.lxy.pink.utils.Config;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherItemView extends RelativeLayout implements IAdapterView<Weather> {
    private final SimpleDateFormat timeFormat;
    private final SimpleDateFormat dateTimeFormat;
    private Context context;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.background)
    SimpleDraweeView background;
    @BindView(R.id.building)
    SimpleDraweeView building;
    @BindView(R.id.sun)
    SimpleDraweeView sun;
    @BindView(R.id.light)
    SimpleDraweeView light;
    @BindView(R.id.temperature)
    TextView temperature;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.location_icon)
    AppCompatImageView mLocationIcon;
    @BindView(R.id.refresh)
    AppCompatImageView mRefresh;

    public WeatherItemView(Context context) {
        super(context);
        this.context = context;
        this.timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        this.dateTimeFormat = new SimpleDateFormat("MM/dd HH:mm", Locale.getDefault());

        View.inflate(context, R.layout.item_home_weather, this);
        ButterKnife.bind(this);
    }


    @Override
    public void bind(Weather weather, int position) {
        if (weather.getId() <= 0)
            return;
        location.setText(String.valueOf(weather.getName()));
        temperature.setText(String.valueOf((int) weather.getMain().getTemp() + "°"));
        if (weather.getWeather() != null && weather.getWeather().size() > 0) {
            Weather.WeatherBean weatherBean = weather.getWeather().get(0);

            description.setText(String.valueOf(weatherBean.getDescription()));
            background.setImageURI(getWeatherResourceUri("background", weatherBean.getId()));
            light.setImageURI(getWeatherResourceUri("light", weatherBean.getId()));
            sun.setImageURI(getWeatherResourceUri("sun", weatherBean.getId()));
            building.setImageURI(getWeatherResourceUri("building", weatherBean.getId()));
            Date publishDate = new Date(weather.getDt() * 1000l);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            //TODO 显示方式
//            if(publishDate.getTime()>=calendar.getTimeInMillis()){
//                this.date.setText(timeFormat.format(publishDate));
//            }else {
//                this.date.setText(dateTimeFormat.format(publishDate));
//            }
//
//
//            this.date.setText(weather.getDt());

        } else {
            description.setText(null);
        }
    }

    public void bindClock() {
        Date date = new Date();
        this.date.setText(date.toString().substring(0, 10));
        this.time.setText(timeFormat.format(date));
    }

    public void startLocationAnimation(Animation animation) {
        this.mLocationIcon.startAnimation(animation);
    }

    public void stopLocationAnimation() {
        this.mLocationIcon.clearAnimation();
    }

    public void startLoadWeatherAnimation(Animation animation) {
        this.mRefresh.startAnimation(animation);
    }

    public void stopLoadWeatherAnimation() {
        this.mRefresh.clearAnimation();
    }


    private Uri getWeatherResourceUri(String partName, int weatherId) {
        String url = Config.HOST_WEATHER_IMG + partName + "/" + weatherId + "/" + System.currentTimeMillis();
        return Uri.parse(url);
    }

    public void autoLocFail() {
        location.setText(R.string.pink_weather_auto_location_fail);
    }
}