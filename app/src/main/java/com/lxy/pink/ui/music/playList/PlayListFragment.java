package com.lxy.pink.ui.music.playList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.pink.R;
import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.ui.base.BaseFragment;
import com.lxy.pink.ui.base.adapter.OnItemClickListener;
import com.lxy.pink.ui.common.DefaultDividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuan on 2016/10/18.
 */

public class PlayListFragment extends BaseFragment implements
        PlayListAdapter.AddPlayListCallback, PlayListContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private View root;
    private PlayListAdapter mAdapter;
    private PlayListContract.Presenter presenter;

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
        mAdapter = new PlayListAdapter(getContext(), null);
        mAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        mAdapter.setAddPlayListCallback(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DefaultDividerDecoration());

        new PlayListPresenter(this).subscribe();
    }

    @Override
    public void onAction(View actionView, int position) {

    }

    @Override
    public void onAddPlayList() {

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
    public void onPlayListLoaded(List<PlayList> playLists) {
        mAdapter.setData(playLists);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPlayListCreate(PlayList playList) {

    }

    @Override
    public void onPlayListEdited(PlayList playList) {

    }

    @Override
    public void onPlayListDeleted(PlayList playList) {

    }

    @Override
    public void setPresenter(PlayListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
