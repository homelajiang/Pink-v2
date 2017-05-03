package com.lxy.pink.ui.video.video;

import android.content.Context;

import com.lxy.pink.data.model.acfun.ACAuth;
import com.lxy.pink.data.model.acfun.ACCheckFollow;
import com.lxy.pink.data.model.acfun.ACUserContribute;
import com.lxy.pink.data.model.acfun.ACVideoInfo;
import com.lxy.pink.data.model.acfun.ACVideoMark;
import com.lxy.pink.data.model.acfun.ACVideoSearchLike;
import com.lxy.pink.data.source.AppRepository;
import com.lxy.pink.data.source.PreferenceManager;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by homelajiang on 2017/3/1 0001.
 */

public class ACVideoPresenter implements ACVideoContract.Presenter {


    private final int contentId;
    private Context context;
    private ACVideoContract.View view;
    private AppRepository respository;
    private CompositeSubscription subscriptions;

    ACVideoPresenter(Context context, ACVideoContract.View view, int contentId) {
        this.contentId = contentId;
        this.context = context;
        this.view = view;
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
        context = null;
        view = null;
        subscriptions.clear();
    }
}
