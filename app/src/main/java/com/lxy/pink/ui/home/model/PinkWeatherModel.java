package com.lxy.pink.ui.home.model;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.ui.home.view.PinkWeatherView;

/**
 * Created by homelajiang on 2016/12/23 0023.
 */

public class PinkWeatherModel extends EpoxyModel<PinkWeatherView>{

    @EpoxyAttribute
    Weather weather;

//    private final Animation flickerAnimation = AnimationUtils.loadAnimation(context, R.anim.flicker);
//    private final Animation unLimitedRotate = AnimationUtils.loadAnimation(context, R.anim.unlimited_rotate);



    @Override
    protected int getDefaultLayout() {
        return R.layout.pink_home_weather_model;
    }

    @Override
    public void bind(PinkWeatherView view) {
        if(weather==null)
            return;
        view.setWeather(weather);
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
