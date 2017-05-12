package com.lxy.pink.ui.video.info;

import android.content.Context;

import com.lxy.pink.data.model.acfun.ACCheckFollow;
import com.lxy.pink.data.model.acfun.ACUserContribute;
import com.lxy.pink.data.model.acfun.ACVideoMark;
import com.lxy.pink.data.model.acfun.ACVideoSearchLike;
import com.lxy.pink.data.source.AppRepository;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by homelajiang on 2017/5/2 0002.
 */

public class ACVInfoPresenter implements ACVInfoContract.Presenter {

    private int contentId;
    private Context context;
    private ACVInfoContract.View view;
    private AppRepository respository;
    private CompositeSubscription subscriptions;

    ACVInfoPresenter(Context context, ACVInfoContract.View view, int contentId) {
        this.contentId = contentId;
        this.context = context;
        this.view = view;
        this.respository = AppRepository.getInstance();
        this.subscriptions = new CompositeSubscription();
        view.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
            context = null;
            view = null;
            subscriptions.clear();
    }

    @Override
    public void checkFollow(int userId, String access_token) {
        Subscription subscription = respository
                .checkFollow(userId, access_token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ACCheckFollow>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.checkFollowFail(e);
                    }

                    @Override
                    public void onNext(ACCheckFollow acCheckFollow) {
                        view.checkFollowSuccess(acCheckFollow);
                    }
                });
        subscriptions.add(subscription);
    }

    @Override
    public void follow(String name, int userId, String accessToken) {

    }

    @Override
    public void checkMark(int contentId, int userId, String accessToken) {
        Subscription subscription = respository
                .checkMark(contentId, userId, accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ACVideoMark>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.checkMarkFail(e);
                    }

                    @Override
                    public void onNext(ACVideoMark acVideoMark) {
                        view.checkMarkSuccess(acVideoMark);
                    }
                });
        subscriptions.add(subscription);
    }

    @Override
    public void mark(String name, int userId, int contentId, String accessToken) {

    }

    @Override
    public void getBananaInfo(String accessToken) {

    }

    @Override
    public void checkBanana(String accessToken) {

    }

    @Override
    public void sendBanana(String accessToken, int userId, int count, int contentId) {

    }

    @Override
    public void getVideoRecommend(String id) {
        Subscription subscription = respository
                .getVideoRecommend(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ACVideoSearchLike>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getVideoRecommendFail(e);
                    }

                    @Override
                    public void onNext(ACVideoSearchLike acVideoSearchLike) {
                        view.getVideoRecommendSuccess(acVideoSearchLike);
                    }
                });
        subscriptions.add(subscription);
    }

    @Override
    public void getUserContributeVideo(int userId) {
        Subscription subscription = respository
                .getUserContribute(1, 10, userId, 1, 6)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ACUserContribute>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getUserContributeVideoFail(e);
                    }

                    @Override
                    public void onNext(ACUserContribute contribute) {
                        view.getUserContributeVideoSuccess(contribute);
                    }
                });
        subscriptions.add(subscription);
    }
}
