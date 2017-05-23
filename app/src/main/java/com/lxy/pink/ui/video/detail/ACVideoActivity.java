package com.lxy.pink.ui.video.detail;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.Injection;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACVideoInfo;
import com.lxy.pink.data.source.AppRepository;
import com.lxy.pink.ui.base.BaseActivity;
import com.lxy.pink.ui.base.BaseFragment;
import com.lxy.pink.ui.video.comment.ACCommentFragment;
import com.lxy.pink.ui.video.info.ACVideoInfoFragment;
import com.lxy.pink.utils.schedulers.SchedulerProvider;

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
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.viewpage)
    ViewPager mViewpage;
    private TabLayout.Tab commentTab;
    private BaseFragment[] fragments;
    private ACVideoContract.Presenter presenter;

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
        fragments = new BaseFragment[2];
        fragments[0] = new ACVideoInfoFragment();
        fragments[1] = new ACCommentFragment();
        String[] titles = getResources().getStringArray(R.array.video_info);
        ACVideoPageAdapter adapter = new ACVideoPageAdapter(getSupportFragmentManager(), titles, fragments);
        mViewpage.setAdapter(adapter);
        mTablayout.setupWithViewPager(mViewpage);
        new ACVideoPresenter(this, AppRepository.getInstance(),Injection.provideSchedulerProvider(), 3529332).subscribe();
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
        super.onDestroy();
    }

    @Override
    public void setPresenter(ACVideoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getVideoInfoSuccess(ACVideoInfo info) {
        if (info.getCode() != 200) {
            String msg = TextUtils.isEmpty(info.getMessage()) ? getString(R.string.pink_error_indescribable) : info.getMessage();
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            ((ACVideoInfoFragment) fragments[0]).loadInfo(null);
            ((ACCommentFragment) fragments[1]).loadInfo(null);
            return;
        }
        ((ACVideoInfoFragment) fragments[0]).loadInfo(info.getData());
        ((ACCommentFragment) fragments[1]).loadInfo(info.getData());

    }

    @Override
    public void getVideoInfoFail(Throwable e) {

    }

    @Override
    public void getDanmukuSuccess(String danmuku) {

    }

    @Override
    public void getDanmukuFail(Throwable e) {

    }
}
