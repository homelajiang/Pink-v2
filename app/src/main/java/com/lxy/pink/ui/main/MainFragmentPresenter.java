package com.lxy.pink.ui.main;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.lxy.pink.data.source.AppRepository;
import com.lxy.pink.data.source.PreferenceManager;
import com.lxy.pink.ui.player.PlayMode;
import com.lxy.pink.ui.player.PlaybackService;

import rx.subscriptions.CompositeSubscription;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by homelajiang on 2016/11/7 0007.
 */

public class MainFragmentPresenter implements MainFragmentContract.Presenter {

    private Context context;
    private MainFragmentContract.View view;
    private AppRepository repository;
    private CompositeSubscription subscriptions;
    private PlaybackService playbackService;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            serviceBound = true;
            playbackService = ((PlaybackService.LocalBuilder) service).getService();
            view.onPlaybackServiceBound(playbackService);
            view.onSongUpdated(playbackService.getPlayingSong());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            playbackService = null;
            view.onPlaybackServiceUnbound();
        }
    };
    private boolean serviceBound;

    public MainFragmentPresenter(Context context, MainFragmentContract.View view) {
        this.context = context;
        this.view = view;
        this.repository = AppRepository.getInstance();
        this.subscriptions = new CompositeSubscription();
        view.setPresenter(this);
    }

    @Override
    public void retrieveLastPlayMode() {
        PlayMode lastPlayMode = PreferenceManager.lastPlayMode(context);
        view.updatePlayMode(lastPlayMode);
    }

    @Override
    public void bindPlaybackService() {
        context.bindService(new Intent(context, PlaybackService.class), connection, BIND_AUTO_CREATE);
    }

    @Override
    public void unbindPlaybackService() {
        if (serviceBound) {
            context.unbindService(connection);
            serviceBound = false;
        }
    }

    @Override
    public void subscribe() {
        bindPlaybackService();
        retrieveLastPlayMode();
    }

    @Override
    public void unSubscribe() {
        unbindPlaybackService();
        context = null;
        view = null;
        subscriptions.clear();
    }
}
