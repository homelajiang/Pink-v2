package com.lxy.pink.ui.video;

import android.content.Context;

import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.data.source.AppRepository;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
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
