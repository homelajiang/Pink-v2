package com.lxy.pink.ui.music.musicList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.pink.R;
import com.lxy.pink.RxBus;
import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.event.PlayListLoadedEvent;
import com.lxy.pink.event.PlayListNowEvent;
import com.lxy.pink.ui.base.BaseFragment;
import com.lxy.pink.ui.base.adapter.OnItemClickListener;
import com.lxy.pink.ui.common.DefaultDividerDecoration;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuan on 2016/10/18.
 */

public class SongListFragment extends BaseFragment implements
        SongListAdapter.AddPlayListCallback, SongListContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private View root;
    private SongListAdapter mAdapter;
    private SongListContract.Presenter presenter;
    private PlayList playList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.recyclerview, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new SongListAdapter(getContext(), null);
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
        recyclerView.addItemDecoration(new DefaultDividerDecoration());

        new SongListPresenter(this).subscribe();
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
        this.playList = playList;
        mAdapter.setPlayList(playList);
        mAdapter.notifyDataSetChanged();
        if (playList != null) {
            PlayListLoadedEvent e = new PlayListLoadedEvent(playList);
            RxBus.getInstance().post(e);
        }
    }

    @Override
    public void setPresenter(SongListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
