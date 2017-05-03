package com.lxy.pink.ui.video.video;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lxy.pink.ui.base.BaseFragment;

/**
 * Created by homelajiang on 2017/5/3 0003.
 */

public class ACVideoPageAdapter extends FragmentPagerAdapter {
    private final BaseFragment[] mFragments;
    private final String[] mTitles;

    public ACVideoPageAdapter(FragmentManager fm, String[] titles, BaseFragment[] fragments) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;
    }

    @Override
    public int getCount() {
        if (mTitles == null)
            return 0;
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }
}
