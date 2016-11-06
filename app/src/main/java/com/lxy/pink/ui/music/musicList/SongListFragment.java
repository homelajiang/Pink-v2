package com.lxy.pink.ui.music.musicList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.pink.R;
import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.ui.base.BaseFragment;
import com.lxy.pink.ui.base.adapter.OnItemClickListener;
import com.lxy.pink.ui.common.DefaultDividerDecoration;

import java.util.List;

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
                // TODO: 2016/11/6
            }
        });
        mAdapter.setAddPlayListCallback(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 2;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DefaultDividerDecoration());

        new SongListPresenter(this).subscribe();
    }

    @Override
    public void onAction(View actionView, int position) {

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
        mAdapter.setPlayList(playList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(SongListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
