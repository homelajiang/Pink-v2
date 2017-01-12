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
import com.lxy.pink.data.model.location.PinkLocation;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.ui.home.impl.PinkLocationCallback;
import com.lxy.pink.ui.home.impl.PinkWeatherCallback;
import com.lxy.pink.ui.home.view.PinkWeatherView;

/**
 * Created by homelajiang on 2016/12/23 0023.
 */

public class PinkWeatherModel extends EpoxyModel<PinkWeatherView> implements PinkWeatherCallback, PinkLocationCallback {

    @EpoxyAttribute
    PinkServiceContract.Presenter presenter;
    private PinkWeatherView weatherView;
    private boolean isBind;

    private Weather weather;
    private boolean locationRun;
    private boolean refreshRun;


    @Override
    protected int getDefaultLayout() {
        return R.layout.pink_home_weather_model;
    }

    @Override
    public void bind(final PinkWeatherView view) {
        this.weatherView = view;
        weatherView.bind(weather, locationRun, refreshRun);
        this.isBind = true;
    }


    @Override
    public void unbind(PinkWeatherView view) {
        super.unbind(view);
        this.isBind = false;
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    @Override
    public void weatherLoadStart() {
        refreshRun = true;
        if (isBind)
            weatherView.setRefreshAnim(true);
    }

    @Override
    public void weatherLoadEnd() {
        refreshRun = false;
        if (isBind)
            weatherView.setRefreshAnim(false);
    }

    @Override
    public void weatherLoadError(Throwable e) {

    }

    @Override
    public void weatherLoaded(Weather weather) {
        this.weather = weather;
        if (isBind)
            weatherView.setWeather(weather);
    }

    @Override
    public void locationStart() {
        this.locationRun = true;
        if (isBind)
            weatherView.setLocationAnim(true);
    }

    @Override
    public void locationLoaded(PinkLocation pinkLocation) {
        this.locationRun = false;
        if (isBind)
            weatherView.setLocationAnim(false);
    }
}
