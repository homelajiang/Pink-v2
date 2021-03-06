package com.lxy.pink.ui.music;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.pink.R;
import com.lxy.pink.RxBus;
import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.event.PlayListNowEvent;
import com.lxy.pink.ui.base.BaseFragment;
import com.lxy.pink.ui.base.adapter.OnItemClickListener;
import com.lxy.pink.ui.permission.FcPermissions;
import com.lxy.pink.ui.permission.FcPermissionsCallbacks;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuan on 2016/10/18.
 */

public class MusicFragment extends BaseFragment implements
        MusicAdapter.AddPlayListCallback, MusicContract.View, SwipeRefreshLayout.OnRefreshListener,
        FcPermissionsCallbacks {
    public static final String TAG = "MUSIC_FRAGMENT";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private View root;
    private MusicAdapter mAdapter;
    private MusicContract.Presenter presenter;
    private PlayList playList;

    private String[] PERMISSION_STROGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private final int PERMISSION_CODE_STORAFGE = 10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.recyclerview_with_refresh, container, false);
        ButterKnife.bind(this, root);
        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.pink));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mAdapter = new MusicAdapter(getContext(), null);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (playList != null) {
                    PlayListNowEvent e = new PlayListNowEvent(playList, position);
                    RxBus.getInstance().post(e);
                }
            }
        });
        mAdapter.addPlayListCallback(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 12);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 12;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(mAdapter);

        isPrepared = true;
        loadData();
        return root;
    }

    @Override
    protected void loadData() {
        if (!isPrepared || !isVisible || !isFirstInit) {
            return;
        }
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                new MusicPresenter(MusicFragment.this).subscribe();
                //※ link start ※
                FcPermissions.requestPermissions(MusicFragment.this, "", PERMISSION_CODE_STORAFGE, PERMISSION_STROGE);
            }
        }, 500);
    }

    @Override
    public void onAction(View actionView, int position) {
        Logger.d(position);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void handleError(Throwable e) {

    }

    @Override
    public void onMusicListLoaded(PlayList playList) {
        isFirstInit = false;
        this.playList = playList;
        mAdapter.setPlayList(playList);
        mAdapter.notifyDataSetChanged();
        stopRefresh();
//        if (playList != null) {
//            PlayListLoadedEvent e = new PlayListLoadedEvent(playList);
//            RxBus.getInstance().post(e);
//        }
    }

    public void stopRefresh() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void setPresenter(MusicContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        FcPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == PERMISSION_CODE_STORAFGE) {
            presenter.subscribe();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == PERMISSION_CODE_STORAFGE) {
            FcPermissions.checkDeniedPermissionsNeverAskAgain(this, "需要获取读写存储的权限",
                    R.string.pink_setting, R.string.pink_cancel, null, perms);
        }
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.loadMusicList(new ArrayList<String>());
            }
        }, 500);
    }
}
