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
    public ACVideoInfo mAcVideoInfo;

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
                                if (mAcVideoInfo != null && mAcVideoInfo.getCode() == 200) {
                                    ACVideoInfo.DataBean info = mAcVideoInfo.getData();
                                    ACAuth acAuth = PreferenceManager.getAcAuth(context);
                                    if (acAuth != null) {
                                        checkFollow(info.getOwner().getId(), acAuth.getAccess_token());
                                        checkMark(info.getContentId(), info.getOwner().getId(), acAuth.getAccess_token());
                                    }
                                    getVideoRecommend("ac" + info.getContentId());
                                    getUserContributeVideo(info.getOwner().getId());
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.getVideoInfoFail(e);
                            }

                            @Override
                            public void onNext(ACVideoInfo acVideoInfo) {
                                mAcVideoInfo = acVideoInfo;
                                view.getVideoInfoSuccess(acVideoInfo);
                            }
                        });
        subscriptions.add(subscription);
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
    public void actionFollow(String name, int userId, String accessToken) {

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

    @Override
    public void unSubscribe() {
        context = null;
        view = null;
        subscriptions.clear();
    }
}
