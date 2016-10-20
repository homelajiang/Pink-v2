package com.lxy.pink.ui.auth;

import com.lxy.pink.data.model.auth.Auth;
import com.lxy.pink.data.model.auth.Profile;
import com.lxy.pink.data.source.AppRepository;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yuan on 2016/10/19.
 */

public class ProfilePresenter implements ProfileContract.Presenter {

    private AppRepository appRepository;
    private CompositeSubscription mSubscriptions;
    private ProfileContract.View view;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
        appRepository = AppRepository.getInstance();
        mSubscriptions = new CompositeSubscription();
        view.setPresenter(this);
    }

    @Override
    public void getProfile(String profileId) {
        appRepository.getProfile(profileId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Profile>() {

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
                        view.handleLoadError(e);
                    }

                    @Override
                    public void onNext(Profile profile) {
                        view.profileLoad(profile);
                    }
                });
    }

    @Override
    public void updateProfile(Auth auth, final Profile profile) {
        appRepository.updateProfile(auth, profile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Profile>() {

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
                        view.handleUploadError(e);
                    }

                    @Override
                    public void onNext(Profile profile) {
                        view.profileUploaded(profile);
                    }
                });
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }
}
