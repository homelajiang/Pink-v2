package com.lxy.pink.ui.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.pink.R;
import com.lxy.pink.ui.base.BaseFragment;

/**
 * Created by yuan on 2016/10/18.
 */

public class NewsFragment extends BaseFragment {
    public static final String TAG = "NEW_FRAGMENT";
    private View root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.recyclerview, container, false);
        return root;
    }
}
