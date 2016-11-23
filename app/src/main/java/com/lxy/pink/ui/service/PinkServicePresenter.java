package com.lxy.pink.ui.service;

import android.content.ContentResolver;
import android.location.LocationManager;
import android.text.TextUtils;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.lxy.pink.Injection;
import com.lxy.pink.data.model.location.PinkLocation;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.data.source.AppRepository;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yuan on 2016/10/23.
 */

public class PinkServicePresenter implements PinkServiceContract.Presenter, AMapLocationListener {

    private CompositeSubscription mSubscriptions;
    private AppRepository appRepository;
    private PinkServiceContract.View view;
    private Object objLock = new Object();
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mOption;


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
        mLocationClient = new AMapLocationClient(Injection.provideContext());
        mLocationClient.setLocationOption(getDefaultOption());
        mLocationClient.setLocationListener(this);
    }

    private AMapLocationClientOption getDefaultOption() {
        mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(20000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(10000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(false);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        return mOption;
    }

    @Override
    public void getWeather() {
        Subscription subscription = appRepository.getWeatherInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Weather>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        //request location is weather
                        view.weatherLocationReq();
                        getLocation();
                    }

                    @Override
                    public void onNext(Weather weather) {
                        if (weather != null) {
                            view.weatherLoaded(weather);
                        }
                        view.weatherLocationReq();
                        getLocation();
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void saveWeather(Weather weather) {
        Subscription subscription = appRepository.saveWeatherInfo(weather)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Void aVoid) {

                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getWeather(String cityId) {
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
    public void getWeather(double lat, double lon) {
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
                        if (weather != null) {
                            view.weatherLoaded(weather);
                            saveWeather(weather);
                        }
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
        startLocation();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        this.view = null;
        mSubscriptions.clear();
    }

    public void startLocation() {
        view.locationStart();
        mLocationClient.startLocation();
    }

    public void stopLocation() {
        mLocationClient.stopLocation();
    }

    private void destroyLocation() {
        if (null != mLocationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            mLocationClient.onDestroy();
            mLocationClient = null;
            mOption = null;
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        stopLocation();
        if (null == aMapLocation || aMapLocation.getErrorCode() != 0) {
            view.locationError();
            return;
        }

        PinkLocation location = new PinkLocation();

        location.setLongitude(aMapLocation.getLongitude());
        location.setLatitude(aMapLocation.getLatitude());
        location.setAltitude(aMapLocation.getAltitude());
        location.setLocType(aMapLocation.getLocationType());

        if (aMapLocation.getProvider().equalsIgnoreCase(LocationManager.GPS_PROVIDER)) {
            location.setAltitude(aMapLocation.getAltitude());
            location.setSpeed(aMapLocation.getSpeed());
            location.setDirection(aMapLocation.getBearing());
        } else {
            location.setTime(aMapLocation.getTime());
        }

        view.locationLoaded(location);

        //todo save the location
    }
}
