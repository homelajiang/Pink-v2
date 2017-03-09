package com.lxy.pink.ui.video.video;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.lxy.pink.data.model.acfun.ACVideoInfo;
import com.lxy.pink.data.retrofit.api.RemoteService;
import com.lxy.pink.data.source.AppRepository;

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
    public void getVideoInfo(int contentId) {
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
    public void checkFollow(int userId, String access_token) {

    }

    @Override
    public void actionFollow(String name, int userId, String accessToken) {

    }

    @Override
    public void checkMark(int contentId, int userId, String accessToken) {

    }

    @Override
    public void actionMark(String name, int userId, int contentId, String accessToken) {

    }

    @Override
    public void getBananaInfo(String accessToken) {

    }

    @Override
    public void getBananaCheck(String accessToken) {

    }

    @Override
    public void sendBanana(String accessToken, int userId, int count, int contentId) {

    }

    @Override
    public void getDanmuku(int danmukuId) {

    }

    @Override
    public void getVideoComment(int contentId) {

    }

    @Override
    public void sendComment(String text, int quoteId, int contentId, String accessToken, int userId, String captcha) {

    }

    @Override
    public void getVideoRecommend(String id) {

    }

    @Override
    public void unSubscribe() {
        context = null;
        view = null;
        subscriptions.clear();
    }
}
