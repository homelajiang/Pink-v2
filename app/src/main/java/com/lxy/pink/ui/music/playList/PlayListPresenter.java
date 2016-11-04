package com.lxy.pink.ui.music.playList;

import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.data.source.AppRepository;
import com.orhanobut.logger.Logger;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by homelajiang on 2016/11/3 0003.
 */

public class PlayListPresenter implements PlayListContract.Presenter {

    private PlayListContract.View mView;
    private AppRepository mRepository;
    private CompositeSubscription mSubscriptions;

    public PlayListPresenter(PlayListContract.View view) {
        mView = view;
        mRepository = AppRepository.getInstance();
        mSubscriptions = new CompositeSubscription();
        mView.setPresenter(this);
    }


    @Override
    public void loadPlayList() {
        Subscription subscription = mRepository.playList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<PlayList>>() {
                    @Override
                    public void onCompleted() {
                        Logger.d("completed");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<PlayList> playLists) {
                        Logger.d("done");
                        mView.onPlayListLoaded(playLists);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void createPlatList(PlayList playList) {

    }

    @Override
    public void editPlayList(PlayList playList) {

    }

    @Override
    public void deletePlayList(PlayList playList) {

    }

    @Override
    public void subscribe() {
        loadPlayList();
    }

    @Override
    public void unSubscribe() {
        mView = null;
        mSubscriptions.clear();
    }
}
