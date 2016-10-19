package com.lxy.pink.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.RxBus;
import com.lxy.pink.event.AuthCreateEvent;
import com.lxy.pink.event.ProfileUpdateEvent;
import com.lxy.pink.ui.base.BaseActivity;
import com.lxy.pink.ui.home.HomeFragment;
import com.lxy.pink.utils.TT;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainActivity extends BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    private Unbinder unbinder;
    private HeaderViewHolder headerViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        headerViewHolder = new HeaderViewHolder(mNavView.getHeaderView(0));
        headerViewHolder.mHeadIcon.setOnClickListener(this);
        headerViewHolder.mName.setOnClickListener(this);

//        setSupportActionBar(mToolbar);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, mDrawerLayout, mToolbar,
//                R.string.navigation_drawer_open, R.string.navigation_drawer_close
//        );
//        mDrawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);
        MainFragment mainFragment = new MainFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, mainFragment, HomeFragment.TAG)
                .commit();
    }

    @Override
    protected Subscription subscribeEvents() {

        return RxBus.getInstance().toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        if (o instanceof AuthCreateEvent) {

                        } else if (o instanceof ProfileUpdateEvent) {

                        }
                    }
                })
                .subscribe(RxBus.defaultSubscriber());
    }

    private void toUserCenter(){

    }

    private void toLogin(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.name:
            case R.id.head_icon:
                TT.s(getContext(), "clicked");
                break;
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
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
