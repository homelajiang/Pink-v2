package com.lxy.pink.ui.home.model;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.core.PinkService;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.ui.home.view.PinkWeatherView;

/**
 * Created by homelajiang on 2016/12/23 0023.
 */

public class PinkWeatherModel extends EpoxyModel<PinkWeatherView> {

    @EpoxyAttribute
    PinkService pinkService;
    @EpoxyAttribute
    Weather weather;


    @Override
    protected int getDefaultLayout() {
        return R.layout.pink_home_weather_model;
    }

    @Override
    public void bind(final PinkWeatherView view) {
        view.bind(pinkService, weather);
    }


    @Override
    public void unbind(PinkWeatherView view) {
        super.unbind(view);
        view.unBind();
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
