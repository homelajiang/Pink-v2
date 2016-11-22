package com.lxy.pink.ui.service;

import android.content.ContentResolver;
import android.text.TextUtils;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.lxy.pink.Injection;
import com.lxy.pink.data.model.location.BdLocation;
import com.lxy.pink.data.model.todo.Todo;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.data.source.AppRepository;
import com.lxy.pink.utils.Config;
import com.orhanobut.logger.Logger;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yuan on 2016/10/23.
 */

public class PinkServicePresenter implements PinkServiceContract.Presenter, BDLocationListener {

    private CompositeSubscription mSubscriptions;
    private AppRepository appRepository;
    private PinkServiceContract.View view;
    private LocationClient mLocationClient;


    public PinkServicePresenter(PinkServiceContract.View view) {
        this.view = view;
        appRepository = AppRepository.getInstance();
        mSubscriptions = new CompositeSubscription();
        initLocation();
        view.setPresenter(this);
    }

    private void initLocation() {
        if (mLocationClient != null) {
            return;
        }
        mLocationClient = new LocationClient(Injection.provideContext());
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
    public void getWeatherById(String cityId) {
        if (TextUtils.isEmpty(cityId))
            return;
        Subscription subscription = appRepository.getWeatherInfo(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Weather>() {

                    @Override
                    public void onStart() {
                        view.weatherLoadStart();
                    }

                    @Override
                    public void onCompleted() {
                        view.weatherLoadEnd();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.weatherLoadError(e);
                    }

                    @Override
                    public void onNext(Weather weather) {
                        view.weatherLoaded(weather);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getWeatherByLocation(double lat, double lon) {
        Subscription subscription = appRepository.getWeatherInfo(lat, lon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Weather>() {

                    @Override
                    public void onStart() {
                        view.weatherLoadStart();
                    }

                    @Override
                    public void onCompleted() {
                        view.weatherLoadEnd();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.weatherLoadError(e);
                    }

                    @Override
                    public void onNext(Weather weather) {
                        view.weatherLoaded(weather);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getTodoList(ContentResolver cr) {

        Subscription subscription = appRepository.getTodoList(cr)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TodoList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TodoList todoList) {
                        view.todoListLoaded(todoList);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getLocation() {
        mLocationClient.start();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        this.view = null;
        mSubscriptions.clear();
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        mLocationClient.stop();
        if (bdLocation == null) {
            view.locationError();
            Logger.d("get Location Error!");
            return;
        } else {
            Logger.d("get Location done!" + bdLocation.getTime());
        }

        int locationType = bdLocation.getLocType();
        if (locationType == BDLocation.TypeGpsLocation ||
                locationType == BDLocation.TypeNetWorkLocation ||
                locationType == BDLocation.TypeOffLineLocation) {

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
            }
            if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                bdLoc.setOperators(bdLocation.getOperators());
            }
            view.locationLoaded(bdLoc);
        } else {
            view.locationError();
        }

    }
}
