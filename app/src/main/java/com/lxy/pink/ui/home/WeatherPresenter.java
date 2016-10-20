package com.lxy.pink.ui.home;

import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.data.source.AppRepository;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yuan on 2016/10/20.
 */

public class WeatherPresenter implements WeatherContract.Presenter {

    private CompositeSubscription mSubscriptions;
    private AppRepository appRepository;
    private WeatherContract.View view;

    public WeatherPresenter(WeatherContract.View view) {
        this.view = view;
        appRepository = AppRepository.getInstance();
        mSubscriptions = new CompositeSubscription();
        view.setPresenter(this);
    }

    @Override
    public void getWeatherById(String cityId) {
        appRepository.getWeatherInfo(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Weather>() {

                    @Override
                    public void onStart() {
                        view.showLoading();
                    }

                    @Override
                    public void onCompleted() {
                        view.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.handleLoadWeatherError(e);
                    }

                    @Override
                    public void onNext(Weather weather) {
                        view.weatherLoad(weather);
                    }
                });
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        this.view = null;
        mSubscriptions.clear();
    }
}
