package com.lxy.pink.ui.home.imp;

import com.lxy.pink.core.PinkServiceContract;

/**
 * Created by homelajiang on 2017/2/6 0006.
 */

public abstract class SimpleWeatherCallback implements PinkServiceContract.WeatherCallback {
    public void weatherLoadStart() {

    }

    public void weatherLoadEnd() {

    }

    public void weatherLoadError(Throwable e) {

    }

    public void weatherLocationReq() {

    }

    public void setPresenter(PinkServiceContract.Presenter presenter) {

    }
}
