package com.lxy.pink.ui.home.model;

import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.core.PinkService;
import com.lxy.pink.core.PinkServiceContract;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.ui.home.impl.PinkWeatherCallback;
import com.lxy.pink.ui.home.view.PinkWeatherView;

/**
 * Created by homelajiang on 2016/12/23 0023.
 */

public class PinkWeatherModel extends EpoxyModel<PinkWeatherView> implements PinkWeatherCallback {

    @EpoxyAttribute
    Weather weather;
    @EpoxyAttribute
    PinkServiceContract.Presenter presenter;
    private PinkWeatherView weatherView;

    //    private final Animation flickerAnimation = AnimationUtils.loadAnimation(context, R.anim.flicker);
//    private final Animation unLimitedRotate = AnimationUtils.loadAnimation(context, R.anim.unlimited_rotate);

    @Override
    protected int getDefaultLayout() {
        return R.layout.pink_home_weather_model;
    }

    @Override
    public void bind(final PinkWeatherView view) {
        this.weatherView = view;
        weatherLoaded(weather);
    }


    @Override
    public void unbind(PinkWeatherView view) {
        super.unbind(view);// TODO: 2016/12/29 0029 处理view被回收的情况
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
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
        this.weather = weather;
        if (weather == null || weatherView == null)
            return;
        weatherView.setWeather(weather);
    }
}
