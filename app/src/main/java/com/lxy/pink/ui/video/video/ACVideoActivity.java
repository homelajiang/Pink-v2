package com.lxy.pink.ui.video.video;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */

public class ACVideoActivity extends BaseActivity {
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
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    private ACVideoAdapter adapter;
    private TabLayout.Tab commentTab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_video);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
/*        int contentId = getIntent().getIntExtra(CONTENT_ID, 0);
        if (contentId <= 0) {
            Toast.makeText(this, R.string.ac_video_null, Toast.LENGTH_SHORT).show();
            return;
        }*/
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new ACVideoAdapter(this, 3529332);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        mTablayout.addTab(mTablayout.newTab().setText(R.string.brief));
        commentTab = mTablayout.newTab().setText(R.string.comment);
        mTablayout.addTab(commentTab);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        adapter.onDestroy();
        super.onDestroy();
    }
}
