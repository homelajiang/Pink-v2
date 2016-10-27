package com.lxy.pink.ui.service;

import android.content.ContentResolver;
import android.text.TextUtils;

import com.lxy.pink.data.model.todo.Todo;
import com.lxy.pink.data.model.todo.TodoList;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.data.source.AppRepository;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yuan on 2016/10/23.
 */

public class PinkServicePresenter implements PinkServiceContract.Presenter {

    private CompositeSubscription mSubscriptions;
    private AppRepository appRepository;
    private PinkServiceContract.View view;


    public PinkServicePresenter(PinkServiceContract.View view) {
        this.view = view;
        appRepository = AppRepository.getInstance();
        mSubscriptions = new CompositeSubscription();
        view.setPresenter(this);
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
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        this.view = null;
        mSubscriptions.clear();
    }
}
