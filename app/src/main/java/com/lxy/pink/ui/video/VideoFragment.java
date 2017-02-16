package com.lxy.pink.ui.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuan on 2016/10/18.
 */

public class VideoFragment extends BaseFragment implements VideoContract.View, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private VideoContract.Presenter presenter;
    private VideoFragmentAdapter videoFragmentAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recyclerview_with_refresh, container, false);
        ButterKnife.bind(this, root);

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(),R.color.pink));
        mSwipeRefreshLayout.setOnRefreshListener(this);

        int spanCount = getSpanCount();
        videoFragmentAdapter = new VideoFragmentAdapter(getContext());
        videoFragmentAdapter.setSpanCount(spanCount);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), spanCount);
        gridLayoutManager.setSpanSizeLookup(videoFragmentAdapter.getSpanSizeLookup());

        mRecyclerView.addItemDecoration(new VerticalGridCardSpacingDecoration());
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(videoFragmentAdapter);
        isPrepared = true;
        loadData();
        return root;
    }

    @Override
    protected void loadData() {
        if(!isPrepared || !isVisible ||!isFirstInit) {
            return;
        }
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                new VideoFragmentPresenter(getContext(), VideoFragment.this).subscribe();
            }
        }, 500);
    }

    private int getSpanCount() {
//        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
//        return (int) (dpWidth / 100);
        return 6;
    }

    @Override
    public void recommendLoad(ACRecommend acRecommend) {
        isFirstInit = false;
        if (acRecommend == null) {
            Toast.makeText(getContext(), R.string.pink_error_indescribable, Toast.LENGTH_SHORT).show();
        } else if (acRecommend.getCode() == 200) {
            videoFragmentAdapter.setData(acRecommend);
        } else {
            if (TextUtils.isEmpty(acRecommend.getMessage())) {
                Toast.makeText(getContext(), R.string.pink_error_indescribable, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), acRecommend.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        stopRefresh();
    }

    @Override
    public void recommendError(Throwable e) {

    }

    public void stopRefresh() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void setPresenter(VideoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.getRecommend();
            }
        },500);
    }
}
