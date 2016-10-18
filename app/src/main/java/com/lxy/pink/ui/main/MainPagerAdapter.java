package com.lxy.pink.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lxy.pink.ui.base.BaseFragment;

/**
 * Created by yuan on 2016/10/18.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    private final String[] mTitles;
    private final BaseFragment[] mFragments;

    public MainPagerAdapter(FragmentManager fm, String[] titles, BaseFragment[] baseFragments) {
        super(fm);
        mTitles = titles;
        mFragments = baseFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public int getCount() {
        if (mTitles == null)
            return 0;
        return mTitles.length;
    }
}
