package com.lxy.pink.ui.home;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.pink.R;
import com.lxy.pink.RxBus;
import com.lxy.pink.core.PinkService;
import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.data.source.PreferenceManager;
import com.lxy.pink.event.PlayListLoadedEvent;
import com.lxy.pink.event.PlayListNowEvent;
import com.lxy.pink.player.PlaybackService;
import com.lxy.pink.ui.base.BaseFragment;
import com.lxy.pink.ui.permission.FcPermissions;
import com.lxy.pink.ui.permission.FcPermissionsCallbacks;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by yuan on 2016/10/18.
 */
public class HomeFragment extends BaseFragment implements FcPermissionsCallbacks {

    public static final String TAG = "HomeFragment";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private PinkService.PinkBinder pinkBinder;
    private HomeAdapter homeAdapter;
    private String[] PERMISSION_ALL = {
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private int PERMISSION_CODE_ALL = 3;
    private boolean pinkServiceConnected;
    private PlaybackService mPlayer;
    private boolean musicServiceConnected;

    private ServiceConnection pinkServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            pinkServiceConnected = true;
            pinkBinder = (PinkService.PinkBinder) service;
            pinkBinder.getService().registerCallback(homeAdapter);
            burstLink();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicServiceConnected = false;
            pinkBinder = null;
        }
    };

    private ServiceConnection musicServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicServiceConnected = true;
            mPlayer = ((PlaybackService.LocalBuilder) service).getService();
            mPlayer.registerCallback(homeAdapter);
            homeAdapter.setMusicService(mPlayer);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicServiceConnected = false;
            mPlayer = null;
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recyclerview_with_refresh, container, false);
        ButterKnife.bind(this, root);
        initView();
        initData();
        return root;
    }

    private void initData() {
        Intent intent = new Intent(getContext(), PinkService.class);
        getActivity().bindService(intent, pinkServiceConnection, Context.BIND_AUTO_CREATE);
        Intent musicIntent = new Intent(getContext(), PlaybackService.class);
        getContext().bindService(musicIntent, musicServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void initView() {
        homeAdapter = new HomeAdapter(getContext());
        int spanCount = getSpanCount();
        homeAdapter.setSpanCount(spanCount);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), spanCount);
        gridLayoutManager.setSpanSizeLookup(homeAdapter.getSpanSizeLookup());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new HomeItemDecoration());
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(homeAdapter);
    }

    /**
     * ★ burst link ★
     */
    private void burstLink() {
        FcPermissions.requestPermissions(this, "开始获取权限", PERMISSION_CODE_ALL, PERMISSION_ALL);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        FcPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (pinkServiceConnected) {
            pinkBinder.getService().unRegisterCallback(homeAdapter);
            pinkServiceConnected = false;
            pinkBinder = null;
            getContext().unbindService(pinkServiceConnection);
        }

        if (musicServiceConnected) {
            mPlayer.unregisterCallback(homeAdapter);
            musicServiceConnected = false;
            mPlayer = null;
            getContext().unbindService(musicServiceConnection);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (perms.contains(Manifest.permission.READ_CALENDAR)) {
            if (pinkBinder != null) {
                pinkBinder.getTodoList();
            }
        }
        if (perms.contains(Manifest.permission.ACCESS_FINE_LOCATION)) {
            if (pinkBinder != null) {
                pinkBinder.getWeather();
            }
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (perms.contains(Manifest.permission.READ_CALENDAR)) {
            FcPermissions.checkDeniedPermissionsNeverAskAgain(this, "需要获取读取日历的权限"
                    , R.string.pink_setting, R.string.pink_cancel, null, perms);
        }
        if (perms.contains(Manifest.permission.ACCESS_FINE_LOCATION)) {
            FcPermissions.checkDeniedPermissionsNeverAskAgain(this, "需要获取地理位置的权限"
                    , R.string.pink_setting, R.string.pink_cancel, null, perms);
        }
    }

    public int getSpanCount() {
        return 6;
    }

    @Override
    protected Subscription subscribeEvents() {
        return RxBus.getInstance().toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        if (o instanceof PlayListNowEvent) {
                            onPlayListNowEvent((PlayListNowEvent) o);
                        } else if (o instanceof PlayListLoadedEvent) {
                            onPlayListEvent((PlayListLoadedEvent) o);
                        }
                    }
                })
                .subscribe(RxBus.defaultSubscriber());
    }

    private void onPlayListEvent(PlayListLoadedEvent o) {
        if (mPlayer == null)
            return;
        PlayList playList = o.playList;
        if (playList == null)
            return;
        long songId = PreferenceManager.getLastSong(getContext());
        if (songId == 0)
            return;
        int playIndex = -1;
        for (int i = 0; i < playList.getNumOfSongs(); i++) {
            if (songId == playList.getSong(i).getId()) {
                playIndex = i;
                break;
            }
        }
        if (playIndex == -1)
            return;
        playList.setPlayingIndex(playIndex);
        mPlayer.setPlayList(playList);
    }

    private void onPlayListNowEvent(PlayListNowEvent o) {
        if (mPlayer != null)
            mPlayer.play(o.playList, o.playIndex);
    }
}
