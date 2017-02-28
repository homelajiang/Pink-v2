package com.lxy.pink.ui.video.models;

import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.ui.video.views.ACVDataView;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */

public class ACVDataModel extends EpoxyModel<ACVDataView> {
    @Override
    protected int getDefaultLayout() {
        return R.layout.ac_video_info_data_model;
    }

    @Override
    public void bind(ACVDataView view) {
        super.bind(view);
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
