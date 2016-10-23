package com.lxy.pink.ui.main;

import com.lxy.pink.data.model.auth.Profile;
import com.lxy.pink.data.source.AppRepository;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yuan on 2016/10/23.
 */

public class MainPresenter implements MainContract.Presenter {
    private AppRepository appRepository;
    private CompositeSubscription mSubscriptions;
    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        appRepository = AppRepository.getInstance();
        mSubscriptions = new CompositeSubscription();
        this.view.setPresenter(this);
    }

    @Override
    public void getProfile(String profileId) {
        Subscription subscription = appRepository.getProfile(profileId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Profile>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Profile profile) {
                        view.profileLoad(profile);
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
