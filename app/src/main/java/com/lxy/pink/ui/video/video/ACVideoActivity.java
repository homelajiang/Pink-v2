package com.lxy.pink.ui.video.video;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACActionFollow;
import com.lxy.pink.data.model.acfun.ACBananaCheck;
import com.lxy.pink.data.model.acfun.ACBananaInfo;
import com.lxy.pink.data.model.acfun.ACBananaPostRes;
import com.lxy.pink.data.model.acfun.ACCheckFollow;
import com.lxy.pink.data.model.acfun.ACVideoCommentData;
import com.lxy.pink.data.model.acfun.ACVideoCommentRes;
import com.lxy.pink.data.model.acfun.ACVideoInfo;
import com.lxy.pink.data.model.acfun.ACVideoMark;
import com.lxy.pink.data.model.acfun.ACVideoSearchLike;
import com.lxy.pink.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */

public class ACVideoActivity extends BaseActivity implements ACVideoContract.View {
    private static final String CONTENT_ID = "contentId";
    @BindView(R.id.video_cover)
    SimpleDraweeView videoCover;
    @BindView(R.id.video_danmu)
    FrameLayout videoDanmu;
    @BindView(R.id.play_button)
    ButtonBarLayout playButton;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ACVideoContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_video);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        int contentId = getIntent().getIntExtra(CONTENT_ID, 0);
        if (contentId <= 0) {
            Toast.makeText(this, R.string.ac_video_null, Toast.LENGTH_SHORT).show();
            return;
        }
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ACVideoAdapter adapter = new ACVideoAdapter(this);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        new ACVideoPresenter(this, this).subscribe();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(ACVideoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getVideoInfoSuccess(ACVideoInfo info) {

    }

    @Override
    public void getVideoInfoFail(Throwable e) {

    }

    @Override
    public void checkFollowSuccess(ACCheckFollow acCheckFollow) {

    }

    @Override
    public void checkFollowFail(Throwable e) {

    }

    @Override
    public void actionFollowSuccess(ACActionFollow acActionFollow) {

    }

    @Override
    public void actionFollowFail(Throwable e) {

    }

    @Override
    public void checkMarkSuccess(ACVideoMark acVideoMark) {

    }

    @Override
    public void checkMarkFail(Throwable e) {

    }

    @Override
    public void actionMarkSuccess(ACVideoMark acVideoMark) {

    }

    @Override
    public void actionMarkFail(Throwable e) {

    }

    @Override
    public void getBananaInfoSuccess(ACBananaInfo acBananaInfo) {

    }

    @Override
    public void getVananaInfoFail(Throwable e) {

    }

    @Override
    public void checkBananaSuccess(ACBananaCheck acBananaCheck) {

    }

    @Override
    public void checkBananaFail(Throwable e) {

    }

    @Override
    public void sendBananaSuccess(ACBananaPostRes res) {

    }

    @Override
    public void sendBananaFail(Throwable e) {

    }

    @Override
    public void getDanmukuSuccess(String danmuku) {

    }

    @Override
    public void getDanmukuFail(Throwable e) {

    }

    @Override
    public void getVideoCommentSuccess(ACVideoCommentData acVideoCommentData) {

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

    @Override
    public void getVideoRecommendSuccess(ACVideoSearchLike like) {

    }

    @Override
    public void getVideoRecommendFail(Throwable e) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }
}
