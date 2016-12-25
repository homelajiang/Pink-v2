package com.lxy.pink.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAdapter;
import com.lxy.pink.R;
import com.lxy.pink.core.PinkServiceContract;
import com.lxy.pink.data.model.location.PinkLocation;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.ui.home.model.PinkCalendarModel;
import com.lxy.pink.ui.home.model.PinkCalendarModel_;
import com.lxy.pink.ui.home.model.PinkMusicModel_;
import com.lxy.pink.ui.home.model.PinkWeatherModel;
import com.lxy.pink.ui.home.model.PinkWeatherModel_;
import com.lxy.pink.utils.Config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuan on 2016/10/20.
 */

public class HomeAdapter extends EpoxyAdapter implements PinkServiceContract.View {

    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private final Context context;
    private List<Object> dataList = new ArrayList<>();
    private PinkServiceContract.Presenter presenter;

    private PinkCalendarModel_ pinkCalendarModel_;
    private PinkWeatherModel_ pinkWeatherModel_;
    private PinkMusicModel_ pinkMusicModel_;

    public HomeAdapter(Context context) {
        enableDiffing();
        this.context = context;
        pinkWeatherModel_ = new PinkWeatherModel_();
        addModel(pinkWeatherModel_);
        pinkMusicModel_ = new PinkMusicModel_();
        addModel(pinkMusicModel_);
    }

    @Override
    public void weatherLoadStart() {
//        weatherItemView.startLoadWeatherAnimation(unLimitedRotate);
    }

    @Override
    public void weatherLoadEnd() {
    }

    @Override
    public void weatherLoadError(Throwable e) {
    }

    @Override
    public void weatherLoaded(Weather weather) {

        if (weather != null && weather.getCod() == Config.HOST_WEATHER_SUCCESS_CODE) {
            pinkWeatherModel_.weather(weather);
            notifyModelChanged(pinkWeatherModel_);
        } else {
            weatherLoadError(null);
        }
    }

    @Override
    public void todoListLoaded(TodoList todoList) {

        if (pinkCalendarModel_ == null) {
            pinkCalendarModel_ = new PinkCalendarModel_()
                    .todoList(todoList);
            addModel(pinkCalendarModel_);
        } else {
            pinkCalendarModel_.todoList(todoList);
            notifyModelChanged(pinkCalendarModel_);
        }
    }

    @Override
    public void locationStart() {
    }

    @Override
    public void locationLoaded(PinkLocation pinkLocation) {
    }

    @Override
    public void locationError() {
    }

    @Override
    public void weatherLocationReq() {
        //nothing to do
    }

    @Override
    public void setPresenter(PinkServiceContract.Presenter presenter) {
        this.presenter = presenter;
    }


    static class OtherViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.test)
        TextView test;

        OtherViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
