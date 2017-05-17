package com.lxy.pink.ui.video.detail;

import android.support.annotation.NonNull;

import com.lxy.pink.data.model.acfun.ACVideoInfo;
import com.lxy.pink.data.source.AppRepository;
import com.lxy.pink.utils.schedulers.BaseSchedulerProvider;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by homelajiang on 2017/3/1 0001.
 */

public class ACVideoPresenter implements ACVideoContract.Presenter {


    private final int contentId;
    private final BaseSchedulerProvider mSchedulerProvider;
    private ACVideoContract.View view;
    private AppRepository respository;
    private CompositeSubscription subscriptions;

    ACVideoPresenter(@NonNull ACVideoContract.View view,
                     @NonNull BaseSchedulerProvider schedulerProvider,
                     @NonNull int contentId) {
        this.contentId = contentId;
        this.view = view;
        mSchedulerProvider = schedulerProvider;
        this.respository = AppRepository.getInstance();
        this.subscriptions = new CompositeSubscription();
        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        getVideoInfo(this.contentId);
    }

    @Override
    public void getVideoInfo(final int contentId) {
        Subscription subscription =
                respository.getVideoInfo(contentId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ACVideoInfo>() {
                            @Override
                            public void onCompleted() {
                            }
                            @Override
                            public void onError(Throwable e) {
                                view.getVideoInfoFail(e);
                            }
                            @Override
                            public void onNext(ACVideoInfo acVideoInfo) {
                                view.getVideoInfoSuccess(acVideoInfo);
                            }
                        });
        subscriptions.add(subscription);
    }


    @Override
    public void getDanmuku(int danmukuId) {

    }


    @Override
    public void unSubscribe() {
        view = null;
        subscriptions.clear();
    }
}
