package com.lxy.pink.ui.auth;

import com.lxy.pink.data.model.auth.Profile;
import com.lxy.pink.data.source.AppRepository;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by yuan on 2016/10/19.
 */

public class ProfilePresenter implements ProfileContract.Presenter {

    private  AppRepository appRepository;
    private  CompositeSubscription mSubscriptions;
    private ProfileContract.View view;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
        appRepository = AppRepository.getInstance();
        mSubscriptions = new CompositeSubscription();
        view.setPresenter(this);
    }

    @Override
    public void getProfile() {

    }

    @Override
    public void updateProfile(Profile profile) {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }
}
