package com.lxy.pink.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lxy.pink.R;
import com.lxy.pink.RxBus;
import com.lxy.pink.event.PlayListLoadedEvent;
import com.lxy.pink.event.PlayListNowEvent;
import com.lxy.pink.ui.base.BaseFragment;
import com.lxy.pink.ui.home.HomeFragment;
import com.lxy.pink.ui.music.SongListFragment;
import com.lxy.pink.ui.news.NewsFragment;
import com.lxy.pink.ui.video.VideoFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by yuan on 2016/10/16.
 */

public class MainFragment extends BaseFragment {

    static final int DEFAULT_PAGE_INDEX = 0;
    public static final String TAG = "MainFragment";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout_main)
    TabLayout mTabLayoutMain;
    private String[] titles;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        initViews();
        return root;
    }

    private void initViews() {
        ((AppCompatActivity) getActivity())
                .setSupportActionBar(toolbar);
        titles = getResources().getStringArray(R.array.pink_main_titles);
        BaseFragment[] fragments = new BaseFragment[titles.length];

        fragments[0] = new HomeFragment();
        fragments[1] = new SongListFragment();
        fragments[2] = new VideoFragment();
        fragments[3] = new NewsFragment();

        MainPagerAdapter adapter = new MainPagerAdapter(getActivity()
                .getSupportFragmentManager(), titles, fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(titles.length - 1);
//        viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.mp_margin_large));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mTabLayoutMain.setupWithViewPager(viewPager);
    }

    private void onItemChecked(int position) {
        viewPager.setCurrentItem(position);
        toolbar.setTitle(titles[position]);
    }

    @Override
    protected Subscription subscribeEvents() {
        return RxBus.getInstance().toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        if (o instanceof PlayListNowEvent) {
                        } else if (o instanceof PlayListLoadedEvent) {
                        }
                    }
                })
                .subscribe(RxBus.defaultSubscriber());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
