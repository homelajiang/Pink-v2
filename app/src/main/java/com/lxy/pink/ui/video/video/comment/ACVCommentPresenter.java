package com.lxy.pink.ui.video.video.comment;

import android.content.Context;

import com.lxy.pink.data.model.acfun.ACVideoComment;
import com.lxy.pink.data.model.acfun.ACVideoCommentData;
import com.lxy.pink.data.source.AppRepository;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by homelajiang on 2017/5/2 0002.
 */

public class ACVCommentPresenter implements ACVCommentContract.Presenter {

    private final int contentId;
    private final Context context;
    private final ACVCommentContract.View view;
    private final AppRepository respository;
    private final CompositeSubscription subscriptions;
    private int PageNo;

    ACVCommentPresenter(Context context, ACVCommentContract.View view, int contentId) {
        this.contentId = contentId;
        this.context = context;
        this.view = view;
        this.respository = AppRepository.getInstance();
        this.subscriptions = new CompositeSubscription();
        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        getVideoComment(this.contentId);
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void getVideoComment(int contentId) {
        respository.getVideoComment(contentId, PageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ACVideoCommentData>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    view.getVideoCommentFail(e);
                    }
                    @Override
                    public void onNext(ACVideoCommentData acVideoComment) {
                        view.getVideoCommentSuccess(acVideoComment);
                    }
                });
    }

    @Override
    public void sendComment(String text, int quoteId, int contentId, String accessToken, int userId, String captcha) {

    }
}
