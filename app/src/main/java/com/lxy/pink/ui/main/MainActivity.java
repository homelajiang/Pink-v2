package com.lxy.pink.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jaeger.library.StatusBarUtil;
import com.lxy.pink.R;
import com.lxy.pink.RxBus;
import com.lxy.pink.core.PinkService;
import com.lxy.pink.data.model.auth.Auth;
import com.lxy.pink.data.model.auth.Profile;
import com.lxy.pink.data.source.PreferenceManager;
import com.lxy.pink.event.AuthCreateEvent;
import com.lxy.pink.event.ProfileUpdateEvent;
import com.lxy.pink.ui.auth.LoginActivity;
import com.lxy.pink.ui.base.BaseActivity;
import com.lxy.pink.ui.home.HomeFragment;
import com.lxy.pink.ui.music.MusicFragment;
import com.lxy.pink.ui.news.NewsFragment;
import com.lxy.pink.ui.video.VideoFragment;
import com.lxy.pink.ui.video.detail.ACVideoActivity;
import com.lxy.pink.ui.video.fun.ACFunActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainActivity extends BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, MainContract.View {

    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    private Unbinder unbinder;
    private HeaderViewHolder headerViewHolder;
    private Auth auth;
    private Profile profile;
    private MainContract.Presenter mainPresenter;
    private HomeFragment homeFragment;
    private MusicFragment musicFragment;
    private VideoFragment videoFragment;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        StatusBarUtil.setTranslucentForDrawerLayout(this, mDrawerLayout, 0);
        initView();
        initData();
    }

    private void initData() {
        fm = getSupportFragmentManager();
        startService(new Intent(getContext(), PinkService.class));
        new MainPresenter(this).subscribe();
        auth = PreferenceManager.getAuth(this);
        homeFragment = new HomeFragment();
        fm.beginTransaction()
                .replace(R.id.fragment_container, homeFragment, HomeFragment.TAG)
                .commit();
        if (auth != null && profile != null) {
            updateProfile(profile);
        }
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        headerViewHolder = new HeaderViewHolder(mNavView.getHeaderView(0));
        headerViewHolder.mHeadIcon.setOnClickListener(this);
        headerViewHolder.mName.setOnClickListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected Subscription subscribeEvents() {
        return RxBus.getInstance().toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        if (o instanceof AuthCreateEvent) {
                            auth = ((AuthCreateEvent) o).auth;
                            getProfile();
                        } else if (o instanceof ProfileUpdateEvent) {
                            updateProfile(((ProfileUpdateEvent) o).profile);
                        }
                    }
                })
                .subscribe(RxBus.defaultSubscriber());
    }

    private void getProfile() {
        mainPresenter.getProfile(auth.getProfileId());
    }


    private void updateProfile(Profile profile) {
        headerViewHolder.mHeadIcon.setImageURI(Uri.parse(profile.getUserImg()));
        headerViewHolder.mName.setText(profile.getNickname());
        headerViewHolder.mInfo.setText(profile.getSignature());
        //TODO_LIST add more info
    }

    private void toUserCenter() {

    }

    private void toLogin() {
        startActivity(new Intent(getContext(), LoginActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.name:
            case R.id.head_icon:
                if (auth == null) {
                    toLogin();
                } else {
                    toUserCenter();
                }
                break;
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                FragmentTransaction b0 = fm.beginTransaction();
                if (videoFragment != null)
                    b0.hide(videoFragment);
                if (musicFragment != null)
                    b0.hide(musicFragment);
                b0.show(homeFragment);
                b0.commit();
                break;
            case R.id.nav_music:
                FragmentTransaction b1 = fm.beginTransaction();
                b1.hide(homeFragment);
                if (videoFragment != null)
                    b1.hide(videoFragment);
                if (musicFragment == null) {
                    musicFragment = new MusicFragment();
                    b1.add(R.id.fragment_container, musicFragment, MusicFragment.TAG);
                } else {
                    b1.show(musicFragment);
                }
                b1.commit();
                break;
            case R.id.nav_video:
                FragmentTransaction b2 = fm.beginTransaction();
                b2.hide(homeFragment);
                if (musicFragment != null)
                    b2.hide(musicFragment);
                if (videoFragment == null) {
                    videoFragment = new VideoFragment();
                    b2.add(R.id.fragment_container, videoFragment, VideoFragment.TAG);
                } else {
                    b2.show(videoFragment);
                }
                b2.commit();
                break;
            default:
                break;
        }
        int id = item.getItemId();
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return;
        } else {
            moveTaskToBack(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void profileLoad(Profile profile) {
        updateProfile(profile);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.mainPresenter = presenter;
    }

    static class HeaderViewHolder {
        @BindView(R.id.head_icon)
        SimpleDraweeView mHeadIcon;
        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.info)
        TextView mInfo;

        HeaderViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
