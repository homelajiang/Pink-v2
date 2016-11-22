package com.lxy.pink.ui.music.musicList;

import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.data.source.AppRepository;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by homelajiang on 2016/11/3 0003.
 */

public class SongListPresenter implements SongListContract.Presenter {

    private SongListContract.View mView;
    private AppRepository mRepository;
    private CompositeSubscription mSubscriptions;

    public SongListPresenter(SongListContract.View view) {
        mView = view;
        mRepository = AppRepository.getInstance();
        mSubscriptions = new CompositeSubscription();
        mView.setPresenter(this);
    }


    @Override
    public void loadMusicList(List<String> filters) {

        Subscription subscription = mRepository.playList(filters)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PlayList>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(PlayList playList) {
                        mView.onMusicListLoaded(playList);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void subscribe() {
        loadMusicList(new ArrayList<String>());
    }

    @Override
    public void unSubscribe() {
        mView = null;
        mSubscriptions.clear();
    }
}
