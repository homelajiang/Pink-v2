package com.lxy.pink.ui.auth;

import com.lxy.pink.data.model.auth.Auth;
import com.lxy.pink.data.source.AppRepository;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by homelajiang on 2016/10/19 0019.
 */

public class SignPresenter implements SignContract.Presenter {


    private AppRepository appRepository;
    private SignContract.View view;
    private CompositeSubscription mSubscriptions;

    public SignPresenter(SignContract.View view) {
        this.view = view;
        appRepository = AppRepository.getInstance();
        mSubscriptions = new CompositeSubscription();
        view.setPresenter(this);
    }

    @Override
    public void signUp(String username, String password) {

    }

    @Override
    public void signIn(String username, String password) {
        appRepository.signIn(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Auth>() {

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
                        view.hideLoading();
                        view.handleError(e);
                    }

                    @Override
                    public void onNext(Auth auth) {
                        view.onSignIn(auth);
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
