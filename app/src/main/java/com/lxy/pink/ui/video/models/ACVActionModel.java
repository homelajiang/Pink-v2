package com.lxy.pink.ui.video.models;

import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.ui.video.views.ACVActionView;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */

public class ACVActionModel extends EpoxyModel<ACVActionView> {

    @EpoxyAttribute
    View.OnClickListener clickListener;

    @Override
    protected int getDefaultLayout() {
        return R.layout.ac_video_info_action_model;
    }

    @Override
    public void bind(ACVActionView view) {
        super.bind(view);
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
