package com.lxy.pink.ui.video;

import android.content.Context;
import android.widget.Toast;

import com.lxy.pink.data.model.acfun.ACAuth;
import com.lxy.pink.data.model.acfun.ACAuthRes;
import com.lxy.pink.data.model.acfun.ACProfile;
import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.data.retrofit.RetrofitAPI;
import com.lxy.pink.data.source.AppRepository;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yuan on 2016/12/21.
 */

public class VideoFragmentPresenter implements VideoContract.Presenter {

    private VideoContract.View view;
    private Context context;
    private AppRepository repository;
    private CompositeSubscription subscriptions;

    VideoFragmentPresenter(Context context, VideoContract.View view) {
        this.context = context;
        this.view = view;
        this.repository = AppRepository.getInstance();
        this.subscriptions = new CompositeSubscription();
        view.setPresenter(this);
    }

    @Override
    public void getRecommend() {
        Subscription subscription =
                repository.getRecommend()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ACRecommend>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.recommendError(e);
                            }

                            @Override
                            public void onNext(ACRecommend acRecommend) {
                                view.recommendLoad(acRecommend);
                            }
                        });
        subscriptions.add(subscription);
    }

    @Override
    public void login(String username, String password) {
        Subscription subscription =
                repository.ac_login(username, password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ACAuthRes>() {
                            @Override
                            public void onStart() {
                                view.loginStart();
                            }

                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                view.loginError(e);
                            }

                            @Override
                            public void onNext(ACAuthRes acAuthRes) {
                                view.loginSuccess(acAuthRes);
                            }
                        });
        subscriptions.add(subscription);
    }

    @Override
    public void getProfile(String uid) {
        Subscription subscription =
                repository.ac_getProfile(uid)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ACProfile>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(ACProfile acProfile) {
                                view.acProfileLoad(acProfile);
                            }
                        });
        subscriptions.add(subscription);
    }

    @Override
    public void subscribe() {
        getRecommend();
    }

    @Override
    public void unSubscribe() {
        context = null;
        view = null;
        subscriptions.clear();
    }
}
