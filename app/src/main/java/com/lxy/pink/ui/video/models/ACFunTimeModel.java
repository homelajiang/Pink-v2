package com.lxy.pink.ui.video.models;

import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.ui.video.views.ACFunTimeView;

/**
 * Created by yuan on 2016/12/20.
 */

public class ACFunTimeModel extends EpoxyModel<ACFunTimeView> {
    @EpoxyAttribute
    View.OnClickListener clickListener;

    @Override
    protected int getDefaultLayout() {
        return R.layout.ac_fun_time_model;
    }

    @Override
    public void bind(ACFunTimeView view) {
        if (clickListener != null) {
            view.funTimeLeft.setOnClickListener(clickListener);
            view.funTimeRight.setOnClickListener(clickListener);
        }
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
