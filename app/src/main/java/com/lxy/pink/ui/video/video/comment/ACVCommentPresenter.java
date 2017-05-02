package com.lxy.pink.ui.video.video.comment;

import android.content.Context;

import com.lxy.pink.data.source.AppRepository;

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

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void getVideoComment(int contentId) {

    }

    @Override
    public void sendComment(String text, int quoteId, int contentId, String accessToken, int userId, String captcha) {

    }
}
