package com.lxy.pink.ui.home;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuan on 2016/10/20.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements PinkServiceContract.View {

    private static SimpleDateFormat timeFormater;
    private final Context context;
    private List<Object> dataList = new ArrayList<>();

    public HomeAdapter(Context context) {
        this.context = context;
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
            case NotifyType.TODO_LIST:
                return new TodoListViewHolder(
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
                if (weather.getId() <= 0)
                    return;
                ((WeatherViewHolder) holder).bind(weather, position);
                break;
            case NotifyType.TODO_LIST:
                TodoList todoList = (TodoList) getItem(position);
                ((TodoListViewHolder) holder).bind(todoList, position);
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

    }

    @Override
    public void weatherLoadEnd() {

    }

    @Override
    public void weatherLoadError(Throwable e) {

    }

    @Override
    public void weatherLoaded(Weather weather) {
        if (weather.getCod() == Config.HOST_WEATHER_SUCCESS_CODE) {
            PreferenceManager.setCityId(context, String.valueOf(weather.getId()));
            dataList.set(0, weather);
            notifyItemChanged(0);
        } else {

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
        //TODO when no current to do notify it!!!
    }

    @Override
    public void weatherLocationSuccess(double lat, double lon) {
        //nothing to do
    }

    @Override
    public void weatherLocationFail() {
        //TODO get location fail
        Toast.makeText(context, "定位失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(PinkServiceContract.Presenter presenter) {
        //nothing to do
    }


    class TodoListViewHolder extends RecyclerView.ViewHolder implements IAdapterView<TodoList> {

        private TextView count;
        private View headView;
        @BindView(R.id.exListView)
        ExListView mExListView;
        TodoAdapter todoAdapter;

        TodoListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            todoAdapter = new TodoAdapter(context);
            mExListView.setAdapter(todoAdapter);
            this.headView = LayoutInflater.from(context).inflate(R.layout.item_home_todo_header, null);
            this.count = (TextView) headView.findViewById(R.id.todo_count);
            mExListView.addHeaderView(headView);
        }

        @Override
        public void bind(TodoList item, int position) {
            count.setText(String.format(context.getString(R.string.pink_todo_count), item.size()));
            List<Todo> temp = new ArrayList<>();
            long now = System.currentTimeMillis();

            for (Todo todo : item.getTodoList()) {

                long start = Long.parseLong(todo.getDtstart());
                long end = Long.parseLong(todo.getDtend());

                if (end - start == 86400000 || start >= now) {
                    temp.add(todo);
                }
            }

            todoAdapter.setList(temp);
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

                description.setText(String.valueOf(weatherBean.getDescription()));
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
