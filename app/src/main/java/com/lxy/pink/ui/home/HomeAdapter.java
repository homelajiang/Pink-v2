package com.lxy.pink.ui.home;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.data.model.todo.Todo;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.data.source.PreferenceManager;
import com.lxy.pink.ui.base.adapter.IAdapterView;
import com.lxy.pink.ui.service.PinkServiceContract;
import com.lxy.pink.ui.widget.ExListView;
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

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements PinkServiceContract.View {

    private static SimpleDateFormat timeFormat;
    private final Context context;
    private final Handler handler;
    private final Runnable clockRunnable;
    private final Animation flickerAnimation;
    private final Animation unLimitedRotate;
    private List<Object> dataList = new ArrayList<>();
    public WeatherItemView weatherItemView;

    public HomeAdapter(Context context) {
        this.context = context;
        timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        dataList.add(new Weather());
        handler = new Handler();
        flickerAnimation = AnimationUtils.loadAnimation(context, R.anim.flicker);
        unLimitedRotate = AnimationUtils.loadAnimation(context, R.anim.unlimited_rotate);
        clockRunnable = new Runnable() {
            @Override
            public void run() {
                if (weatherItemView != null) {
                    weatherItemView.bindClock();
                }
                handler.postDelayed(clockRunnable, 1000);
            }
        };
    }

    public void stopClock() {
        handler.removeCallbacks(clockRunnable);
    }

    public void startClock() {
        handler.post(clockRunnable);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case NotifyType.WEATHER:
                weatherItemView = new WeatherItemView(context);
                return new RecyclerView.ViewHolder(weatherItemView) {
                };
            case NotifyType.TODO_LIST:
                return new RecyclerView.ViewHolder(new TodoItemView(context)) {
                };
            default:
                return new OtherViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_home_other, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case NotifyType.WEATHER:
                Weather weather = (Weather) getItem(position);
                ((WeatherItemView) holder.itemView).bind(weather, position);
                break;
            case NotifyType.TODO_LIST:
                TodoList todoList = (TodoList) getItem(position);
                ((TodoItemView) holder.itemView).bind(todoList, position);
                break;
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
        if (o instanceof TodoList)
            type = NotifyType.TODO_LIST;
        return type;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void weatherLoadStart() {
        weatherItemView.startLoadWeatherAnimation(unLimitedRotate);
    }

    @Override
    public void weatherLoadEnd() {
        weatherItemView.stopLoadWeatherAnimation();
    }

    @Override
    public void weatherLoadError(Throwable e) {
        weatherItemView.stopLoadWeatherAnimation();
    }

    @Override
    public void weatherLoaded(Weather weather) {
        if (weather.getCod() == Config.HOST_WEATHER_SUCCESS_CODE) {
            PreferenceManager.setCityId(context, String.valueOf(weather.getId()));
            weatherItemView.bind(weather, 0);
        } else {
            weatherLoadError(null);
        }
    }

    @Override
    public void todoListLoaded(TodoList todoList) {
        if (todoList == null || todoList.size() <= 0)
            return;
        int index = 0;
        for (int i = 1; i < dataList.size(); i++) {
            if (getItemViewType(i) == NotifyType.TODO_LIST) {
                index = i;
                break;
            }
        }
        if (index >= 1) {
            dataList.set(index, todoList);
            notifyItemChanged(index);
        } else {
            dataList.add(todoList);
            notifyItemInserted(dataList.size() - 1);
        }
    }

    @Override
    public void weatherLocationStart() {
        weatherItemView.startLocationAnimation(flickerAnimation);
    }

    @Override
    public void weatherLocationSuccess(double lat, double lon) {
        weatherItemView.stopLocationAnimation();
    }

    @Override
    public void weatherLocationFail() {
        if (weatherItemView != null) {
            weatherItemView.stopLocationAnimation();
            weatherItemView.autoLocFail();
        }
    }

    @Override
    public void setPresenter(PinkServiceContract.Presenter presenter) {
        //nothing to do
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
