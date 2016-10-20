package com.lxy.pink.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxy.pink.R;
import com.lxy.pink.data.model.todo.Todo;
import com.lxy.pink.data.model.weather.Weather;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuan on 2016/10/20.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Object> dataList = new ArrayList<>();

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
//            case NotifyType.WEATHER:
//                ((WeatherViewHolder)holder).
//            case NotifyType.TODO:
//                return new TodoViewHolder(
//                        LayoutInflater.from(parent.getContext())
//                                .inflate(R.layout.item_home_todo, parent, false));
//            default:
//                return new OtherViewHolder(
//                        LayoutInflater.from(parent.getContext())
//                                .inflate(R.layout.item_home_other, parent, false));
        }
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


    static class WeatherViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.test)
        TextView test;

        WeatherViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bindData(Weather weather){
            //TODO
        }
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.test)
        TextView test;

        TodoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        void bindData(Todo todo){
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
}
