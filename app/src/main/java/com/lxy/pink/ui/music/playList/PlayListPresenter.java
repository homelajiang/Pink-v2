package com.lxy.pink.ui.music.playList;

import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.data.source.AppRepository;

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
