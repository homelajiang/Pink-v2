package com.lxy.pink.ui.service;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.lxy.pink.data.model.location.BdLocation;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.data.source.PreferenceManager;
import com.lxy.pink.ui.base.BaseService;
import com.lxy.pink.utils.Config;
import com.orhanobut.logger.Logger;

/**
 * Created by yuan on 2016/10/22.
 */

public class PinkService extends BaseService implements PinkServiceContract.View, BDLocationListener {


    private PinkServiceContract.View serviceCallback;
    private PinkServiceContract.Presenter presenter;
    private LocationClient mLocationClient;
    private String preLocalTime;
    private boolean weatherRequestLocation;
//    private boolean otherRequestLocation;

    @Override
    public void onCreate() {
        super.onCreate();
        new PinkServicePresenter(this).subscribe();
        // TODO_LIST unSubscribe
        initBaiduMap();
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

    private void initBaiduMap() {
        if (mLocationClient != null) {
            return;
        }
        mLocationClient = new LocationClient(getApplicationContext());
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd0911");
        option.setScanSpan(Config.DEFAULT_LOCATE_DELAY);
        option.setIsNeedAddress(false);
        option.setOpenGps(false);
        option.setLocationNotify(false);
        option.setIsNeedLocationDescribe(false);
        option.setIsNeedLocationPoiList(false);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(true);
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(this);
    }


    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        Logger.d("location");
        if (bdLocation == null)
            return;
        //some thing to do
        if (weatherRequestLocation) {
            weatherRequestLocation = false;
            int locationType = bdLocation.getLocType();

            if (locationType == BDLocation.TypeGpsLocation ||
                    locationType == BDLocation.TypeNetWorkLocation ||
                    locationType == BDLocation.TypeOffLineLocation) {
                this.weatherLocationSuccess(bdLocation.getLatitude(), bdLocation.getLongitude());
            } else {
                this.weatherLocationFail();
            }
        }

        //if location not change  Don't save it
        if (bdLocation.getTime().equals(preLocalTime))
            return;


        preLocalTime = bdLocation.getTime();
        BdLocation bdLoc = new BdLocation();

        bdLoc.setTime(bdLocation.getTime());
        bdLoc.setLatitude(bdLocation.getLatitude());
        bdLoc.setLongitude(bdLocation.getLongitude());
        bdLoc.setRadius(bdLocation.getRadius());
        bdLoc.setLocType(bdLocation.getLocType());
        if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
            bdLoc.setAltitude(bdLocation.getAltitude());
            bdLoc.setSpeed(bdLocation.getSpeed());
            bdLoc.setDirection(bdLocation.getDirection());
        } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
            bdLoc.setOperators(bdLocation.getOperators());
        } else if (bdLoc.getLocType() == BDLocation.TypeOffLineLocation) {

        } else {
            return;
        }
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
        if (serviceCallback != null)
            serviceCallback.weatherLoaded(weather);
    }

    @Override
    public void todoListLoaded(TodoList todoList) {
        if (serviceCallback != null)
            serviceCallback.todoListLoaded(todoList);
    }

    @Override
    public void weatherLocationStart() {
        if (serviceCallback != null)
            serviceCallback.weatherLocationStart();
    }

    @Override
    public void weatherLocationSuccess(double lat, double lon) {
        if (serviceCallback != null)
            serviceCallback.weatherLocationSuccess(lat, lon);
        presenter.getWeatherByLocation(lat, lon);
    }

    @Override
    public void weatherLocationFail() {
        if (serviceCallback != null)
            serviceCallback.weatherLocationFail();
    }

    @Override
    public void setPresenter(PinkServiceContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public class PinkBinder extends Binder {
        public PinkService getService() {
            return PinkService.this;
        }

        public void getWeather(){

        }

        public void getWeatherById(String cityId) {
            presenter.getWeatherById(cityId);
        }

        public void getWeatherByLocation() {
            weatherRequestLocation = true;
            mLocationClient.start();
            weatherLocationStart();
        }

        public void getTodoList() {
            presenter.getTodoList(getContentResolver());
        }

    }
}
