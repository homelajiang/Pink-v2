package com.lxy.pink.ui.service;

import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.lxy.pink.data.model.location.PinkLocation;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.data.source.PreferenceManager;
import com.lxy.pink.ui.base.BaseService;

import java.io.File;

/**
 * Created by yuan on 2016/10/22.
 */

public class PinkService extends BaseService implements PinkServiceContract.View {


    private PinkServiceContract.View serviceCallback;
    private PinkServiceContract.Presenter presenter;
    private boolean weatherRequestLocation;

    @Override
    public void onCreate() {
        super.onCreate();
        new PinkServicePresenter(this).subscribe();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.getLocation();
            }
        }, 10000);
        // TODO_LIST unSubscribe
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PinkBinder();
    }


    public void registerWeatherCallback(PinkServiceContract.View weatherCallback) {
        this.serviceCallback = weatherCallback;
    }

    public void unRegisterWeatherCallback() {
        this.serviceCallback = null;
    }

    @Override
    public void weatherLoadStart() {
        if (serviceCallback != null)
            serviceCallback.weatherLoadStart();
    }

    @Override
    public void weatherLoadEnd() {
        if (serviceCallback != null)
            serviceCallback.weatherLoadEnd();
    }

    @Override
    public void weatherLoadError(Throwable e) {
        if (serviceCallback != null)
            serviceCallback.weatherLoadError(e);
    }

    @Override
    public void weatherLoaded(Weather weather) {
        //todo save the weather
        if (serviceCallback != null)
            serviceCallback.weatherLoaded(weather);
    }

    @Override
    public void todoListLoaded(TodoList todoList) {
        if (serviceCallback != null)
            serviceCallback.todoListLoaded(todoList);
    }

    @Override
    public void locationStart() {
        if (weatherRequestLocation && serviceCallback != null) {
            serviceCallback.locationStart();
        }
    }

    @Override
    public void locationLoaded(PinkLocation pinkLocation) {
        //todo save into db
        if (weatherRequestLocation && serviceCallback != null) {
            serviceCallback.locationLoaded(pinkLocation);
            weatherRequestLocation = false;
            presenter.getWeather(pinkLocation.getLatitude(), pinkLocation.getLongitude());
        }
    }

    @Override
    public void locationError() {
        if (weatherRequestLocation && serviceCallback != null) {
            serviceCallback.locationError();
            weatherRequestLocation = false;
            String cityId = PreferenceManager.getCityId(getContext());
            if (!TextUtils.isEmpty(cityId)) {
                String[] temp = cityId.split(File.separator);
                if (temp.length == 2) {
                    presenter.getWeather(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]));
                } else {
                    Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void setPresenter(PinkServiceContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public class PinkBinder extends Binder {
        public PinkService getService() {
            return PinkService.this;
        }

        public void getWeather() {
            weatherRequestLocation = true;
            presenter.getLocation();
        }

        public void getTodoList() {
            presenter.getTodoList(getContentResolver());
        }

    }
}
