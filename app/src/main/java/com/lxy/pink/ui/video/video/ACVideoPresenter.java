package com.lxy.pink.ui.video.video;

import android.content.Context;

import com.lxy.pink.data.source.AppRepository;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by homelajiang on 2017/3/1 0001.
 */

public class ACVideoPresenter implements ACVideoContract.Presenter {


    private Context context;
    private ACVideoContract.View view;
    private AppRepository respository;
    private CompositeSubscription subscriptions;

    ACVideoPresenter(Context context, ACVideoContract.View view) {
        this.context = context;
        this.view = view;
        this.respository = AppRepository.getInstance();
        this.subscriptions = new CompositeSubscription();
        view.setPresenter(this);
    }

    @Override
    public void getVideoInfo(int contentId) {

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
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        context = null;
        view = null;
        subscriptions.clear();
    }
}
