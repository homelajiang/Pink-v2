package com.lxy.pink.ui.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuan on 2016/10/18.
 */

public class VideoFragment extends BaseFragment implements VideoContract.View{
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private VideoContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recyclerview, container, false);
        ButterKnife.bind(this, root);

        int spanCount = getSpanCount();
        VideoFragmentAdapter videoFragmentAdapter = new VideoFragmentAdapter();
        videoFragmentAdapter.setSpanCount(spanCount);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),spanCount);
        gridLayoutManager.setSpanSizeLookup(videoFragmentAdapter.getSpanSizeLookup());

        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(videoFragmentAdapter);

        new VideoFragmentPresenter(getContext(),this).subscribe();

        return root;
    }

    private int getSpanCount() {
//        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
//        return (int) (dpWidth / 100);
        return 6;
    }

    @Override
    public void recommendLoad(ACRecommend acRecommend) {

    }

    @Override
    public void recommendError(Throwable e) {

    }

    @Override
    public void setPresenter(VideoContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
