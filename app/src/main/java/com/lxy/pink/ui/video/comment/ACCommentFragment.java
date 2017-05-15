package com.lxy.pink.ui.video.comment;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.pink.Injection;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACVideoCommentData;
import com.lxy.pink.data.model.acfun.ACVideoCommentRes;
import com.lxy.pink.data.model.acfun.ACVideoInfo;
import com.lxy.pink.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;

/**
 * Created by homelajiang on 2017/5/2 0002.
 */

public class ACCommentFragment extends BaseFragment implements ACCommentContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private Unbinder unbinder;
    private ACCommentAdapter adapter;
    private ACCommentContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview, container, false);
        unbinder = ButterKnife.bind(this, view);
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,17,getContext().getResources().getDisplayMetrics());
        mRecyclerView.setPadding(padding,0,padding,0);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                DisplayMetrics metrics = Injection.provideContext().getResources().getDisplayMetrics();
                int size2 = (int) TypedValue.applyDimension(COMPLEX_UNIT_DIP, 3f, metrics);
                outRect.set(0, size2, 0, size2);
            }
        });
        adapter = new ACCommentAdapter(getContext());
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    public void loadInfo(ACVideoInfo.DataBean videoInfo) {
        new ACCommentPresenter(getContext(), this, videoInfo.getContentId()).subscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setPresenter(ACCommentContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getVideoCommentSuccess(ACVideoCommentData acVideoCommentData) {
        adapter.addData(acVideoCommentData.getData().getPage().getList(),
                acVideoCommentData.getData().getPage().getMap());
    }

    @Override
    public void getVideoCommentFail(Throwable e) {

    }

    @Override
    public void sendCommentSuccess(ACVideoCommentRes res) {

    }

    @Override
    public void sendCommentFail(Throwable e) {

    }
}
