package com.lxy.pink.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lxy.pink.R;
import com.lxy.pink.ui.base.BaseFragment;
import com.lxy.pink.ui.home.HomeFragment;
import com.lxy.pink.ui.music.MusicFragment;
import com.lxy.pink.ui.news.NewsFragment;
import com.lxy.pink.ui.video.VideoFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

/**
 * Created by yuan on 2016/10/16.
 */

public class MainFragment extends BaseFragment {

    static final int DEFAULT_PAGE_INDEX = 0;

    @BindViews({R.id.radio_btn_main, R.id.radio_btn_music,
            R.id.radio_btn_video, R.id.radio_btn_news})
    List<RadioButton> radioButtons;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private String[] titles;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        ((AppCompatActivity) getActivity())
                .setSupportActionBar(toolbar);
        titles = getResources().getStringArray(R.array.pink_main_titles);

        BaseFragment[] fragments = new BaseFragment[titles.length];

        fragments[0] = new HomeFragment();
        fragments[1] = new MusicFragment();
        fragments[2] = new VideoFragment();
        fragments[3] = new NewsFragment();

        MainPagerAdapter adapter = new MainPagerAdapter(getActivity()
                .getSupportFragmentManager(), titles, fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(titles.length - 1);
        viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.mp_margin_large));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                radioButtons.get(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        radioButtons.get(DEFAULT_PAGE_INDEX).setChecked(true);
        return root;
    }

    @OnCheckedChanged({R.id.radio_btn_main, R.id.radio_btn_music,
            R.id.radio_btn_video, R.id.radio_btn_news})
    public void onRadioButtonChecked(RadioButton button, boolean isChecked) {
        if (isChecked) {
            onItemChecked(radioButtons.indexOf(button));
        }
    }

    private void onItemChecked(int position) {
        viewPager.setCurrentItem(position);
        toolbar.setTitle(titles[position]);
    }

}
