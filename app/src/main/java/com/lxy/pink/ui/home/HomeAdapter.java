package com.lxy.pink.ui.home;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.data.model.todo.Todo;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.ui.base.adapter.IAdapterView;
import com.lxy.pink.ui.service.WeatherCallback;
import com.lxy.pink.utils.Config;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuan on 2016/10/20.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements WeatherCallback {

    private static SimpleDateFormat timeFormater;
    private List<Object> dataList = new ArrayList<>();

    public HomeAdapter() {
        this.timeFormater = new SimpleDateFormat("HH:mm", Locale.getDefault());
        dataList.add(new Weather());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case NotifyType.WEATHER:
                return new WeatherViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_home_weather, parent, false));
            case NotifyType.TODO:
                return new TodoViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_home_todo, parent, false));
            default:
                return new OtherViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_home_other, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case NotifyType.WEATHER:
                Weather weather = (Weather) getItem(position);
//                ((WeatherViewHolder) holder).
                if (weather.getId() <= 0)
                    return;
                ((WeatherViewHolder) holder).bind(weather, position);
            case NotifyType.TODO:
            default:
        }
    }

    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        Object o = dataList.get(position);
        int type = NotifyType.OTHER;
        if (o instanceof Weather)
            type = NotifyType.WEATHER;
        if (o instanceof Todo)
            type = NotifyType.TODO;
        return type;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void loadWeatherStart() {
    }

    @Override
    public void loadWeather(final Weather weather) {

        if (weather.getCod() == Config.HOST_WEATHER_SUCCESS_CODE) {
            dataList.set(0, weather);
            notifyItemChanged(0);
        } else {

        }
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
////                dataList.set(0, weather);
//                notifyItemChanged(0);
//            }
//        },5000);

    }

    @Override
    public void loadWeatherError(Throwable e) {
    }

    @Override
    public void loadWeatherCompleted() {
    }


    static class TodoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.test)
        TextView test;

        TodoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bindData(Todo todo) {
            //TODO
        }
    }

    static class OtherViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.test)
        TextView test;

        OtherViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class WeatherViewHolder extends RecyclerView.ViewHolder implements IAdapterView<Weather> {
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

        WeatherViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


        @Override
        public void bind(Weather weather, int position) {
            location.setText(String.valueOf(weather.getName()));
            temperature.setText(String.valueOf((int) weather.getMain().getTemp() + "°"));
            if (weather.getWeather() != null && weather.getWeather().size() > 0) {
                Weather.WeatherBean weatherBean = weather.getWeather().get(0);

                description.setText(String.valueOf(weatherBean.getMain()));
                background.setImageURI(getWeatherResourceUri("background", weatherBean.getId()));
                light.setImageURI(getWeatherResourceUri("light", weatherBean.getId()));
                sun.setImageURI(getWeatherResourceUri("sun", weatherBean.getId()));
                building.setImageURI(getWeatherResourceUri("building", weatherBean.getId()));

            } else {
                description.setText(null);
            }
            Date date = new Date(weather.getDt() * 1000L);
            this.date.setText(date.toString().substring(0, 10));
            this.time.setText(timeFormater.format(date));
        }

        private Uri getWeatherResourceUri(String partName, int weatherId) {
            String url = Config.HOST_WEATHER_IMG + partName + "/" + weatherId + "/" + System.currentTimeMillis();
            return Uri.parse(url);
        }
    }
}
