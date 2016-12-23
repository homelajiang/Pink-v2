package com.lxy.pink.ui.home.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lxy.pink.R;
import com.lxy.pink.ui.widget.ExListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2016/12/23 0023.
 */

public class PinkCalendarView extends CardView {
    @BindView(R.id.exListView)
    ExListView mExListView;
    @BindView(R.id.empty_list_view)
    TextView mEmptyListView;

    public PinkCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.pink_home_calendar_view, this);
        ButterKnife.bind(this);
    }
}
